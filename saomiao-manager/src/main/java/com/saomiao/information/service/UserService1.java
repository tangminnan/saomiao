package com.saomiao.information.service;

import com.saomiao.information.domain.UserDO1;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
public interface UserService1 {
	
	UserDO1 get(Long uId);
	
	List<UserDO1> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO1 user);
	
	int update(UserDO1 user);
	
	int remove(Long uId);
	
	int batchRemove(Long[] uIds);
}
