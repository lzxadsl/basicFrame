package com.frame.test;

/**
 * 类的创建过程研究
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-17 下午5:41:31
 */
public class ClassCreate {

	static{//静态方法在类被加载到内存时就会被调用
		System.out.println("静态变量初始化...");
	}
	{//非静态方法只有在实例被创建时才会被调用
		System.out.println("非静态变量初始化...");
	}
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.frame.test.ClassCreate");//将类加载到内存(静态方法在加载到内存时就会执行)
		Class.forName("com.frame.test.ClassCreate").newInstance();//将类加载到内存并创建实例
	}
}
