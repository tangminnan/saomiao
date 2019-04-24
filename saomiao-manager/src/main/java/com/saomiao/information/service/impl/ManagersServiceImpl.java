package com.saomiao.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.saomiao.information.dao.ManagersDao;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.service.ManagersService;



@Service
public class ManagersServiceImpl implements ManagersService {
	@Autowired
	private ManagersDao managersDao;
	
	@Override
	public ManagersDO get(Long mId){
		return managersDao.get(mId);
	}
	
	@Override
	public List<ManagersDO> list(Map<String, Object> map){
		return managersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return managersDao.count(map);
	}
	
	@Override
	public int save(ManagersDO managers){
		return managersDao.save(managers);
	}
	
	@Override
	public int update(ManagersDO managers){
		return managersDao.update(managers);
	}
	
	@Override
	public int remove(Long mId){
		return managersDao.remove(mId);
	}
	
	@Override
	public int batchRemove(Long[] mIds){
		return managersDao.batchRemove(mIds);
	}
	
}
