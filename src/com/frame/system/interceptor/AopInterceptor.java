package com.frame.system.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * aop切面拦截器
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-7-15 上午11:27:06
 */
@Aspect
@Service
public class AopInterceptor {

	/**
	 * 定义一个切入点
	 * public 公有方法
	 * 第一星代表任何返回值类型
	 * 搜索位置com.lzx.user.service.impl 下所以服务类下的所以方法,impl后第一个点星是到类，第二个.*(..)类下所有方法  
	 * public * com.lzx.user.service.impl.*.*(..) 匹配com.lzx.user.service.impl下所有类的所以公有方法
	 * *[所有类型返回值] com.frame.controller.[所有包（什么都不写代表包）].*[所有类].*(..)[类下所有方法]
	 */
	@SuppressWarnings("unused")
	//@Pointcut("execution(* com.frame.controller..*.*(..))") 
	@Pointcut("execution(* com.frame.basic.service.impl.*.*(..))") 
    private void log(){}
	
  /*  //带参数  
    @Before("log() && args(name)")  
    public void before(String name){  
        System.out.println(name);  
        System.out.println("前置通知");  
    } */
    
    @Before("log()")//等同于@Before("execution(public * com.lzx.user.service.impl.*.*(..))")
    public void addStartLog(JoinPoint joinPoint){
    	System.out.println("前置：添加开始日志");  
    }  
      
    @After("log()")  
    public void addEndLog(){  
        System.out.println("后置：添加结束日志");  
    }  
      
    @AfterReturning("log()")  
    public void afterReturn(){  
        System.out.println("返回后通知");  
    } 
    
    @AfterThrowing("log()")  
    public void afterThrow(){  
        System.out.println("抛出异常后通知");  
    }  
      
    /*@Around("log()")  
    public Object around(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("进入环绕通知");  
        Object object = pjp.proceed();//执行该方法  
        System.out.println("退出方法");  
        return object;  
    }  */
}
