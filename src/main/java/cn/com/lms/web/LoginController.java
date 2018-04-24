package cn.com.lms.web;


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.lms.entity.User;
import cn.com.lms.service.TicketService;
import cn.com.lms.service.UserService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;

/**
 * 
 *登录
 */
@Controller
@RequestMapping("lms/login")
public class LoginController {
	@Resource
	private UserService userService;
	@Resource
	private TicketService ticketService;

	@RequestMapping("")
	public String index(Model model){
		
		return "raw.lms.login.index";
	}
	
	@RequestMapping(value="" ,method=RequestMethod.POST)
	@ResponseBody
	public RestResponse login(@RequestBody Map<String,String> map ,HttpServletResponse httpResponse){
		RestResponse response = new RestResponse();
		if(!(map.containsKey("username")&&map.containsKey("password"))){
			response.setMessage("登录参数异常！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		User user = userService.login(map.get("username"), map.get("password"));
		if(null==user){
			response.setMessage("用户名或密码错误！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		if(user.getStatus()==2){
			response.setMessage("该用户被锁定！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		String ticket = ticketService.addTicket(user.getUsername());
		Cookie cookie = new Cookie("lms.cookie.ticket",ticket);
		cookie.setMaxAge(6000);
		httpResponse.addCookie(cookie);
		response.setMessage("登录成功！");
		response.getHeader().put("referer", "/lms/book/list");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	} 
	
	@RequestMapping("logoutAndReferer")
	public void logoutAndReferer(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Cookie[] cookies = request.getCookies();
		if(null!= cookies && cookies.length>0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("lms.cookie.ticket")){
					String ticket = cookie.getValue();
					ticketService.delete(ticket);
				}
			}
		}
		response.sendRedirect("/lms/login");
	}
	@RequestMapping(value="editPassword",method=RequestMethod.POST)
	@ResponseBody
	public RestResponse editPasword (@RequestBody Map<String,String> map){
		RestResponse response = new RestResponse();
		User user = userService.login(map.get("username"), map.get("oldPassword"));
		if(user==null){
			response.setMessage("秘密错误，修改失败！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		user.setPassword(map.get("newPassword"));
		userService.update(user);
		response.setMessage("修改成功！");
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
}
