package com.saomiao.information.dao;

import com.saomiao.information.domain.ManagersDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
@Mapper
public interface ManagersDao {

	ManagersDO get(Long mId);
	
	List<ManagersDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ManagersDO managers);
	
	int update(ManagersDO managers);
	
	int remove(Long m_Id);
	
	int batchRemove(Long[] mIds);
}
