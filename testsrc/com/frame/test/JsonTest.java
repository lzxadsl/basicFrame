package com.frame.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;

import com.frame.basic.utils.GsonUtils;

/**
 * 
 * @author lzx
 * @version 1.0
 * @create_date 下午9:26:29
 */
public class JsonTest {

	public static void json2Obj(){
		String json = "{\"id\":\"0\",\"moner\":\"0\",\"time\":null,\"act_no\":\"1\",\"name\":\"\",\"statue\":\"\"}";
		Account account = GsonUtils.fromJson(json,Account.class);
		System.out.println(account);
	}
	
	public static void main(String[] args) {
		json2Obj();
		int i = 128;
		Integer i2 = 128;
		Integer i3 = new Integer(128);
		//Integer会自动拆箱为int
		System.out.println(i == i2);//true
		System.out.println(i == i3);//true
		System.out.println(i2 == i3);//false
		System.out.println(Account.getAccount().hashCode());
		System.out.println(new Account().hashCode());
		List<Integer> list = new ArrayList<Integer>();
		list.add(i);
		System.out.println(list.get(0));
	}
}
