package com.saomiao.owneruser.service;

import java.util.List;
import java.util.Map;

import com.saomiao.owneruser.domain.OwnerUserDO;


public interface OwnerUserService {
	OwnerUserDO get(Long id);
	
	OwnerUserDO getbyname(String username);

	List<OwnerUserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(OwnerUserDO user);

	int update(OwnerUserDO user);

	int remove(Long userId);

	boolean exit(Map<String, Object> params);

}
