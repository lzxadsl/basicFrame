package com.frame.test;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dubbo.provide.user.service.IUserService;


public class Consumer {

	public void test() throws IOException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"config/spring/app-dubbo-consumer.xml" });
		context.start();
		IUserService demoService =  (IUserService) context.getBean("userService");
		String hello = demoService.getUser();
		System.out.println(hello);
		System.in.read();
	}
	public static void main(String[] args) throws Exception {
		new Consumer().test();
	}

}