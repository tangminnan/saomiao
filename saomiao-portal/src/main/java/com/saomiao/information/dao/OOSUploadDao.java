package com.saomiao.information.dao;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;

@Mapper
public interface OOSUploadDao {

	int save(UsersDO users);

	ManagersDO selectNameByid(Long mid);

}
