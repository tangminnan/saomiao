package com.saomiao.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.saomiao.information.dao.UserDao1;
import com.saomiao.information.domain.UserDO1;
import com.saomiao.information.service.UserService1;



@Service
public class UserServiceImpl1 implements UserService1 {
	@Autowired
	private UserDao1 userDao;
	
	@Override
	public UserDO1 get(Long uId){
		return userDao.get(uId);
	}
	
	@Override
	public List<UserDO1> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO1 user){
		return userDao.save(user);
	}
	
	@Override
	public int update(UserDO1 user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Long uId){
		return userDao.remove(uId);
	}
	
	@Override
	public int batchRemove(Long[] uIds){
		return userDao.batchRemove(uIds);
	}
	
}
