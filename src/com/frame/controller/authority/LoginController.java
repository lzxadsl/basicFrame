package com.frame.controller.authority;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping(value="login.htm")
	public String login(String username,ModelMap map,HttpServletRequest request){
		map.addAttribute("msg","你好啊! "+username);
		request.getSession().setAttribute("username",username);
		return "/basic/iframe1";
	}
}
