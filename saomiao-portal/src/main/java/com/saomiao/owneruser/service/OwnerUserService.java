package com.saomiao.owneruser.service;

import java.util.List;
import java.util.Map;

import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.domain.OwnerUserDO;


public interface OwnerUserService {
	ManagersDO get(Long id);
	
	ManagersDO getbyname(String username);

	List<ManagersDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(ManagersDO user);

	int update(ManagersDO user);

	int remove(Long userId);

	boolean exit(Map<String, Object> params);

}
