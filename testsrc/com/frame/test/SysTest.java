package com.frame.test;

import javax.jms.Destination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.model.Role;
import com.frame.authority.model.SysUser;
import com.frame.authority.model.User;
import com.frame.authority.quartz.QuartzThread;
import com.frame.authority.quartz.TestQuartz;
import com.frame.authority.service.IRoleService;
import com.frame.authority.service.IUserService;
import com.frame.system.service.IProducerService;
import com.frame.test.service.ITestBeanService;


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
	@Autowired
	private ITestBeanService testBean;
	@Autowired
	private TestQuartz testQuartz;
	@Autowired
	private IRoleService roleService;
	@Test
	public void test() {
		/*PageData page = new PageData();
		List<User> list = userService.listPage(page);
		userService.getUser(3);
		System.out.println(page.getTotalSize());
		for(User u : list){
			System.out.println(u.getUsername());
		}*/
		
		//testBean.test();
		//userService.saveUser(user);
		
		Thread thread1 = new QuartzThread(testQuartz,1);
		Thread thread2 = new QuartzThread(testQuartz,2);
		thread1.start();
		thread2.start();
		/*Role role = new Role();
		role.setId(3);
		role.setName("vip123456");
		roleService.updateRole(role);*/
		//userService.transation(user, "更新名称");
		//User user = userService.get(1);
		//while(true){}
	}
	
	/*@Autowired
	private IProducerService pService;
	@Autowired
	Destination destination;
	@Test
	public void testmq() throws InterruptedException{
		for (int i=0; i<2; i++) {
			Thread.sleep(5000);
			pService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
        }
	}*/
}
