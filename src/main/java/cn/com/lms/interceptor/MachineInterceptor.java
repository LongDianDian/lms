package cn.com.lms.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.lms.dto.RestResponse;
import cn.com.lms.dto.UserDto;
import cn.com.lms.entity.Machine;
import cn.com.lms.service.MachineService;
import cn.com.lms.service.TicketService;
import cn.videoworks.commons.util.json.JsonConverter;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;


/**
 * 
 *借阅机认证拦截
 */
public class MachineInterceptor extends HandlerInterceptorAdapter{
	@Resource
	private MachineService machineService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String macId = request.getHeader("macAddress");
		if(StringUtils.isNotEmpty(macId)){
			Machine machine = machineService.getMac(macId);
			if(null!= machine&& machine.getDisabled()==false){
				return true;
			}
		}
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(ResponseStatusCode.CONFLICT);
		restResponse.setMessage("借阅机信息验证失败！");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonConverter.format(restResponse));
		return false;
	}
}
