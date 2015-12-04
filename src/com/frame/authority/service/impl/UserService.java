package com.frame.authority.service.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.frame.authority.dao.IUserDao;
import com.frame.authority.model.SysUser;
import com.frame.authority.service.IUserService;
import com.frame.basic.service.impl.BaseService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-18 上午10:11:48
 */
@Service
public class UserService extends BaseService<SysUser, Integer> implements IUserService {

	/**
	 * @param sqlSessionFactory
	 */
	@Autowired
	public UserService(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		this.setDaoClass(IUserDao.class);
	}

	@Override
	public SysUser getUser(Integer id) {
		return getDao().get(id);
	}

	@Override
	@Transactional
	public void saveUser(SysUser user) {
		getDao().insert(user);
	}

	@Override
	@Transactional
	public void updateUser(SysUser user){
		System.out.println("执行更新操作...........");
		getDao().update(user);
		//getDao().insert(user);
		
	}

	@Override
	@Transactional
	public void transation(SysUser user,String newName){
		getDao().insert(user);
		user.setUsername(newName);
		//updateUser(user);
		//throw new Error("...........");
	}
}
