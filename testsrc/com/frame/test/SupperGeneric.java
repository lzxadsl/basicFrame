package com.frame.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-17 下午5:04:27
 */
public class SupperGeneric<T> extends Consumer{

	
	//获取泛型类型
	public Class getGenericType(int index) {
		 Type genType = getClass().getGenericSuperclass();
		 getClass().getGenericInterfaces();
		 //判断如果没有实现ParameterizedType接口就不是泛型
		 if (!(genType instanceof ParameterizedType)) {
			 return Object.class;
		 }
		 Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		 if (index >= params.length || index < 0) {
			 throw new RuntimeException("Index outof bounds");
		 }
		 if (!(params[index] instanceof Class)) {
			 return Object.class;
		 }
		 return (Class) params[index];
	}
}
