package com.frame.authority.dao;

import com.frame.authority.model.User;
import com.frame.basic.dao.IBaseDao;

/**
 * 用户管理
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-18 下午1:42:23
 */
public interface IUserDao extends IBaseDao<User, Integer>{

	/**
	 * 根据用户名查询用户
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-6 下午11:07:26
	 */
	public User selectUserByName(String username);
}
