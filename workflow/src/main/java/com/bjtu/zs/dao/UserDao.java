package com.bjtu.zs.dao;

import org.springframework.stereotype.Repository;

import com.bjtu.zs.pojo.User;

@Repository
public interface UserDao {

	public User getUserByLoginId(String loginId);

}
