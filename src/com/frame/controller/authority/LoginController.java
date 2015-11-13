package com.frame.controller.authority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登入管理
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-6-26 下午3:42:14
 */
@Controller
@RequestMapping(value="/*")
public class LoginController {

	public String login(){
		return "";
	}
}
