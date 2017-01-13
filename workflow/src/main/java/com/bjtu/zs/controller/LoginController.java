package com.bjtu.zs.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.UserService;
import com.bjtu.zs.util.QuickReturn;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 用户登录控制器
	 * 
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpSession session, String loginId, String password) {
		User user = null;
		user = userService.getUserByLoginId(loginId);
		if (user != null) {
			System.out.println(user.toString());
			if (user.getPassword().equals(password)) {
				return QuickReturn.mapOk("success");
			}
		}
		return QuickReturn.mapError("failure");
	}
}
