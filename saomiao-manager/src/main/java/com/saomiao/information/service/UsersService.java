package com.saomiao.information.service;

import java.util.List;
import java.util.Map;

import com.saomiao.common.utils.Query;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
public interface UsersService {
	
	UsersDO get(Long uId);
	
	List<UsersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UsersDO user);
	
	int update(UsersDO user);
	
	int remove(Long uId);
	
	int batchRemove(Long[] uIds);

	List<UsersDO> lists();

	List<UsersDO> getFileByid(Long uid);

	ManagersDO selectManById(Long userId);

	List<UsersDO> getfileByname(Map<String, Object> map);

	UsersDO getNameByimg(String uimg);

	int removeByimg(String uimg);

	List<Map<String, Object>> exeList(Map<String, Object> map);
}
