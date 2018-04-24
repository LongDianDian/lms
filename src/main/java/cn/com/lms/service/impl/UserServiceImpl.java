package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.lms.dao.UserDao;
import cn.com.lms.entity.User;
import cn.com.lms.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao ;
	
	@Override
	public User login(String username, String password) {
		if(StringUtils.isNotBlank(username)&& StringUtils.isNotBlank(password)){
			List<User> list = userDao.login(username,password);
			if(null!= list&& list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public User getByName(String username) {
		if(StringUtils.isNotEmpty(username)){
			List<User> list = userDao.getByUserName(username);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

}
