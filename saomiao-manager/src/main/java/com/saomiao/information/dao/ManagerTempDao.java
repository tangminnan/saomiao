package com.saomiao.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.domain.ManagersDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-08 18:53:14
 */
@Mapper
public interface ManagerTempDao {

	ManagerTempDO get(Integer mid);
	
	List<ManagerTempDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ManagerTempDO managerTemp);
	
	int update(ManagerTempDO managerTemp);
	
	int remove(Integer mid);
	
	int batchRemove(Integer[] mids);

	ManagersDO selectNameByid(Integer mid);

	int pointUpdate(Map<String, Object> params);

	List<ManagerTempDO> getfile(Long mid);

	ManagersDO getidByname(String name);
}
