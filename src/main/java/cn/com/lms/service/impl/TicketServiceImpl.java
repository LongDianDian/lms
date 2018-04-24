package cn.com.lms.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.com.lms.dto.UserDto;
import cn.com.lms.entity.User;
import cn.com.lms.service.TicketService;
import cn.com.lms.service.UserService;

@Service
public class TicketServiceImpl implements TicketService {
	@Resource
	private RedisTemplate<String, String> template;

	@Resource
	private UserService userService;

	/**
	 * 添加ticket
	 */
	@Override
	public String addTicket(String username) {
		String ticket = UUID.randomUUID().toString().replace("-", "");
		ValueOperations<String, String> valueOperations = template
				.opsForValue();
		valueOperations.set(ticket,username,8*60*60,TimeUnit.SECONDS);
		return ticket;
	}

	/**
	 * ticket校验
	 */
	@Override
	public UserDto checkTicket(String ticket) {
		if (StringUtils.isNotBlank(ticket)) {
			ValueOperations<String, String> valueOperations = template
					.opsForValue();
			String username = valueOperations.get(ticket);
			User user = userService.getByName(username);
			if (user != null) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUsername(user.getUsername());
				userDto.setNickname(user.getNickname());
				userDto.setDepartment(user.getDepartment());
				userDto.setSchool(user.getSchool());
				userDto.setPhoneNumber(user.getPhoneNumber());
				userDto.setEmail(user.getEmail());
				userDto.setStatus(user.getStatus());
				userDto.setType(user.getType());
				userDto.setCreatetime(user.getCreatetime());
				return userDto;
			}
		}
		return null;
	}

	@Override
	public void delete(String ticket) {
		template.delete(ticket);
	}

}
