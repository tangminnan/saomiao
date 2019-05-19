package com.saomiao.information.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saomiao.common.utils.ExcelExportUtil4DIY;
import com.saomiao.common.utils.PageUtils;
import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
import com.saomiao.common.utils.ShiroUtils;
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
	
	private static Logger logger = LoggerFactory.getLogger(UsersController.class);
	
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
		String admin = ShiroUtils.getUser().getRoleName();
		if(!"admin".equals(admin)){		//普通管理登录
			params.put("mname", ShiroUtils.getUser().getUsername());
		}
		
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
	
	@ResponseBody
	@GetMapping("/download")
	public List<UsersDO> ossDownload(Long uid){
		
		List<UsersDO> usersDO= usersService.getFileByid(uid);
		
		usersDO.get(0).setUfolder(usersDO.get(0).getUfolder()+"/3d.zip");
		
		return usersDO;
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
	String point(@PathVariable("uid") Long uid,Model model,@RequestParam Map<String, Object> params){
		
		UsersDO user = usersService.get(uid);
		
		//查询列表数据
		String admin = ShiroUtils.getUser().getRoleName();
		if(!"admin".equals(admin)){		//普通管理登录
			params.put("username", ShiroUtils.getUser().getUsername());
		}
		
		List<UsersDO> user2  = usersService.getfileByname(params);
		
		/*List<ManagersDO> managersDO = managersService.list(params);
		model.addAttribute("managersDO", managersDO); */
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		
	    return "information/user/point";
	}
	
	/*@ResponseBody
	@GetMapping("/getfile/{name}")
	List<UsersDO> getfile(@PathVariable("name") String name){
		
		List<UsersDO> usersDO =  usersService.getfileByname(name);
	    return usersDO;
	}
	*/
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("information:user:add")
	public R save(UsersDO user){
		//查询列表数据
		String admin = ShiroUtils.getUser().getRoleName();
		if(!"admin".equals(admin)){		//普通管理登录
			user.setMname(ShiroUtils.getUser().getUsername());
		}
		
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
			    	
					//查询列表数据
					String admin = ShiroUtils.getUser().getRoleName();
					if(!"admin".equals(admin)){		//普通管理登录
						user.setMname(ShiroUtils.getUser().getUsername());
					}
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
	 * 导出
	 * */
	@RequestMapping(value="/exportExcel")
	public void exportExcel(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "用户信息列表"+format.format(new Date().getTime())+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
	try {

		//查询列表数据
		String admin = ShiroUtils.getUser().getRoleName();
		if(!"admin".equals(admin)){		//普通管理登录
			params.put("mname",ShiroUtils.getUser().getUsername());
		}
			
		Query query = new Query(params);
		String type = request.getParameter("type");
		
		/*if(type.equals("3")){
			List<Map<String, Object>> XxxDOs = sportsStatisticsService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}*/
		//导出按时间查询后的数据
		//导出全部数据
		if(type.equals("2")){
			List<Map<String, Object>> XxxDOs = usersService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		/*//导出符合条件的全部数据
		if(type.equals("3")){
			query.remove("offset");
			query.remove("limit");
			List<Map<String, Object>> XxxDOs = dataService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}*/
		//导选中部分
		if(type.equals("4")){
			query.remove("offset");
			query.remove("limit");
			List<Map<String, Object>> XxxDOs = usersService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("exportExcel出错"+e.getMessage());
		}finally{
			out.close();
		}
	}
	
	
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ResponseBody
	@RequiresPermissions("information:user:edit")
	public R update(UsersDO user){
		
		user.setUupdatedate(new Date());
		if(user.getUimg() != null && user.getUimg() != "" && (user.getUfolder() == null || user.getUfolder() == "")){
			int length = user.getUimg().length();
			user.setUfolder(user.getUimg().substring(0, length-14));
			
			UsersDO userName =  usersService.getNameByimg(user.getUimg());
			user.setMname(userName.getMname());
			
			if(usersService.update(user) > 0){
				usersService.removeByimg(user.getUimg());
			}
		}
		
	
		return R.ok();
	}
	
	@PostMapping("/updateUser")
	@ResponseBody
	@RequiresPermissions("information:user:edit")
	public R updateUser(UsersDO user){
		
		user.setUupdatedate(new Date());
		if(usersService.update(user) > 0){
			return R.ok();
		}else {
			return R.error();
		}
		
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
		
		usersService.batchRemove(uIds);
		return R.ok();
	}
	
}
