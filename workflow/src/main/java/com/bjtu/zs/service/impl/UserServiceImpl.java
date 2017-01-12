package com.bjtu.zs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bjtu.zs.dao.UserDao;
import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.UserService;

@Service
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = userDao.getAllUser();
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
//	@Transactional(rollbackFor=Exception.class)
	public void editUser(User user) {
		// TODO Auto-generated method stub
		try {
			userDao.editUser(user);
		} catch (Exception e) {
			// TODO: handle exception(处理异常)
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("事务已回滚");
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> getUserByParam(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserByParam(user);
	}

}
