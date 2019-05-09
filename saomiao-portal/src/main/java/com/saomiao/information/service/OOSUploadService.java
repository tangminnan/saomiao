package com.saomiao.information.service;

import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;


public interface OOSUploadService {

	ManagersDO selectNameByid(Long mid);

	int save(UsersDO users);
	
}
