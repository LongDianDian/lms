package cn.com.lms.service;

import cn.com.lms.entity.User;

public interface UserService {
	
	public User login(String username ,String password);

	public User getByName(String username);

	public void update(User user);

}
