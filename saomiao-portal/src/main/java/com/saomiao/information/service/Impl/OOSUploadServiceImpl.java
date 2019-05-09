package com.saomiao.information.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saomiao.information.dao.OOSUploadDao;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.OOSUploadService;


@Service
public class OOSUploadServiceImpl implements OOSUploadService {

	@Autowired
	private OOSUploadDao oosUploadDao;

	@Override
	public ManagersDO selectNameByid(Long mid) {
		return oosUploadDao.selectNameByid(mid);
	}

	@Override
	public int save(UsersDO users) {
		return oosUploadDao.save(users);
	}
	
	

}
