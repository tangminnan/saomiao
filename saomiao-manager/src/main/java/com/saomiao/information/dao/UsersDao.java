package com.saomiao.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.information.domain.UsersDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
@Mapper
public interface UsersDao {

	UsersDO get(Long uId);
	
	List<UsersDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UsersDO user);
	
	int update(UsersDO user);
	
	int remove(Long u_id);
	
	int batchRemove(Long[] uIds);
}