package com.frame.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		String json = "{\"act\":\"\",\"f\":\"\",\"id\":\"\",\"moner\":\"\",\"time\":\"\",\"act_no\":\"\",\"name\":\"\",\"statue\":\"\",\"age\":\"\"}";
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
		Set<String> k1 = map.keySet();
		for(String k:map.keySet()){}
		System.out.println(map.keySet());
		System.out.println("cebucdefg".compareTo("ceb"));
		array();
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(123);
		list = list1;
		System.out.println(list.size());
	}
	public static void array(){
		String[] ary = new String[10];
		double[] i = new double[5];
		ary[0] = "1";
		ary[5] = "2";
		String a="aa";
		System.out.println(i[1]);
		System.out.println("数组长度："+ary.length);
		String s1 = "a";
		System.out.println(s1.hashCode());
		String s2 = s1 + "b";
		System.out.println(s2.hashCode());
		String s3 = "a" + "b";
		System.out.println(s3==s2);
		System.out.println("ab".hashCode());
		float f = 1f;
		Float ff = 1f;
		System.out.println(f+ff);
		byte[] b = new byte[100];
		b[0] = 1;
		b[1] = 2;
		b[2] = 2;
		b[4] = 3;
		System.out.println(getRealLength(b));
	}
	
	public static int getRealLength(byte[] a){  
        int i=0;  
        for(;i<a.length;i++){  
          if(a[i]=='\0')  
              break;  
        }  
        return i;  
    }  
}
