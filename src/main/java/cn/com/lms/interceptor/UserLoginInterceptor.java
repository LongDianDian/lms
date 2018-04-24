package cn.com.lms.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.lms.dto.UserDto;
import cn.com.lms.service.TicketService;


/**
 * 
 *用户登录拦截器
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter{
	@Resource
	private TicketService ticketService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie :cookies){
				if("lms.cookie.ticket".equals(cookie.getName())){
					String ticket = cookie.getValue();
					UserDto userDto = ticketService.checkTicket(ticket);
					if(userDto!=null){
						request.getSession().setAttribute("userInfo", userDto);
						request.setAttribute("userInfo", userDto);
						return true;
					}
				}
			}
		}
		response.sendRedirect("/lms/login");
		return false;
	}
}
