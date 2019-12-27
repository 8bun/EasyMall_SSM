package com.wq.service.impl;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wq.dao.UserDao;
import com.wq.domain.User;
import com.wq.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	
	public User findUserByUserName(String username) throws Exception{
		return userDao.findUserByUserName(username);
	}
	
	public void addUser(User user) throws Exception{
		userDao.addUser(user);
	}

	@Override
	public String findUserNameByUserId(Integer user_id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findUserNameByUserId(user_id);
	}
}
