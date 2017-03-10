package com.bjtu.zs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * @param loginId 账号（可以是邮箱地址）
	 * @param password 密码(密文密码)
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(HttpSession session, String loginId, String password) {
		Map<String,String> loginStatus = new HashMap<>();
		
		User user = null;
		try {
			user = userService.getUserByLoginId(loginId);
		} catch (Exception e) {
			e.printStackTrace();
			loginStatus.put("status", "-1");
			loginStatus.put("message", "服务器错误:"+e.getMessage());
			return loginStatus;
			
		}
		
		
		if (user != null) {
			if (user.getPassword().equals(password)) {
				session.setAttribute("user", user);
				loginStatus.put("status", "1");
				loginStatus.put("message", "登录成功!");
			}else{
				loginStatus.put("status", "0");
				loginStatus.put("message", "密码错误!");
			}
		}else{
			loginStatus.put("status", "0");
			loginStatus.put("message", "用户不存在!");
		}
		
		return loginStatus;
	}
	//TODO 会出现无法响应回前台js的问题
	/**
	 * 用户注销控制器
	 * 
	 * @param session
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String,Object> logout(HttpSession session){
		session.removeAttribute("user");
		return QuickReturn.mapOk("注销成功！");
	}
}
