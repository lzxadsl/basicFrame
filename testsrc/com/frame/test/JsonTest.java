package com.frame.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;

import com.alibaba.fastjson.JSONObject;
import com.frame.basic.utils.GsonUtils;

/**
 * 
 * @author lzx
 * @version 1.0
 * @create_date 下午9:26:29
 */
public class JsonTest {

	public static void json2Obj(){
		String json = "{\"id\":\"\",\"moner\":\"\",\"time\":\"\",\"act_no\":\"1\",\"name\":\"\",\"statue\":\"\",\"age\":null}";
		/*Account account = GsonUtils.fromJson(json,Account.class);
		System.out.println(account);
		Account acc =  new Account();
		System.out.println(GsonUtils.toJson(acc));*/
		
		Account account1 = JSONObject.parseObject(json, Account.class);
		System.out.println(account1);
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
		//System.out.println(Account.getAccount().hashCode());
		System.out.println(new Account().hashCode());
		List<Integer> list = new ArrayList<Integer>();
		list.add(i);
		System.out.println(list.get(0));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "lzx");
		map.put("sex", "nan");
		System.out.println(map.keySet());
		System.out.println("cebucdefg".compareTo("ceb"));
	}
}
