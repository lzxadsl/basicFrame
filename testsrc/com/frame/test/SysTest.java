package com.frame.test;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;
import com.frame.basic.model.PageData;


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
		PageData page = new PageData();
		List<User> list = userService.listPage(page);
		System.out.println(page.getTotalSize());
		for(User u : list){
			System.out.println(u.getUsername());
		}
	}

}