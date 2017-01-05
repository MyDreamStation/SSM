package com.bjtu.zs.service;

import java.util.List;

import com.bjtu.zs.pojo.User;


public interface UserService {

	public void addUser(String userName);
	
	public List<User> getAllUser();
}
