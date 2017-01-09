package com.bjtu.zs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bjtu.zs.pojo.User;

@Repository
public interface UserDao {

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
	List<User> getUserByParam(User user);

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
