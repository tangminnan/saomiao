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
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.service.ManagersService;

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
        Query query = new Query(params);
		List<ManagersDO> managersList = managersService.list(query);
		int total = managersService.count(query);
		PageUtils pageUtils = new PageUtils(managersList, total);
		return pageUtils;
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
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:managers:add")
	public R save( ManagersDO managers){
		if(managersService.save(managers)>0){
			return R.ok();
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
		managersService.update(managers);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:managers:remove")
	public R remove( Long mid){
		if(managersService.remove(mid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:managers:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] mIds){
		System.out.println(mIds);
		
		managersService.batchRemove(mIds);
		return R.ok();
	}
	
}
