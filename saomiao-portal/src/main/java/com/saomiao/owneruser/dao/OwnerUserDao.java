package com.saomiao.owneruser.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.domain.OwnerUserDO;

/**
 * 
 * @author tmn
 */
@Mapper
public interface OwnerUserDao {

	ManagersDO get(Long userId);
	
	ManagersDO getbyname(String username);
	
	List<ManagersDO> list(Map<String,Object> map);
	
	int save(ManagersDO user);
	
	int count(Map<String,Object> map);
	
	int update(ManagersDO user);
	
	int remove(Long userId);
	int updateFlag(ManagersDO user);



}
