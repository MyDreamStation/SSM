package com.bjtu.zs.service.impl;

import org.springframework.stereotype.Service;

import com.bjtu.zs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void addUser(String userName) {
		// TODO Auto-generated method stub
		System.out.println("add User"+userName);
	}

}
