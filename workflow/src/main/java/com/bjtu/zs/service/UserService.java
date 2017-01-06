package com.bjtu.zs.service;

import java.util.List;

import com.bjtu.zs.pojo.User;


public interface UserService {
	
	
	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<User> getAllUser();
	
	/**
	 * 通过id获取用户
	 * 
	 * @return
	 */
	User getUserByParam();
	
	/**
	 * 增加用户
	 * 
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	void deleteUserByParam(User user);
	
	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	void editUser(User user);
	
	
}
