package com.frame.testcache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;
import com.frame.ehcache.ICacheUserService;

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
		System.out.println(user.getUsername());
		System.out.println(user1.getUsername());
	}
	
	@Autowired
	private ICacheUserService cacheUserService;
	
	/**
	 * 测试使用spring缓存注解
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-17 上午9:50:05
	 */
	@Test
	public void testSpringCache(){
		cacheUserService.getUser(1);
		cacheUserService.addUser(1);
		cacheUserService.getUser(1);
	}
	
	/**
	 * 测试使用ehcache缓存注解
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-17 上午9:50:05
	 */
	@Test
	public void testEHCache(){
		cacheUserService.list(1);
		cacheUserService.list(2);
		cacheUserService.update(1);
		cacheUserService.list(1);
		cacheUserService.list(2);
	}
}
