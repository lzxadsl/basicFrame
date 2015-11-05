package com.frame.controller.authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	public @ResponseBody List<User> getUser(User user){
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(user.getUsername())){
			params.put("where","and username like '%"+user.getUsername()+"%'");
		}
		User user1 = new User();
		user1.setUsername("aop2");
		user1.setSex("男");
		user1.setNick("admin");
		userService.saveUser(user1);
		return userService.list(params);
	}
}
