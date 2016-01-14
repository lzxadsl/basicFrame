package com.frame.authority.service;

import com.frame.authority.model.Role;
import com.frame.basic.service.IBaseService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-9-18 上午10:10:38
 */
public interface IRoleService extends IBaseService<Role, Integer>{

	public Role getRole(Integer id);
	
	public void saveRole(Role role);
	
	public void updateRole(Role role);
}
