package com.frame.authority.filter;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * 访问拦截
 * @author lizhixian
 * @version 1.0
 * @date 2015-6-26 下午4:11:19
 */
public class AuthorityFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		//获取登入成功时保存的session对象
		//Object object = SMVC_SessionContext.getInstance().getObject(session);
		Object object = session.getAttribute("user");
		//验证参数是否合法
		String result = vaildParams(httpRequest);
		
		if(StringUtils.isNotBlank(result)){
			//参数非法，统一提示
			request.setAttribute("error",result);
			httpResponse.sendRedirect("error.jsp");
			return;
		}
		
		Principal principal = httpRequest.getUserPrincipal();
		if(httpRequest.getServletPath().contains("loginPage.htm") || 
				httpRequest.getServletPath().contains("login.htm") || 
				httpRequest.getServletPath().contains("cas_login.htm")){
			//已经登陆直接进首页
			if(principal != null && !httpRequest.getServletPath().contains("cas_login.htm")){
				request.getRequestDispatcher("/cas_login.htm").forward(request,response);
				return;
			}else {	
				chain.doFilter(request, response);
				return;	
			}	
		}
		else if(object == null){
			boolean isAjax = isAjaxRequest(httpRequest);
			if(isAjax){
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"您已经太长时间没有操作，清刷新页面！");
			}
			httpResponse.sendRedirect("/smvc/loginPage.htm");
			return;
		}
		chain.doFilter(request, response);
		return;
	}

	/**
	 * 是否是Ajax请求
	 */
	public boolean isAjaxRequest(HttpServletRequest httpRequest){
		return httpRequest.getRequestURI().endsWith("api");
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	/**
	 * 验证参数是否带有非法关键字
	 * @author lizhixian
	 * @create 2015-4-20 下午1:31:11
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String vaildParams(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();  
        String flag = "";
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];
                if (paramValue.length() != 0 && !isValid(paramValue.toLowerCase())) {  
                	flag = "参数非法";
                	System.out.println(paramName+"===xx==="+paramValue);
                	break;
                }  
            }  
        }
        return flag;
    }
	
	/**
	 * 参数校验 
	 * @param str 
	 */  
	public static boolean isValid(String p) {  
		if(p.indexOf("'") >= 0){
			if(p.indexOf("delete") >= 0 || p.indexOf("ASCII") >= 0  
			        || p.indexOf("update") >= 0 || p.indexOf("select") >= 0 || p.indexOf("substr(") >= 0  
			        || p.indexOf("count(") >= 0 || p.indexOf(" or ") >= 0  
			        || p.indexOf(" and ") >= 0 || p.indexOf("drop") >= 0  
			        || p.indexOf("execute") >= 0 || p.indexOf("exec") >= 0  
			        || p.indexOf("truncate") >= 0 || p.indexOf("into") >= 0  
			        || p.indexOf("declare") >= 0 || p.indexOf("master") >= 0  
			        || p.indexOf("--") >= 0){
				 return false; 
			}
		}
		if(p.indexOf("<script") >= 0 || p.indexOf("<input") >= 0){
			 return false; 
		}
	    return true;  
	}
	
	/**
	 * 判断是否重复提交
	 * @author lzx
	 * @param token
	 * @param session
	 * @return 判断结果
	 */
	public boolean putToken(String token,HttpSession session){
		//清除掉半个小时之前的token
		Object hz_token = session.getAttribute("hz_token");
		Map<String, String> t_maps = new HashMap<String, String>();
		/***
		 * 清除半个小时之前的token
		 */
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		cl.add(Calendar.MINUTE, -30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datestr = sdf.format(cl.getTime());
		if(hz_token != null){
			@SuppressWarnings("unchecked")
			Map<String, String> t_m = (Map<String, String>)hz_token;
			for (String key : t_m.keySet()) {
				if(datestr.compareTo(t_m.get(key)) <= 0){
					t_maps.put(key, t_m.get(key));
				}
			}
		}
		/***
		 * 判断是否重复提交token
		 */
		boolean rs = false;
		if(t_maps.keySet().contains(token)){
			rs = true;
		}else{
			t_maps.put(token,sdf.format(new Date()));
		}
		session.setAttribute("hz_token", t_maps);
		return rs;
	}

}
