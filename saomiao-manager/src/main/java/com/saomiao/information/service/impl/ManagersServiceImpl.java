package com.saomiao.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.saomiao.information.dao.ManagersDao;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
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
	public int remove(Long mid){
		return managersDao.remove(mid);
	}
	
	@Override
	public int batchRemove(Long[] mIds){
		return managersDao.batchRemove(mIds);
	}

	@Override
	public List<UsersDO> selectUserById(Long mid) {
		return managersDao.selectUserById(mid);
	}

	@Override
	public List<ManagersDO> Manlist(Long mid) {
		return managersDao.Manlist(mid);
	}

	@Override
	public void updateMname(ManagersDO managersDO) {
		
		managersDao.updateMname(managersDO);
	}

	@Override
	public List<ManagersDO> lists() {
		return managersDao.lists();
	}
	
}
