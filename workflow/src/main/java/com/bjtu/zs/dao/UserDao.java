package com.bjtu.zs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bjtu.zs.pojo.User;

@Repository
public interface UserDao {

	List<User> getAllUser();

}
