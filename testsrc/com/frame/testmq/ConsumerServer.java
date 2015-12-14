package com.frame.testmq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-11 下午4:13:24
 */
@Service
public class ConsumerServer {

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/app-activeMQ.xml");
		while (true) {
			
		}
	}
}
