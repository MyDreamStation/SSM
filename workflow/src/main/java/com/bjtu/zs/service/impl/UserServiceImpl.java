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
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList=userDao.getAllUser();
		return userList;
	}



	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserByParam(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<User> getUserByParam(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserByParam(user);
	}

}
