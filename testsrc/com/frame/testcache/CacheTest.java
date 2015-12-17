package com.frame.testcache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-16 下午4:19:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/app-*.xml")
public class CacheTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void test(){
		User user = userService.getUserByName("lzx");
		User user1 = userService.getUserByName("lsl");
		//User user = userService.get(1);
		System.out.println(user.getUsername());
		System.out.println(user1.getUsername());
	}
}
