package com.bjtu.zs.service;

import com.bjtu.zs.pojo.User;

public interface UserService {

	/**
	 * 通过登录id获取用户信息
	 * 
	 * @param loginId
	 * @return
	 */
	User getUserByLoginId(String loginId);
}
