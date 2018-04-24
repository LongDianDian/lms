package cn.com.lms.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.lms.entity.Book;
import cn.com.lms.entity.BookType;
import cn.com.lms.entity.BookTypeMapping;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.service.BookService;
import cn.com.lms.service.BookTypeMappingService;
import cn.com.lms.service.BookTypeService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;

@Controller
@RequestMapping("/lms/bookType")
public class BookTypeController {

	@Resource
	private BookTypeService bookTypeService;

	@Resource
	private BookTypeMappingService bookTypeMappingService;

	@Resource
	private BookService bookService;

	@RequestMapping("list")
	public String list(Model model) {

		model.addAttribute("list", bookTypeService.list());
		return "site.lms.bookType.list";
	}

	@RequestMapping(value = "addOrUpdate")
	@ResponseBody
	public RestResponse add(@RequestBody BookType bookType) {
		RestResponse response = new RestResponse();
		List<BookType> list = bookTypeService.list();
		List<BookType> toRemoveList = new ArrayList<BookType>();
		BookType oldBookType = null;
		if (null != bookType) {
			if (bookType.getId() != 0) {
				for (BookType bookType2 : list) {
					if (bookType2.getId() == bookType.getId()) {
						oldBookType = bookType2;
						break;
					}
				}
				if (oldBookType.getOrder() == bookType.getOrder()) {
					list.remove(oldBookType);
					bookTypeService.update(bookType);
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getId() != bookType.getId()) {
							if (list.get(i).getOrder() >= oldBookType
									.getOrder()) {
								list.get(i)
										.setOrder(list.get(i).getOrder() + 1);
							} else {
								toRemoveList.add(list.get(i));
							}
						} else {
							list.get(i).setName(bookType.getName());
							list.get(i).setOrder(bookType.getOrder());
						}
					}
				}
			} else {
				int order = bookType.getOrder();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getOrder() >= order) {
						list.get(i).setOrder(list.get(i).getOrder() + 1);
					} else {
						toRemoveList.add(list.get(i));
					}
				}
				bookTypeService.save(bookType);
			}
		}
		list.removeAll(toRemoveList);
		bookTypeService.update(list);
		response.setMessage("操作成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

	@RequestMapping(value = "toTop/{typeId}")
	@ResponseBody
	public RestResponse toTop(@PathVariable(value = "typeId") Integer typeId) {
		RestResponse response = new RestResponse();
		List<BookType> list = bookTypeService.list();
		List<BookType> toRemoveList = new ArrayList<BookType>();
		int order = 1;
		if (null != list && list.size() > 0) {
			for (BookType type : list) {
				if (type.getId() == typeId) {
					order = type.getOrder();
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOrder() < order) {
					list.get(i).setOrder(list.get(i).getOrder() + 1);
				} else if (list.get(i).getOrder() == order) {
					list.get(i).setOrder(1);
				} else if (list.get(i).getOrder() > order) {
					toRemoveList.add(list.get(i));
				}
			}
		}
		list.removeAll(toRemoveList);
		bookTypeService.update(list);
		response.setMessage("操作成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

	@RequestMapping(value = "delete/{typeId}")
	@ResponseBody
	public RestResponse delete(@PathVariable(value = "typeId") Integer typeId) {
		RestResponse response = new RestResponse();
		if (typeId != null) {
			bookTypeService.delete(typeId);
		}
		List<BookType> list = bookTypeService.list();
		if(null!= list && list.size()>0){
			int i =1;
			for(BookType bookType: list){
				bookType.setOrder(i);
				i++;
			}
			bookTypeService.update(list);
		}
		response.setMessage("操作成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

	@RequestMapping("detail/{bookTypeId}")
	public String bookTypeDetail(Model model,
			@PathVariable("bookTypeId") int bookTypeId) {
		BookType bookType = bookTypeService.findById(bookTypeId);
		model.addAttribute("bookType", bookType);
		return "site.lms.bookType.detail";
	}

	@RequestMapping("mapping/list")
	@ResponseBody
	public Object mappingList(BookMappingQuery query) {
		Integer count = bookTypeMappingService.countByQury(query);
		List<BookTypeMapping> bookMappings = bookTypeMappingService
				.listByQuery(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordsTotal", count);
		map.put("recordsFiltered", count);
		map.put("draw", query.getDraw());
		map.put("data", bookMappings);
		return map;
	}

	@RequestMapping("addBookForModel")
	@ResponseBody
	public Object addBookForModel(BookMappingQuery query) {
		Integer bookTypeId = query.getBookTypeId();
		List<Integer> bookIds = bookTypeMappingService
				.getBookIdsByBookTypeId(bookTypeId);
		Integer count = bookService.countForAddModel(bookIds, query);
		List<Book> books = bookService.listForAddModel(bookIds, query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordsTotal", count);
		map.put("recordsFiltered", count);
		map.put("draw", query.getDraw());
		map.put("data", books);
		return map;
	}

	/**
	 * 添加到mapping
	 * 
	 * @param bookTypeId
	 * @param bookIds
	 * @return
	 */
	@RequestMapping(value = "addMapping/{bookTypeId}")
	@ResponseBody
	public RestResponse addMapping(@PathVariable("bookTypeId") int bookTypeId,
			@RequestBody List<Integer> bookIds) {
		RestResponse response = new RestResponse();
		if (null != bookIds && bookIds.size() > 0) {
			for (Integer bookId : bookIds) {
				BookTypeMapping mapping = new BookTypeMapping();
				mapping.setBookTypeId(bookTypeId);
				mapping.setCreatetime(new Date());
				Book book = bookService.getById(bookId);
				mapping.setBook(book);
				mapping.setBookOrder(100);
				bookTypeMappingService.save(mapping);
			}
		}
		response.setMessage("操作成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

	/**
	 * 上下架
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("shelve/{id}/{status}")
	@ResponseBody
	public RestResponse shelve(@PathVariable("id") int id,
			@PathVariable("status") int status) {
		RestResponse response = new RestResponse();
		BookTypeMapping bookTypeMapping = bookTypeMappingService.findById(id);
		if (bookTypeMapping != null) {
			bookTypeMapping.setShelveStatus(status);
			bookTypeMappingService.update(bookTypeMapping);
			response.setMessage("操作成功!");
			response.setStatusCode(ResponseStatusCode.OK);
		} else {
			response.setMessage("对于数据异常！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		}
		return response;
	}
	/**
	 * 上下架
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="batchShelve/{status}",method=RequestMethod.POST)
	@ResponseBody
	public RestResponse batchShelve(@RequestBody List<Integer> ids,
			@PathVariable("status") int status) {
		RestResponse response = new RestResponse();
		if(null!=ids && ids.size()>0){
			for(Integer id : ids){
				BookTypeMapping bookTypeMapping = bookTypeMappingService.findById(id);
				if (bookTypeMapping != null) {
					bookTypeMapping.setShelveStatus(status);
					bookTypeMappingService.update(bookTypeMapping);
					response.setMessage("操作成功!");
					response.setStatusCode(ResponseStatusCode.OK);
				} else {
					response.setMessage("对于数据异常！");
					response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
				}
			}
		}
		return response;
	}


	/**
	 * 置顶
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("mappingToTop/{id}/{typeId}")
	@ResponseBody
	public RestResponse toTop(@PathVariable("id") int id,@PathVariable("typeId") int typeId) {
		RestResponse response = new RestResponse();
//		BookTypeMapping bookTypeMapping = bookTypeMappingService.findById(id);
		BookMappingQuery query = new BookMappingQuery();
		query.setBookTypeId(typeId);
		query.setStart(0);
		query.setLength(99);
		List<BookTypeMapping> bookMappings = bookTypeMappingService.listByQuery(query);
		BookTypeMapping bookTypeMapping = null;
		for (BookTypeMapping mapping : bookMappings) {
			if (mapping.getId() == id) {
				bookTypeMapping = mapping;
				break;
			}
		}
		if(null==bookTypeMapping){
			bookTypeMapping = bookTypeMappingService.findById(id);
			bookTypeMapping.setBookOrder(1);
			bookTypeMappingService.update(bookTypeMapping);
			for(BookTypeMapping map :  bookMappings){
				map.setBookOrder(map.getBookOrder()-1);
			}
			bookTypeMappingService.update(bookMappings);
		}else{
			for(BookTypeMapping map : bookMappings){
				if(map.getId()!=id && map.getBookOrder()<bookTypeMapping.getBookOrder()){
					map.setBookOrder(map.getBookOrder()+1);
					bookTypeMappingService.update(map);
				}
				
			}
			bookTypeMapping.setBookOrder(1);
			bookTypeMappingService.update(bookTypeMapping);
		}
		
		response.setMessage("操作成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}

	@RequestMapping(value = "mappingUpdataOrder", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse mappingUpdataOrder(@RequestBody Map<String, Object> map) {
		RestResponse response = new RestResponse();
		Integer order = (Integer) map.get("mappingOrder");
		if (order >= 100) {
			BookTypeMapping bookTypeMapping = bookTypeMappingService
					.findById((Integer) map.get("mappingId"));
			bookTypeMapping.setBookOrder(100);
			bookTypeMappingService.update(bookTypeMapping);
		} else {
			BookMappingQuery query = new BookMappingQuery();
			query.setBookTypeId((Integer) map.get("bookTypeId"));
			query.setStart(0);
			query.setLength(99);
			List<BookTypeMapping> bookMappings = bookTypeMappingService
					.listByQuery(query);
			List<BookTypeMapping> toUpdateList = new ArrayList<BookTypeMapping>();
			BookTypeMapping bookTypeMapping = null;
			for (BookTypeMapping mapping : bookMappings) {
				if (mapping.getId() == (Integer) map.get("mappingId")) {
					bookTypeMapping = mapping;
					break;
				}
			}
			if ((Integer) map.get("mappingOrder") > bookTypeMapping
					.getBookOrder()) {
				for (BookTypeMapping mapping : bookMappings) {
					if ( mapping.getId() != (Integer) map.get("mappingId")
							&& mapping.getBookOrder() <= (Integer) map
									.get("mappingOrder")
							&& mapping.getBookOrder() >= bookTypeMapping
									.getBookOrder()) {
						mapping.setBookOrder(mapping.getBookOrder()-1);
						toUpdateList.add(mapping);
					}
				}
			}
			if ((Integer) map.get("mappingOrder") < bookTypeMapping
					.getBookOrder()) {
				for (BookTypeMapping mapping : bookMappings) {
					if (mapping.getId() != (Integer) map.get("mappingId")
							&& mapping.getBookOrder() >= (Integer) map
									.get("mappingOrder")
							&& mapping.getBookOrder()<= bookTypeMapping
									.getBookOrder()) {
						mapping.setBookOrder(mapping.getBookOrder()+1);
						toUpdateList.add(mapping);
					}
				}
			}
			bookTypeMapping.setBookOrder((Integer)map.get("mappingOrder"));
			toUpdateList.add(bookTypeMapping);
			bookTypeMappingService.update(toUpdateList);
		}
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	@RequestMapping("mappingRemove/{mappingId}")
	@ResponseBody
	public RestResponse mappingRemov(@PathVariable int mappingId){
		RestResponse response = new RestResponse();
		bookTypeMappingService.deleteById(mappingId);
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
}
