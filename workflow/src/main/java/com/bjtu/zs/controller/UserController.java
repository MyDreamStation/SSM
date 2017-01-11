package com.bjtu.zs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.UserService;
import com.bjtu.zs.util.QuickReturn;

/**
 * 用于处理和用户相关的操作
 * 
 * @author zengshuang
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 添加用户的测试方法
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(
			@RequestParam(value = "user.userName", defaultValue = "zhangsan") String userName,
			@RequestParam(value = "user.name", defaultValue = "18") int age) {

		return null;
	}

	/**
	 * 获取所有用户的测试方法
	 * 
	 * @return
	 */
	@RequestMapping("/getAllUser")
	@ResponseBody
	public Map<String, Object> getAllUser() {
		try {
			List<User> userList = userService.getAllUser();

			return QuickReturn.mapOk(userList);

		} catch (Exception e) {
			e.printStackTrace();
			return QuickReturn.mapError("服务器异常");
		}
	}

	/**
	 * 通过参数获取用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getUserByParam", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByParam(User user) {
		List<User> userList = userService.getUserByParam(user);
		return QuickReturn.mapOk(userList);
	}

	/**
	 * 编辑用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editUser")
	@ResponseBody
	public Map<String, Object> editUser(User user) {
		try {
			userService.editUser(user);
			return QuickReturn.mapOk("success");
		} catch (Exception e) {
			return QuickReturn.mapError("服务器异常:" + e.getMessage());
		}
	}
}
