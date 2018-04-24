package cn.com.lms.service;

import cn.com.lms.dto.UserDto;

public interface TicketService {
	
	public String addTicket(String username);
	
	public UserDto checkTicket(String ticket);

	public void delete(String ticket);

}
