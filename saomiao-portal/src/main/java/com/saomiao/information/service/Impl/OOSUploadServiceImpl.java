package com.saomiao.information.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saomiao.information.dao.OOSUploadDao;
import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.OOSUploadService;
import com.saomiao.owneruser.domain.ManagersDO;

@Service
public class OOSUploadServiceImpl implements OOSUploadService {

	@Autowired
	private OOSUploadDao oosUploadDao;
	
	@Override
	public int save(ManagerTempDO managerTempDO) {
		// TODO Auto-generated method stub
		return oosUploadDao.save(managerTempDO);
	}

}
