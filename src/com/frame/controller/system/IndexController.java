package com.frame.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * @author LiZhiXian
 * @version 1.0
 * @date 2016-1-13 下午3:52:08
 */
@Controller
@RequestMapping(value="/index/*")
public class IndexController {

	/**
	 * 跳转到首页
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2016-1-13 下午3:54:33
	 */
	@RequestMapping(value="index.htm")
	public String index(){
		return "/index";
	}
}
