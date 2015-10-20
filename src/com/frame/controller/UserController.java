package com.frame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-16 上午8:54:55
 */
@Controller
@RequestMapping(value="/user/*")
public class UserController {

	/*@Reference
	IUserService userService;//调用Dubbo暴露的接口

	@RequestMapping(value="getUser.htm")
	public @ResponseBody String getUser(){
		return userService.getUser();
	}*/
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="getUser.htm")
	public @ResponseBody List<User> getUser(){
		return userService.list();
	}
}
