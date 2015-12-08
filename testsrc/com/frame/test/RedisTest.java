package com.frame.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
	@Test
	public void set(){
		Set<Integer> result = new HashSet<Integer>();
		Set<Integer> set1 = new HashSet<Integer>(){{
			add(1);
			add(3);
			add(5);
		}};
		
		Set<Integer> set2 = new HashSet<Integer>(){{
			add(1);
			add(2);
			add(3);
		}};

		result.addAll(set1);
		result.addAll(set2);
		System.out.println("并集："+result);	
		set1.retainAll(set2);
		System.out.println("交集："+set1);
		result.removeAll(set1);
		System.out.println("同时不存在："+result);
		
		/*result.addAll(set1);
		result.removeAll(set2);
		System.out.println("交集："+result);*/
	}
	public static void main(String[] args) {
		RedisTest t = new RedisTest();
		t.set();
	}
}
