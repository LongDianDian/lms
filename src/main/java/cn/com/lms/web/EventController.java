package cn.com.lms.web;

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
import cn.com.lms.entity.Event;
import cn.com.lms.entity.School;
import cn.com.lms.query.BookQuery;
import cn.com.lms.query.EventQuery;
import cn.com.lms.service.EventService;
import cn.com.lms.service.SchoolService;
import cn.com.lms.vo.EventVo;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;

@Controller
@RequestMapping(value="lms/event")
public class EventController {
	
	@Resource
	private SchoolService schoolService;
	
	@Resource
	private EventService eventService;
	
	@RequestMapping(value="toAdd/{type}")
	public String toCreateEvent(Model model,@PathVariable int type){
		List<School> lists = schoolService.listWithOutDel();
		model.addAttribute("schools", lists);
		model.addAttribute("type", type);
		return "site.lms.event.addEvent";
	}
	
	@RequestMapping(value="add" ,method=RequestMethod.POST)
	@ResponseBody
	public RestResponse add(@RequestBody EventVo eventVo){
		RestResponse response = new RestResponse();
		eventService.saveEventByEventVo(eventVo);
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	
	@RequestMapping(value="list/{type}")
	public String list(Model model,@PathVariable Integer type){
		model.addAttribute("type", type);
		return "site.lms.event.list";
	}
	@RequestMapping(value="getDate")
	@ResponseBody
	public Object getDate(EventQuery query){
		Integer count = eventService.countByQury(query);
		List<Event> events =eventService.listByQuery(query); 
		for(Event event : events){
			event.setMedias(null);
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("recordsTotal",count);
		map.put("recordsFiltered",count);
		map.put("draw", query.getDraw());
		map.put("data", events);
		return map;
	}
	
	
	@RequestMapping(value="toUpdate/{type}")
	public String update(Model model ,@PathVariable Integer type ,Integer eventId){
		List<School> lists = schoolService.listWithOutDel();
		model.addAttribute("type",type );
		model.addAttribute("event", eventService.findById(eventId));
		model.addAttribute("schools", lists);
		return "site.lms.event.update";
	}
	
	@RequestMapping(value="delete/{id}")
	@ResponseBody
	public RestResponse delete(@PathVariable int id ){
		RestResponse response = new RestResponse();
		eventService.deleteById(id);
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	@RequestMapping("detail/{libraryId}")
	public String detail(Model model ,@PathVariable Integer libraryId){
		
		return "site.lms.library.detail";
	}

}
