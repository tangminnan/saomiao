package com.saomiao.information.dao;

import org.apache.ibatis.annotations.Mapper;

import com.saomiao.information.domain.ManagerTempDO;

@Mapper
public interface OOSUploadDao {

	int save(ManagerTempDO managerTempDO);

}
