package com.saomiao.information.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saomiao.common.utils.MD5Utils;
import com.saomiao.common.utils.PageUtils;
import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
import com.saomiao.common.utils.ShiroUtils;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.ManagersService;
import com.saomiao.system.dao.UserDao;
import com.saomiao.system.dao.UserRoleDao;
import com.saomiao.system.domain.RoleDO;
import com.saomiao.system.domain.UserDO;
import com.saomiao.system.domain.UserRoleDO;
import com.saomiao.system.service.UserService;


/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
 
@Controller
@RequestMapping("/information/managers")
public class ManagersController {
	@Autowired
	private ManagersService managersService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping()
	@RequiresPermissions("information:managers:managers")
	String Managers(){
	    return "information/managers/managers";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:managers:managers")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String admin = ShiroUtils.getUser().getRoleName();
		if(!"admin".equals(admin)){		//普通管理登录
			params.put("username", ShiroUtils.getUser().getUsername());
		}
		
        Query query = new Query(params);
		List<ManagersDO> managersList = managersService.list(query);
		int total = managersService.count(query);
		PageUtils pageUtils = new PageUtils(managersList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/lists")
	public List<ManagersDO> lists(){
		//查询列表数据
		List<ManagersDO> managersList = managersService.lists();
		return managersList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:managers:add")
	String add(){
	    return "information/managers/add";
	}

	@GetMapping("/edit/{mid}")
	@RequiresPermissions("information:managers:edit")
	String edit(@PathVariable("mid") Long mid,Model model){
		ManagersDO managers = managersService.get(mid);
		model.addAttribute("managers", managers);
	    return "information/managers/edit";
	}
	
	@GetMapping("/transfer/{mid}")
	String transfer(@PathVariable("mid") Long mid,Model model){
		List<ManagersDO> managersList = managersService.Manlist(mid);		//查询所有管理的名字
		
		model.addAttribute("mid", mid);		
		model.addAttribute("managersList", managersList);
	    return "information/managers/transfer";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateMname")
	public R updateMname(Long mid , ManagersDO managersDO){
			if(mid != null && mid != 0 && managersDO.getUsername() != null && !managersDO.getUsername().isEmpty()){
				managersDO.setMid(mid);
				
				if(managersService.updateMname(managersDO) >0){
					managersService.remove(mid);
				}
				return R.ok();
			}else{
				return R.error("无其他管理，不可转移，请勿删除");
			}
			
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:managers:add")
	public R save( ManagersDO managers){
		managers.setMupdatedate(new Date());
		
		if(managersService.save(managers)>0){
			
			UserRoleDO role = new UserRoleDO();
			UserDO user = new UserDO();
			user.setPassword(MD5Utils.encrypt(managers.getUsername(), managers.getPassword()));
			user.setUsername(managers.getUsername());
			user.setName(managers.getUsername());
			user.setMobile(managers.getMphone());
			user.setStatus(1);     //默认状态为正常
			
			//RoleDO roleName = new RoleDO();
			if( userService.saveUser(user) > 0 ){
				/*//查询列表数据
				String admin = ShiroUtils.getUser().getRoleName();
				if(!"admin".equals(admin)){		//普通管理登录
					roleName.setRoleName(ShiroUtils.getUser().getRoleName());
				}
				*/
				role.setRoleId((long) 3);
				role.setUserId(user.getUserId());
				
				roleDao.save(role);
				return R.ok();
			}
		}
		return R.error();
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:managers:edit")
	public R update( ManagersDO managers){
		
		managers.setMupdatedate(new Date());
		if(managersService.update(managers) > 0){
			return R.ok();
		}else{
			return R.error();
		}
		
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:managers:remove")
	public R remove(Long mid){
		
		List<UsersDO> userList = managersService.selectUserById(mid);	//根据id查询属下user
		if(userList != null && !userList.isEmpty()){
			R r = new R();
			r.put("code", 2);
			return r; 
		}else{
			managersService.remove(mid);
			return R.ok();
		}
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:managers:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] mIds){
		
		managersService.batchRemove(mIds);
		return R.ok();
	}
	
}
