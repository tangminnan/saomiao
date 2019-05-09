package com.saomiao.information.service;

import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.domain.ManagersDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-08 18:53:14
 */
public interface ManagerTempService {
	
	ManagerTempDO get(Integer mid);
	
	List<ManagerTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ManagerTempDO managerTemp);
	
	int update(ManagerTempDO managerTemp);
	
	int remove(Integer mid);
	
	int batchRemove(Integer[] mids);

	ManagersDO selectNameByid(Integer mid);

	int pointUpdate(Map<String, Object> params);

	List<ManagerTempDO> getfile(Long mid);

	ManagersDO getidByname(String name);
}
