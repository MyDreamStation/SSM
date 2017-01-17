package com.bjtu.zs.util;

public class LoginJudgement {
	
	/**
	 * 判断是否为邮箱格式的工具类
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		boolean flag = false;
		String rule = "\\w+\\x40\\w+\\x2e\\w+";
		if (email.matches(rule)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 正则表达式验证手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile){
		boolean flag = false;
		String rule = "^[1][3,4,5,7,8][0-9]{9}$";
		if (mobile.matches(rule)) {
			flag = true;
		}
		return flag;
	}

}
