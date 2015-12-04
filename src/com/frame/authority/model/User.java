package com.frame.authority.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-4 下午4:11:02
 */
public class User {

	private String id;//主键
	private String username;//用户名
	private String password;//密码
	private Set<Role> roleSet = new HashSet<Role>();//角色
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Set<Role> getRoleSet() {
		return roleSet;
	}
	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
}
