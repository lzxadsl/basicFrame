package com.frame.test;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-18 下午5:20:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/app-*.xml")
public class RedisTest {
	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	@Test
	public void test(){
		BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps("username");
		//<key类型，对象类型，对象中属性值类型> hset user name "lzx"（分别是这三个的类型）
		BoundHashOperations<String,String,Object> boundHashOperations = redisTemplate.boundHashOps("user");
		Map<String, Object> data = boundHashOperations.entries();
		System.out.println(data);
		boundValueOperations.set("lsl");
		System.out.println("-------:"+boundValueOperations.get());
	}
}
