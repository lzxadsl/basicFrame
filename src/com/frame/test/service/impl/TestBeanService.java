package com.frame.test.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import com.frame.test.service.ITestBeanService;


public class TestBeanService implements ITestBeanService{

	private BasicDataSource dSource;
	private String username;

	public void setdSource(BasicDataSource dSource) {
		this.dSource = dSource;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void test() {
		System.out.println("你好啊.............."+username);
		ResultSet rs = null;
		try {
			Statement stat = dSource.getConnection().createStatement();
			rs = stat.executeQuery("select * from sys_user");
			while(rs.next()){
				System.out.println(rs.getString(1)+":"+rs.getString(2));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println("不管怎样我都被执行");
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(dSource);
	}

}
