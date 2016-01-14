package com.frame.authority.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.authority.model.Role;
import com.frame.authority.model.User;
import com.frame.authority.service.IRoleService;
import com.frame.authority.service.IUserService;

/**
 * 
 * @author lzx
 * @version 1.0
 * @create_date 下午9:07:43
 */
@Service
public class TestQuartz {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	@Transactional
	public void method1(int i){
		System.out.println("-------------------------------方法1请求角色更新---------------------------------");
		Role role = new Role();
		role.setId(3);
		role.setName("vip"+i);
		roleService.updateRole(role);
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------方法1请求用户更新---------------------------------");
		User user = new User();
		user.setId(1);
		user.setUsername("lzx"+i);
		userService.updateUser(user);
		System.out.println("-------------------------------方法1执行完毕---------------------------------");
	}
	
	@Transactional
	public void method2(int i){
		System.out.println("-------------------------------方法2请求用户更新---------------------------------");
		User user = new User();
		user.setId(1);
		user.setUsername("lzx"+i);
		userService.updateUser(user);
		System.out.println("-------------------------------方法2请求角色更新---------------------------------");
		Role role = new Role();
		role.setId(3);
		role.setName("vip"+i);
		roleService.updateRole(role);
		System.out.println("-------------------------------方法2执行完毕---------------------------------");
	}
}
