package com.frame.test;

import java.lang.reflect.Method;
import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-11 下午4:39:37
 */
public class MainTest {

	public static void generTest() throws NoSuchMethodException, SecurityException{
		/**
		 * 研究泛型
		 */
		Generic<User> gen = new Generic<User>();
		User u = new User();
		u.setUsername("lzx");
		Method method = gen.getGenericType(0).getMethod("setUserId",new Class[]{String.class});
		System.out.println(method);
		System.out.println("fhz:"+gen.getValue(u).getUsername());
		SupperGeneric su = new SupperGeneric();
		Method[] method1 = su.getGenericType(0).getDeclaredMethods();
		System.out.println(method1);
		
		/**
		 * 研究类的创建
		 */
		Class cl = ClassCreate.class;
		cl.getClass();
		try {
			Class.forName("com.frame.test.ClassCreate");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(User.class instanceof Class);
		
		Class<?>[] interfaces = new Class[]{IUserService.class}; 
		ClassLoader cload = IUserService.class.getClassLoader();
		try {
			System.out.println(interfaces+"/n"+cload.loadClass("com.frame.test.ClassCreate"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		/*Map<String,Object> map =  new HashMap<String, Object>();
		map.put("TITLE","三星");
		map.put("ID",10010);
		map.put("PRICE",999.9);
		OrderForm order = (OrderForm) BeanUtil.convert2Bean(map,new OrderForm());
		System.out.println(order.getTitle()+":"+order.getPrice());
		
		String [] ar = {"lzx","lcx"};
		System.out.println(StringUtils.join(ar));
		String html = "<input></input>";
		System.out.println(StringUtils.escapeHTML(html));*/
		
		
		StringBuilder pageSql = new StringBuilder();  
	    pageSql.append("select * from aa where 1=1");  
	    pageSql.append(" limit " + 10 + " offset " + 20); 
	    System.out.println(pageSql.toString());
	    String dialect = "Postgresql";
	    System.out.println(dialect.toUpperCase());
	}
}
