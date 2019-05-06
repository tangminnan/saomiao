package com.saomiao.information.dao;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.information.domain.UsersDO;
import com.saomiao.owneruser.domain.ManagersDO;

@Mapper
public interface OOSUploadDao {

	ManagersDO selectNameByid(Long mid);

	int save(UsersDO userdo);

}
