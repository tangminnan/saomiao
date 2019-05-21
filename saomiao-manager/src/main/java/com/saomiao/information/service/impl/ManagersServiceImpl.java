package com.saomiao.information.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saomiao.common.utils.R;
import com.saomiao.information.dao.ManagersDao;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.ManagersService;
import com.saomiao.system.service.UserService;



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

	@Autowired
	private UserService userService;
	
	@Override
	public R selectUserById(Long mid) {
		List<UsersDO> userList =  managersDao.selectUserById(mid);
		if(userList != null && !userList.isEmpty()){
			R r = new R();
			r.put("code", 2);
			return r; 
		}else{
			ManagersDO  managers = selectUsername(mid);
			userService.removeUser(managers.getUsername());
			remove(mid);
			return R.ok();
		}
	}

	@Override
	public List<ManagersDO> Manlist(Long mid) {
		return managersDao.Manlist(mid);
	}

	@Override
	public int updateMname(ManagersDO managersDO) {
		
		return managersDao.updateMname(managersDO);
	}

	@Override
	public List<ManagersDO> lists() {
		return managersDao.lists();
	}

	@Override
	public ManagersDO getIdByname(String username) {
		return managersDao.getIdByname(username);
	}

	@Override
	public List<ManagersDO> likelist(Map<String, Object> map) {
		return managersDao.likelist(map);
	}

	@Override
	public ManagersDO selectUsername(Long mid) {
		return managersDao.selectUsername(mid);
	}
	
}
