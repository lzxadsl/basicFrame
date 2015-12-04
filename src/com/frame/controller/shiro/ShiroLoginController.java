package com.frame.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.authority.model.Login;


/**
 * 登入控制
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-4 下午1:42:57
 */
@RequestMapping(value="/shiro/*")
@Controller
public class ShiroLoginController {

	@RequestMapping(value="login.htm")
    public String showLoginForm(Model model,@ModelAttribute Login login){
        return "login";
    }
	/**
	 * 登入验证
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-4 下午1:45:38
	 */
	@RequestMapping(value="doLogin.htm")
	public String doLogin(Model model,@ModelAttribute Login login,BindingResult errors){
		UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(),login.getPassword());
		try{
			SecurityUtils.getSubject().login(token);  
		}catch(AuthenticationException e){
			errors.reject( "error.login.generic", "Invalid username or password.  Please try again." );
		}
		if(errors.hasErrors()){
			model.addAttribute("error","用户名或密码有误");
            return showLoginForm(model,login);
        }else{
            return "main";
        }
	}
	/**
	 * 退出登入
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-4 下午2:16:50
	 */
	@RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
}
