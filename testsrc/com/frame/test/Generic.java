package com.frame.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.frame.authority.model.SysUser;

/**
 * 泛型类测试
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-17 下午4:42:32
 */
public class Generic<T> extends SupperGeneric<SysUser>{


	//获取父类指定位置泛型参数类型
	public Class getGenericType(int index) {
		/**
		 * 获取父类类型，这边得到的是，如果Generic没有继承，则他的父类是Object
		 * com.frame.test.SupperGeneric
		 */
		Type genType = getClass().getGenericSuperclass();
		
		/**
		 * 判断父类如果没有实现ParameterizedType接口就不是泛型
		 */
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		/**
		 * 获取泛型参数<T,V>代表有两个泛型参数
		 */
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		/**
		 * 获取指定位置泛型，并判断该泛型是否是类
		 */
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	public T getValue(T obj){
		return obj;
	} 
}
