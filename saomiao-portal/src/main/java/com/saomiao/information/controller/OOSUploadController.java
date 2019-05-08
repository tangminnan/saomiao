package com.saomiao.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saomiao.common.annotation.Log;
import com.saomiao.common.controller.BaseController;
import com.saomiao.common.utils.R;
import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.service.OOSUploadService;

@RestController
public class OOSUploadController extends BaseController {
	
	@Autowired
	private OOSUploadService oosUploadService;
	
	@Log("oos数据上传")
	@GetMapping("/information/oosUpload")
	public R oosUpload(Long mid,String url){ 
		ManagerTempDO managerTempDO = new ManagerTempDO();
		
		if(mid != 0 && url != null && !url.isEmpty()){
			managerTempDO.setMid(mid);
			managerTempDO.setUrl(url);
			managerTempDO.setImg(managerTempDO.getUrl()+"/head_sign.jpg");
			
			if(oosUploadService.save(managerTempDO)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	
	
}
