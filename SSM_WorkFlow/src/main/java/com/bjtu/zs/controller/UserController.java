package com.bjtu.zs.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.zs.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> addUser(String userName){
		userService.addUser(userName);
		
		return null;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserService userService=ctx.getBean(UserService.class);
//		userService.addUser("zhnagsan");
		UserController userController=ctx.getBean(UserController.class);
		
		userController.addUser("zhangsan");
	}
}
