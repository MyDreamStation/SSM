package com.bjtu.zs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.zs.pojo.User;
import com.bjtu.zs.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 添加用户测试方法
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> addUser(String userName){
		Map<String,Object> map=new HashMap<>();
		
		map.put("success", "true");
		return map;
	}
	
	/**
	 * 获取所有用户的测试类
	 * 
	 * @return
	 */
	@RequestMapping("/getAllUser")
	@ResponseBody
	public Map<String,Object> getAllUser(){
		try{
			List<User> userList=userService.getAllUser();
			
			Map<String,Object> map=new HashMap<>();
			map.put("Alluser", userList);
			return map;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis.xml");
//		UserService userService=ctx.getBean(UserService.class);
//		userService.addUser("zhnagsan");
		UserController userController=ctx.getBean(UserController.class);
		
		userController.addUser("zhangsan");
	}
}
