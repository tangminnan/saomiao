package com.saomiao.information.controller;

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

import com.saomiao.common.utils.PageUtils;
import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
import com.saomiao.information.domain.ManagerTempDO;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.ManagerTempService;
import com.saomiao.information.service.UsersService;


/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-08 18:53:14
 */
 
@Controller
@RequestMapping("/information/managerTemp")
public class ManagerTempController {
	@Autowired
	private ManagerTempService managerTempService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping()
	@RequiresPermissions("information:managerTemp:managerTemp")
	String ManagerTemp(){
	    return "information/managerTemp/managerTemp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:managerTemp:managerTemp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ManagerTempDO> managerTempList = managerTempService.list(query);
		int total = managerTempService.count(query);
		PageUtils pageUtils = new PageUtils(managerTempList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:managerTemp:add")
	String add(){
	    return "information/managerTemp/add";
	}

	@GetMapping("/edit/{mid}")
	@RequiresPermissions("information:managerTemp:edit")
	String edit(@PathVariable("mid") Integer mid,Model model){
		ManagerTempDO managerTemp = managerTempService.get(mid);
		model.addAttribute("managerTemp", managerTemp);
	    return "information/managerTemp/edit";
	}
	
	@GetMapping("/point/{id}")
	String point(@PathVariable("id") Integer id,Model model){
		return "information/managerTemp/point";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:managerTemp:add")
	public R save( ManagerTempDO managerTemp){
		if(managerTempService.save(managerTemp)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:managerTemp:edit")
	public R update( ManagerTempDO managerTemp){
		managerTempService.update(managerTemp);
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/pointUpdate")
	public R pointUpdate(@RequestParam Map<String, Object> params){
		managerTempService.pointUpdate(params);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:managerTemp:remove")
	public R remove( Integer mid){
		if(managerTempService.remove(mid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:managerTemp:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] mids){
		managerTempService.batchRemove(mids);
		return R.ok();
	}
	
}
