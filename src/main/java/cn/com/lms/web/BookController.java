package cn.com.lms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.lms.entity.Book;
import cn.com.lms.query.BookQuery;
import cn.com.lms.service.BookService;
import cn.videoworks.commons.util.json.JsonConverter;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;
import cn.videoworks.commons.webdev.vo.Page;

/**
 * 
 *图书馆里
 */
@Controller
@RequestMapping(value="/lms/book")
public class BookController {
	
	@Resource
	private BookService bookService;
	
	
	@RequestMapping(value="list")
	public String list(Model model,BookQuery query){
		return "site.lms.book.list";
	}
	@RequestMapping(value="getDate")
	@ResponseBody
	public Object getDate(BookQuery query){
		Integer count = bookService.countByQury(query);
		List<Book> books =bookService.listByQuery(query); 
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("recordsTotal",count);
		map.put("recordsFiltered",count);
		map.put("draw", query.getDraw());
		map.put("data", books);
		return map;
	}
}
