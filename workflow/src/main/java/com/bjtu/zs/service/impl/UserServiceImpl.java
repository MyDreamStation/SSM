package com.bjtu.zs.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
	public void addUser(String userName) {
		// TODO Auto-generated method stub
		System.out.println("add User"+userName);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList=userDao.getAllUser();
		return userList;
	}

}
