package com.saomiao.information.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.saomiao.common.annotation.Log;
import com.saomiao.common.controller.BaseController;
import com.saomiao.common.utils.R;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.OOSUploadService;
import com.saomiao.owneruser.domain.ManagersDO;

@RestController
public class OOSUploadController extends BaseController {
	
	@Autowired
	private OOSUploadService oosUploadService;
	
	@Log("oos数据上传")
	@GetMapping("/information/oosUpload")
	public R oosUpload(String url,Long mid){   
		UsersDO userdo = new UsersDO();
		//根据医生id，查询医生姓名
		ManagersDO managersDO = oosUploadService.selectNameByid(mid);
		
		userdo.setMname(managersDO.getUsername());
		userdo.setUfolder(url);
		userdo.setUupdatedate(new Date());
		
		if(oosUploadService.save(userdo)>0){
			return R.ok();
		}
		return R.error();
	}
	
	
}
