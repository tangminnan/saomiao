package com.saomiao.common.controller;

import org.springframework.stereotype.Controller;

import com.saomiao.common.utils.ShiroUtils;
import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.domain.OwnerUserDO;

@Controller
public class BaseController {
	public ManagersDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getMid();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	public Long getforIds() {
		return getUser().getMid();
	}
}