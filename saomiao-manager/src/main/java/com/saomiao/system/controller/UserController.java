package com.saomiao.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.saomiao.common.annotation.Log;
import com.saomiao.common.config.Constant;
import com.saomiao.common.controller.BaseController;
import com.saomiao.common.domain.Tree;
import com.saomiao.common.service.DictService;
import com.saomiao.common.utils.MD5Utils;
import com.saomiao.common.utils.PageUtils;
import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.service.ManagersService;
import com.saomiao.system.domain.DeptDO;
import com.saomiao.system.domain.RoleDO;
import com.saomiao.system.domain.UserDO;
import com.saomiao.system.service.RoleService;
import com.saomiao.system.service.UserService;
import com.saomiao.system.vo.UserVO;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	DictService dictService;
	
	@Autowired
	ManagersService managersService;
	
	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		String rname = "";
		List<UserDO> sysUserList = userService.list(query);
		for(UserDO udo:sysUserList){
			List<RoleDO> rdol =roleService.listbyid(udo.getUserId());
			for(int i=0;i<rdol.size();i++){
				rname =rname+ rdol.get(i).getRoleName();
				if(i<rdol.size()-1){
					rname =rname+",";
				}
			}
			udo.setRoleName(rname);
			rname = "";
		}
		int total = userService.count(query);
		System.out.println(sysUserList.size());
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add") 
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		for (int i = 0; i < roles.size(); i++) { 
			if (roles.get(i).getRoleName().equals("admin")) { 
				roles.remove(i); 
				i--; 
			} 
		}
		
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		for (int i = 0; i < roles.size(); i++) { 
			if (roles.get(i).getRoleName().equals("admin")) { 
				roles.remove(i); 
				i--; 
			} 
		}
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		ManagersDO managers = new ManagersDO();
		managers.setMphone(user.getMobile());
		managers.setPassword(user.getPassword());
		managers.setUsername(user.getUsername());
		managers.setMorganization(user.getOrganization());
		managers.setMupdatedate(new Date());
		
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			for(int i=0; i<user.getRoleIds().size();i++){
				if(roleService.get(user.getRoleIds().get(i)).getRoleName().equals("manager")){
					if(managersService.save(managers) >0){
						return R.ok();
					}
				}
			}
			
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		ManagersDO managers = new ManagersDO();
		managers.setMphone(user.getMobile());
		managers.setUsername(user.getUsername());
		managers.setMorganization(user.getOrganization());
		List<String> ls= new ArrayList<String>();
		for(int i=0; i<user.getRoleIds().size();i++){
				ls.add(roleService.get(user.getRoleIds().get(i)).getRoleName());
		}
		if(!ls.contains("manager") && managersService.getIdByname(managers.getUsername())!=null){
			return managersService.selectUserByMid(managersService.getIdByname(managers.getUsername()).getMid());
		}
		if (userService.update(user) > 0) {
			for(int i=0; i<user.getRoleIds().size();i++){
				if(roleService.get(user.getRoleIds().get(i)).getRoleName().equals("manager")){
					if(managersService.getIdByname(managers.getUsername())!=null){
						if(managersService.update(managers) >0){
							return R.ok();
						}
					}else{
						if(managersService.save(managers) >0){
							return R.ok();
						}
					}
				}
					
			}
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		ManagersDO managers = new ManagersDO();
		managers.setMphone(user.getMobile());
		managers.setUsername(user.getUsername());
		managers.setMorganization(user.getOrganization());
		
		if (userService.updatePersonal(user) > 0) {
			if(roleService.get(user.getRoleIds().get(0)).getRoleName().equals("manager")){
				if(managersService.save(managers) >0){
					return R.ok();
				}
			}
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		ManagersDO managers = new ManagersDO();
		UserDO user = userService.get(id);
		managers.setUsername(user.getUsername());
		List<String> ls= new ArrayList<String>();
		
		for(int i=0; i<user.getRoleIds().size();i++){
				ls.add(roleService.get(user.getRoleIds().get(i)).getRoleName());
		}
		//如果包含manager觉得的情况就去查询manager下是否有用户
		if(ls.contains("manager") && managersService.getIdByname(managers.getUsername())!=null){
			return managersService.selectUserById(managersService.getIdByname(managers.getUsername()).getMid());
		}else{
			if(userService.remove(id) >0){
				return R.ok();
			}
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(userVO,getUser());
			
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userVO);
			
			ManagersDO managersDO = new ManagersDO();
			managersDO.setPassword(userVO.getPwdNew());
			managersDO.setUsername(userService.get(userVO.getUserDO().getUserId()).getUsername());
			
			managersService.update(managersDO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}
}
