package com.frame.authority.quartz;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author lzx
 * @version 1.0
 * @create_date 下午9:24:05
 */
public class QuartzThread extends Thread{

	private final TestQuartz testQuartz;
	
	private final int i;
	public QuartzThread(TestQuartz testQuartz,int i){
		this.i = i;
		this.testQuartz = testQuartz;
	}
	@Override
	public void run() {
		if(i%2==0){
			//testQuartz.method2(i);
			System.out.println("2222222222222222222222222222222222");
		}else{
			//testQuartz.method1(i);
			System.out.println("11111111111111111111111111111111111");
		}
	}
}
