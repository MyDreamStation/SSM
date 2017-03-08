package com.bjtu.zs.util.enums;

/**
 * @ClassName LoginResult
 * @Description 描述登录结果的枚举类
 * @author 曾双  631710518@qq.com
 * @Date 2017年3月8日15:31:03
 * 
 */
public enum LoginResult {

	/**
	 * OK
	 */
	OK(0),

	/**
	 * invalid input
	 */
	INVALID_INPUT(1),

	/**
	 * invalid verification code
	 */
	INVALID_VERIFICATION_CODE(2),

	/**
	 * user not found
	 */
	USER_NOT_FOUND(3),

	/**
	 * invalid password
	 */
	INVALID_PASSWORD(4),

	/**
	 * user login disabled
	 */
	USER_LOGIN_DISABLED(5),

	/**
	 * group disable
	 */
	GROUP_DISABLED(6);
	
	private int value;

	LoginResult(int value) {
		this.value = value;
	}
	
	 public int value(){
		 return value;
	 }
	
}
