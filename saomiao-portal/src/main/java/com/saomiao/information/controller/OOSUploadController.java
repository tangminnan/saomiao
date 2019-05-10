package com.saomiao.information.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saomiao.common.annotation.Log;
import com.saomiao.common.controller.BaseController;
import com.saomiao.common.utils.R;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.OOSUploadService;


@RestController
public class OOSUploadController extends BaseController {
	
	@Autowired
	private OOSUploadService oosUploadService;
	
	@Log("oos数据上传")
	@GetMapping("/information/oosUpload")
	public R oosUpload(Long mid,String url){ 
		UsersDO users = new UsersDO();
		
		ManagersDO  manager = oosUploadService.selectNameByid(mid);
		
		
		users.setMname(manager.getUsername());
		try {
			
			URLEncoder.encode(url, "utf-8");
			users.setUfolder(url);
			users.setUimg(url+"/head_sign.jpg");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
		users.setUupdatedate(new Date());
		
		if(oosUploadService.save(users)>0){
				return R.ok();
		}
		return R.error();
	}
	
	
}
