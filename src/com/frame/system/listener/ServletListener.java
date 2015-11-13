package com.frame.system.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.frame.system.constant.WhiteListConstant;

/**
 * tomcate启动监听器
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-13 上午11:29:31
 */
public class ServletListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce){
		
	}
	/**
	 * 服务器启动时初始化系统信息
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass().getResourceAsStream("/power.properties");
			prop.load(in);
			String strList = prop.get("whiteList") == null ? "" : String.valueOf(prop.get("whiteList"));
			List<String> wList = Arrays.asList(strList.split(";"));
			WhiteListConstant.getInstance().setWhiteList(wList);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("------------白名单初始化完毕--------------");
	}
}
