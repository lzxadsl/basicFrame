package com.frame.test;

/**
 * 类的创建过程研究
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-17 下午5:41:31
 */
public class ClassCreate {

	static{
		System.out.println("静态变量初始化...");
	}
	{
		System.out.println("非静态变量初始化...");
	}
}
