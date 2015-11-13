package com.frame.system.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单全局常量
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-13 下午1:31:34
 */
public class WhiteListConstant {
	
	private final static WhiteListConstant wlc = new WhiteListConstant();
	
	private WhiteListConstant(){
		super();
	}
	//白名单列表
	private List<String> whiteList = new ArrayList<String>(); 
	/**
	 * 获取实例
	 */
	public static WhiteListConstant getInstance(){
		return wlc;
	}
	/**
	 * 判断该访问地址是否在白名单中
	 * @param path 访问地址路径
	 */
	public boolean isExist(String path){
		boolean exist = false;
		if(whiteList.contains(path)){
			exist = true;
		}
		return exist;
	}
	/**
	 * 获取白名单列表
	 */
	public List<String> getWhiteList() {
		return whiteList;
	}
	/**
	 * 设置白名单列表
	 */
	public void setWhiteList(List<String> whiteList) {
		if(this.whiteList.size() < 1){
			this.whiteList = whiteList;
		}
	}
}
