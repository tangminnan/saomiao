package com.saomiao.information.service;

import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
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

	R selectUserById(Long mid);
	
	R selectUserByMid(Long mid);

	int updateMname(ManagersDO managersDO);
	
	List<ManagersDO> Manlist(Long mid);

	List<ManagersDO> lists();

	ManagersDO getIdByname(String username);

	List<ManagersDO> likelist(Map<String, Object> map);

	ManagersDO selectUsername(Long mid);

	boolean exit(Map<String, Object> params);


}
