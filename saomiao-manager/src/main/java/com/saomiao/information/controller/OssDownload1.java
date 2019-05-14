package com.saomiao.information.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.UsersService;
import com.saomiao.oa.domain.Response;


/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-08 18:53:14
 */
 
@Controller
@RequestMapping("/ossDownload")
public class OssDownload1 {
	
	@Autowired
	private UsersService userService;
	
	@GetMapping()
	String Ossdownload(Long uid){
		/*// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
		String accessKeyId = "LTAIshP9j8jxO4KO";
		String accessKeySecret = "QKwMvh2VocxZUf1qJl5nfPyJEHA7Lk";
		String bucketName = "jiujiu056";
		*/
		System.out.println(uid);
		UsersDO usersDO  = userService.getFileByid(uid);
		System.out.println(usersDO.getUfolder());
		

		/*// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
		ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("E:/MyDownloads"));

		// 关闭OSSClient。
		ossClient.shutdown();*/
		
		return "";
	}
	
	
	
}
