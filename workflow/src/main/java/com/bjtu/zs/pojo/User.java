package com.bjtu.zs.pojo;

import java.util.Date;

public class User {

	// 用户id
	private int id;

	// 用户登录id
	private String loginId;

	// 用户名
	private String username;

	// 用户密码
	private String password;

	// 邮件地址
	private String mail;

	// 手机号码
	private String telephone;

	// 注册时间
	private Date registerDate;

	// 最后登录时间
	private Date lastLoginDate;

	// 最后登录ip
	private String lastLoginIp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", username=" + username + ", password=" + password
				+ ", mail=" + mail + ", telephone=" + telephone + ", registerDate=" + registerDate + ", lastLoginDate="
				+ lastLoginDate + ", lastLoginIp=" + lastLoginIp + "]";
	}

}
