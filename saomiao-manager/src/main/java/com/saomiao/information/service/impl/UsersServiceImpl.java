package com.saomiao.information.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saomiao.information.dao.UsersDao;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.UsersService;



@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao userDao;
	
	@Override
	public UsersDO get(Long uId){
		return userDao.get(uId);
	}
	
	@Override
	public List<UsersDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UsersDO user){
		return userDao.save(user);
	}
	
	@Override
	public int update(UsersDO user){
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

	@Override
	public List<UsersDO> lists() {
		return userDao.lists();
	}

	@Override
	public List<UsersDO> getFileByid(Long uid) {
		return userDao.getFileByid(uid);
	}
	
}
