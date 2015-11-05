package com.frame.test;

import java.util.List;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;
import com.frame.basic.model.PageData;
import com.frame.system.service.IProducerService;


/**
 * 系统功能测试
 * @author lizhixian
 * @version 1.0
 * @create_date 2015-9-11 上午11:12:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/app-*.xml")
public class SysTest {
	@Autowired
	private IUserService userService;
	
	@Test
	public void test() {
		/*PageData page = new PageData();
		List<User> list = userService.listPage(page);
		userService.getUser(3);
		System.out.println(page.getTotalSize());
		for(User u : list){
			System.out.println(u.getUsername());
		}*/
		User user = new User();
		user.setUsername("aop2");
		user.setSex("男");
		user.setNick("admin");
		userService.saveUser(user);
		//userService.update(user);
	}
	
	@Autowired
	private IProducerService pService;
	@Autowired
	Destination destination;
	@Test
	public void testmq() throws InterruptedException{
		for (int i=0; i<2; i++) {
			pService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
        }
		Thread.sleep(2000);
		pService.receiveMessage(destination);
	}

}
