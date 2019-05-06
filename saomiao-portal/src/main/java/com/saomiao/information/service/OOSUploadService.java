package com.saomiao.information.service;

import com.saomiao.information.domain.UsersDO;
import com.saomiao.owneruser.domain.ManagersDO;

public interface OOSUploadService {

	ManagersDO selectNameByid(Long mid);

	int save(UsersDO userdo);

}
