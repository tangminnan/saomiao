package com.saomiao.information.service;

import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
public interface ManagersService {
	
	ManagersDO get(Long mId);
	
	List<ManagersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ManagersDO managers);
	
	int update(ManagersDO managers);
	
	int remove(Long mId);
	
	int batchRemove(Long[] mIds);

	List<UsersDO> selectUserById(Long mid);

	void updateMname(ManagersDO managersDO);
	
	List<ManagersDO> Manlist(Long mid);

	List<ManagersDO> lists();
}
