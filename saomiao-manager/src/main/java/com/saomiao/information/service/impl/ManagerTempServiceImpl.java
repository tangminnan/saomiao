package com.saomiao.information.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saomiao.information.dao.ManagerTempDao;
import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.service.ManagerTempService;



@Service
public class ManagerTempServiceImpl implements ManagerTempService {
	@Autowired
	private ManagerTempDao managerTempDao;
	
	@Override
	public ManagerTempDO get(Integer mid){
		return managerTempDao.get(mid);
	}
	
	@Override
	public List<ManagerTempDO> list(Map<String, Object> map){
		return managerTempDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return managerTempDao.count(map);
	}
	
	@Override
	public int save(ManagerTempDO managerTemp){
		return managerTempDao.save(managerTemp);
	}
	
	@Override
	public int update(ManagerTempDO managerTemp){
		return managerTempDao.update(managerTemp);
	}
	
	@Override
	public int remove(Integer mid){
		return managerTempDao.remove(mid);
	}
	
	@Override
	public int batchRemove(Integer[] mids){
		return managerTempDao.batchRemove(mids);
	}

	@Override
	public ManagersDO selectNameByid(Integer mid) {
		return managerTempDao.selectNameByid(mid);
	}
	
}
