package cn.com.lms.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.lms.dto.RestResponse;
import cn.com.lms.entity.Book;
import cn.com.lms.entity.BookType;
import cn.com.lms.entity.BookTypeMapping;
import cn.com.lms.entity.Event;
import cn.com.lms.entity.Machine;
import cn.com.lms.entity.Media;
import cn.com.lms.entity.School;
import cn.com.lms.entity.Version;
import cn.com.lms.query.BookQueryForApi;
import cn.com.lms.query.EventQueryForApi;
import cn.com.lms.query.LibraryQueryForApi;
import cn.com.lms.query.MediaQuery;
import cn.com.lms.service.BookService;
import cn.com.lms.service.BookTypeMappingService;
import cn.com.lms.service.BookTypeService;
import cn.com.lms.service.EventService;
import cn.com.lms.service.LibraryService;
import cn.com.lms.service.MachineService;
import cn.com.lms.service.MediaService;
import cn.com.lms.service.SchoolService;
import cn.com.lms.service.VersionService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;

@Controller
@RequestMapping("/lms/api")
public class ApiController {
	
	@Resource
	private SchoolService schoolService;
	
	@Resource
	private BookTypeService bookTypeService;
	
	@Resource
	private BookTypeMappingService bookTypeMappingService;
	
	@Resource
	private MachineService machineService;
	
	@Resource
	private VersionService versionService;
	
	@Resource
	private BookService bookService;
	
	@Resource
	private EventService eventService;
	
	@Resource
	private LibraryService libraryService;
	
	@Resource
	private MediaService mediaService;
	
	/**
	 * 学校信息查询
	 * @param id
	 * @return
	 */
	@RequestMapping("schoolInfo/{id}")
	@ResponseBody
	public RestResponse getSchoolInfo(@PathVariable int id ){
		RestResponse response = new RestResponse();
		School school= schoolService.findById(id);
		if(school==null){
			response.setMessage("not exist");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		response.setMessage("success");
		response.getBody().put("schoolInfo", school);
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	/**
	 * 获得图书分类信息
	 * @return
	 */
	@RequestMapping("getBookTypes")
	@ResponseBody
	public RestResponse getBookTypes(){
		RestResponse response = new RestResponse();
		List<BookType> list = bookTypeService.list();
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		response.getBody().put("bookTypes", list);
		return response;
	}
	
	/**
	 * 获取图书信息
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="getBooks")
	@ResponseBody
	public RestResponse getBooks(@RequestBody BookQueryForApi bookQueryForApi ){
		RestResponse response = new RestResponse();
		if(bookQueryForApi.getPageStartNum()==null|| bookQueryForApi.getPageSize()==null){
			response.setMessage("params exception");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		List<BookTypeMapping> list = bookTypeMappingService.findByBookQueryApi(bookQueryForApi);
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
		if(null!= list  && list.size()>0){
			for(BookTypeMapping mapping: list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", mapping.getBook().getId());
				map.put("bookName", mapping.getBook().getBookname());
				map.put("bookPoster", mapping.getBook().getBookPoster());
				map.put("press", mapping.getBook().getPress());
				map.put("bookUrl", mapping.getBook().getBookUrl());
				map.put("createtime", mapping.getBook().getCreatetime());
				map.put("description", mapping.getBook().getDescription());
				map.put("press", mapping.getBook().getPress());
				map.put("publicationDate", null!= mapping.getBook().getPublicationDate()?format.format(mapping.getBook().getPublicationDate()):"");
				mapList.add(map);
			}
		}
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		response.getBody().put("books", mapList);
		response.getBody().put("pageStartNum", bookQueryForApi.getPageStartNum());
		response.getBody().put("pageSize", list.size());
		return response;
	}
	
	/**
	 * 获取图书信息
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="book/{id}")
	@ResponseBody
	public RestResponse getBooks(@PathVariable int id ){
		RestResponse response = new RestResponse();
		Book book = bookService.getById(id);
		if(book!=null){
			response.setMessage("success");
			response.setStatusCode(ResponseStatusCode.OK);
			response.getBody().put("book", book);
		}else{
			response.setMessage("error");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping("machine/{macId}")
	@ResponseBody
	public RestResponse machine(@PathVariable String macId){
		RestResponse response = new RestResponse();
		if(StringUtils.isNotEmpty(macId)){
			Machine oldMmachine = machineService.getMac(macId);
			if(null!= oldMmachine){
				response.setMessage("该mac已经注册！");
				response.setStatusCode(ResponseStatusCode.OK);
				response.getBody().put("info", oldMmachine);
				return response;
			}
			Machine machine = new Machine();
			machine.setCreateTime(new Date());
			machine.setDisabled(false);
			machine.setMac(macId);
			machineService.save(machine);
			
			response.setMessage("该mac注册成功！");
			response.setStatusCode(ResponseStatusCode.OK);
			return response;
		}else{
			response.setMessage("参数有误！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
	}
	@RequestMapping("machineInfo/{macId}")
	@ResponseBody
	public RestResponse machineInfo(@PathVariable String macId){
		RestResponse response = new RestResponse();
		if(StringUtils.isNotEmpty(macId)){
			Machine oldMmachine = machineService.getMac(macId);
			if(null!= oldMmachine){
				response.setStatusCode(ResponseStatusCode.OK);
				response.getBody().put("info", oldMmachine);
				return response;
			}
		}
		response.setMessage("参数有误！");
		response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		return response;
	}
	
	@RequestMapping("version")
	@ResponseBody 
	public RestResponse getVersion(){
		RestResponse response = new RestResponse();
		Version version = versionService.getVersion();
		if(null!=version){
			response.setStatusCode(ResponseStatusCode.OK);
			response.getBody().put("version", version);
			return response;
		}
		response.setMessage("无最新版本！");
		response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		return response;
	}
	
	/**
	 * 获取事件列表
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="event/list")
	@ResponseBody
	public RestResponse getEvents(@RequestBody EventQueryForApi eventQueryForApi ){
		RestResponse response = new RestResponse();
		if(eventQueryForApi.getPageStartNum()==null|| eventQueryForApi.getPageSize()==null){
			response.setMessage("params exception");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		List<Map<String,Object>> list = eventService.findByEventQueryApi(eventQueryForApi); 
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		response.getBody().put("events", list);
		response.getBody().put("pageStartNum", eventQueryForApi.getPageStartNum());
		response.getBody().put("pageSize", list.size());
		return response;
	}
	/**
	 * 获取事件列表
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="event/detail/{id}")
	@ResponseBody
	public RestResponse getEvents(@PathVariable Integer id){
		RestResponse response = new RestResponse();
		if(id==null){
			response.setMessage("params exception");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		Event event = eventService.findById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(event!=null){
			map.put("id", event.getId());
			map.put("title", event.getTitle());
			map.put("type", event.getType());
			map.put("content", event.getContent());
			map.put("outline", event.getOutline());
			map.put("createtime", event.getCreateTime());
		}
		response.getBody().put("event", map);
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	/**
	 * 获取校园风采
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="library/list")
	@ResponseBody
	public RestResponse getEvents(@RequestBody LibraryQueryForApi libraryQueryForApi ){
		RestResponse response = new RestResponse();
		if(libraryQueryForApi.getPageStartNum()==null|| libraryQueryForApi.getPageSize()==null){
			response.setMessage("params exception");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		List<Map<String,Object>> list = libraryService.findByLibraryQueryApi(libraryQueryForApi); 
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		response.getBody().put("librarys", list);
		response.getBody().put("pageStartNum", libraryQueryForApi.getPageStartNum());
		response.getBody().put("pageSize", list.size());
		return response;
	}
	/**
	 * 获取校园风采详细内容
	 * @param bookQueryForApi
	 * @return
	 */
	@RequestMapping(value="meida/detail")
	@ResponseBody
	public RestResponse getEvents(@RequestBody MediaQuery mediaQuery ){
		RestResponse response = new RestResponse();
		if(mediaQuery.getPageStartNum()==null|| mediaQuery.getPageSize()==null){
			response.setMessage("params exception");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		List<Map<String,Object>> list = mediaService.listByQueryForApi(mediaQuery); 
		response.setMessage("success");
		response.setStatusCode(ResponseStatusCode.OK);
		response.getBody().put("librarys", list);
		response.getBody().put("pageStartNum", mediaQuery.getPageStartNum());
		response.getBody().put("pageSize", list.size());
		return response;
	}
}
