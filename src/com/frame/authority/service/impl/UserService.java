package com.frame.authority.service.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.frame.authority.dao.IUserDao;
import com.frame.authority.model.User;
import com.frame.authority.service.IUserService;
import com.frame.basic.service.impl.BaseService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-18 上午10:11:48
 */
@Service
public class UserService extends BaseService<User, Integer> implements IUserService {

	/**
	 * @param sqlSessionFactory
	 */
	@Autowired
	public UserService(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		this.setDaoClass(IUserDao.class);
	}

	@Override
	public User getUser(Integer id) {
		return getDao().get(id);
	}

}
