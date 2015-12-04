package com.frame.authority.model;

/**
 * 登入实体
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-4 下午2:20:46
 */
public class Login {

	private String username;//用户名
	private String password;//密码
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
