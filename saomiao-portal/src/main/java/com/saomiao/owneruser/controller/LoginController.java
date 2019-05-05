package com.saomiao.owneruser.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saomiao.common.annotation.Log;
import com.saomiao.common.controller.BaseController;
import com.saomiao.common.utils.ShiroUtils;
import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.service.OwnerUserService;
import com.saomiao.smsservice.service.ISMSService;



@RestController
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OwnerUserService userService;
	
   
    @Log("密码登录")  
	@GetMapping("/login")
    Map<String, Object> login(String username, String password) {
 	    Map<String, Object> message = new HashMap<>();
	   	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	   	Subject subject = SecurityUtils.getSubject();
	   		try {
	   			Map<String, Object> mapP = new HashMap<String, Object>();
	   			mapP.put("username", username);
	   			boolean flag = userService.exit(mapP);
	   			if (!flag) {
	   				message.put("msg","该手机号码未注册");
	   			}
	   			ManagersDO udo= userService.getbyname(username);
	   			if(udo.getDeleteFlag()==0){
	   				message.put("msg","禁止登录，请联系客服");
	   			}
	   			subject.login(token);
	   			userService.update(udo);
	   			
               
	   			message.put("msg","登录成功");
	   		} catch (AuthenticationException e) {
	   			message.put("msg","用户或密码错误");
	   		}
	    	return message;
    }
 
    
    @Log("登出")
    @GetMapping("/logout")
    Map<String, String> logout() {
        Map<String, String> message = new HashMap<>();
        ShiroUtils.logout();
        message.put("msg", "登出成功");
        return message;
    }

}
