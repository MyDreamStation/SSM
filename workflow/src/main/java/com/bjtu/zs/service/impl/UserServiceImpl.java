package com.bjtu.zs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjtu.zs.dao.UserDao;
import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByLoginId(String loginId) {
		User user = null;
		user = userDao.getUserByLoginId(loginId);
		return user;
	}

}
