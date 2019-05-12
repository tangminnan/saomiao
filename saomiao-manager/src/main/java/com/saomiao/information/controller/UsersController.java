package com.saomiao.information.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.saomiao.information.service.ManagersService;
import com.saomiao.information.service.UsersService;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
 
@Controller
@RequestMapping("/information/user")
public class UsersController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ManagersService managersService;
	
	@Autowired
	private ManagerTempService managertemp;
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UsersDO> userList = usersService.list(query);
		int total = usersService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	public List<UsersDO> lists(){
		//查询列表数据
		List<UsersDO> userList = usersService.lists();
		return userList;
	}
	
	@GetMapping("/add")
	//@RequiresPermissions("information:user:add")
	String add(){
	    return "information/user/add";
	}

	@GetMapping("/batchAdd")
	@RequiresPermissions("information:user:batchAdd")
	String batchAdd(){
	    return "information/user/batchAdd";
	}
	
	@GetMapping("/edit/{uid}")
	@RequiresPermissions("information:user:edit")
	String edit(@PathVariable("uid") Long uid,Model model){
		UsersDO user = usersService.get(uid);
		List<ManagersDO> managersDO = managersService.lists();
		model.addAttribute("user", user);
		model.addAttribute("managersDO", managersDO);
	    return "information/user/edit";
	}
	
	@GetMapping("/point/{uid}")
	String point(@PathVariable("uid") Long uid,Model model){
		
		UsersDO user = usersService.get(uid);
		user.setUid(uid);
		List<ManagersDO> managersDO = managersService.lists();
		
		model.addAttribute("user", user);
		model.addAttribute("managersDO", managersDO); 
		
	    return "information/managerTemp/point";
	}
	
	@ResponseBody
	@GetMapping("/getfile/{name}")
	List<ManagerTempDO> getfile(@PathVariable("name") String name){
		
		ManagersDO managersDO =  managertemp.getidByname(name);
		List<ManagerTempDO> managerTempDOs = managertemp.getfile(managersDO.getMid());
	    return managerTempDOs;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("information:user:add")
	public R save(UsersDO user){
		Date update = new Date();
		user.setUupdatedate(update);
		if(usersService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	
	

	/**
	 * 批量保存
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	@RequiresPermissions("information:user:batchAdd")
	public R batchSave(UsersDO user){
		System.out.println(user);
		try {
			XSSFWorkbook wb=null;
			XSSFSheet sheet=null;
			
			if(user.getExcelUser()!=null && user.getExcelUser().getSize()>0){
				wb= new XSSFWorkbook(user.getExcelUser().getInputStream());
			    sheet=wb.getSheetAt(0);
			    
			    for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
			    	
			    	XSSFRow row = sheet.getRow(rowNum);
			    	
			    	if(row==null) continue;
			    	if(row.getCell(0)!=null)
			    		row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setUname(row.getCell(0).getStringCellValue());//姓名
			    	if(row.getCell(1)!=null)
			    		row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setUorganization(row.getCell(1).getStringCellValue());//单位或学校
			    	if(row.getCell(2)!=null)
			    		user.setUgender((int)row.getCell(2).getNumericCellValue());//性别（0男 1女）
			    	if(row.getCell(3)!=null)
						row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setUgrand(row.getCell(3).getStringCellValue());//年级或职业
			    	if(row.getCell(4)!=null)
			    		user.setUage((int)row.getCell(4).getNumericCellValue());//年龄
			    	if(row.getCell(5)!=null)
			    		user.setUbirthday(row.getCell(5).getDateCellValue());//出生日期
			    	if(row.getCell(6)!=null)
						row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setUidcard(row.getCell(6).getStringCellValue());//身份证号
			    	if(row.getCell(7)!=null)
			    		row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setUphone(row.getCell(7).getStringCellValue());//联系电话
			    	if(row.getCell(8)!=null)
			    		user.setUheight(row.getCell(8).getNumericCellValue());//身高
			    	if(row.getCell(9)!=null)
			    		user.setUweight(row.getCell(9).getNumericCellValue());//体重
			    	if(row.getCell(10)!=null)
						row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
			    		user.setMname(row.getCell(10).getStringCellValue());//负责人
			    	
			    	user.setUupdatedate(new Date());
			    	
			    	usersService.save(user);
			    	
			    	Map<String,Object> params = new HashMap<String,Object>();
			    	List<UsersDO> list = usersService.list(params);
			    	if(list.size()>0) continue;
			    }
			}
			return R.ok();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ResponseBody
	@RequiresPermissions("information:user:edit")
	public R update(UsersDO user){
		user.setUupdatedate(new Date());
		
		String img = user.getUimg();
		String file = img.substring(0, -14);
		user.setUfolder(file);
		
		usersService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:user:remove")
	public R remove( Long uid){
		if(usersService.remove(uid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:user:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] uIds){
		System.out.println(uIds);
		
		usersService.batchRemove(uIds);
		return R.ok();
	}
	
}
