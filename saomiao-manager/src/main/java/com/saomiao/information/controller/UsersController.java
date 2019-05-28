package com.saomiao.information.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.multipart.MultipartFile;

import com.saomiao.common.utils.ExcelExportUtil4DIY;
import com.saomiao.common.utils.PageUtils;
import com.saomiao.common.utils.Query;
import com.saomiao.common.utils.R;
import com.saomiao.common.utils.ShiroUtils;
import com.saomiao.common.utils.StringUtils;
import com.saomiao.information.domain.ManagersDO;
import com.saomiao.information.domain.UsersDO;
import com.saomiao.information.service.ManagersService;
import com.saomiao.information.service.UsersService;
import com.saomiao.system.config.ExcelUtils;

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
	
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		
		String admin = ShiroUtils.getUser().getUsername();
		if(!"admin".equals(admin)){		//普通管理登录
			if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
				params.put("mname", ShiroUtils.getUser().getUsername());
			}
		}
		
        Query query = new Query(params);
        
		List<UsersDO> userList = usersService.list(query);
		
		/*for(int i = 0; i<userList.size() ; i++){
			if(userList.get(i).getUbirthday() !=null){
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				try {
					Date date = sdf2.parse(sdf2.format(userList.get(i).getUbirthday()));
					userList.get(i).setUbirthday(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}	
		}*/
		int total = usersService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	
	@ResponseBody
	@GetMapping("/selectlist")
	public PageUtils selectlist(@RequestParam Map<String, Object> params){
		
		String admin = ShiroUtils.getUser().getUsername();
		if(!"admin".equals(admin)){		//普通管理登录
			if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
				params.put("mname", ShiroUtils.getUser().getUsername());
			}
		}
		
        Query query = new Query(params);
		List<UsersDO> userList = usersService.selectlist(query);
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
	
	
	//解绑
	@ResponseBody
	@GetMapping("/unbind")
	public R unbind(Long uid){
		
		System.out.println("==============uid==========="+uid);
		UsersDO oldusers =  usersService.get(uid);
		System.out.println("==============================="+oldusers.getUimg());
		UsersDO newusers = new UsersDO();
		newusers.setUfolder(oldusers.getUfolder());
		newusers.setMname(oldusers.getMname());
		newusers.setUimg(oldusers.getUimg());
		newusers.setUupdatedate(new Date());
		
		if(usersService.save(newusers) >0){
			usersService.updateUser(uid);
		}
		return R.ok();
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
		
		String admin = ShiroUtils.getUser().getUsername();
		if(!"admin".equals(admin)){		//普通管理登录
			if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
				params.put("username", ShiroUtils.getUser().getUsername());
			}
		}
		
		List<UsersDO> user2  = usersService.getfileByname(params);
		
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		
	    return "information/user/point";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("information:user:add")
	public R save(UsersDO user){

		String admin = ShiroUtils.getUser().getUsername();
		if(!"admin".equals(admin)){		//普通管理登录
			if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
				user.setMname(ShiroUtils.getUser().getUsername());
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("uname", user.getUname());
    	map.put("uorganization", user.getUorganization());
    	map.put("uage", user.getUage());
    	user.setUupdatedate(new Date());
    	if(usersService.list(map).size() == 0){
    		usersService.save(user);
    		return R.ok();
    	}else if(usersService.list(map).size() > 0){
    		return R.error("此用户已存在，请勿重复添加");
    	}else{
    		return R.error();
    	}
	}
	
	

	/**
	 * 批量保存
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	@RequiresPermissions("information:user:batchAdd")
	public R batchSave(MultipartFile file){
		int num = 0;
		InputStream in=null;
		Workbook book=null;
		try {
			if(file != null){
				in = file.getInputStream();
				book =ExcelUtils.getBook(in);
				Sheet sheet = book.getSheetAt(0);
				Row row=null;
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					try {
						row = sheet.getRow(rowNum);
						String uname = ExcelUtils.getCellFormatValue(row.getCell((short)0));
						String uorganization = ExcelUtils.getCellFormatValue(row.getCell((short)1));
						String ugender = ExcelUtils.getCellFormatValue(row.getCell((short)2));
						String ugrand = ExcelUtils.getCellFormatValue(row.getCell((short)3));
						String uage = ExcelUtils.getCellFormatValue(row.getCell((short)4));
						String ubirthday = ExcelUtils.getCellFormatValue(row.getCell((short)5));
						String uidcard = ExcelUtils.getCellFormatValue(row.getCell((short)6));
						String uphone = ExcelUtils.getCellFormatValue(row.getCell((short)7));
						String uheight = ExcelUtils.getCellFormatValue(row.getCell((short)8));
						String uweight = ExcelUtils.getCellFormatValue(row.getCell((short)9));
						UsersDO user = new UsersDO();
						if(StringUtils.isNotBlank(uname) && StringUtils.isNotBlank(uorganization)&& StringUtils.isNotBlank(uage)){
							user.setUname(uname);
							user.setUorganization(uorganization);
							user.setUage(Integer.parseInt(uage));
							if(ugender != null && ugender !=""){
								user.setUgender(Integer.parseInt(ugender));
							}
							if(ugrand != null && ugrand !=""){
								user.setUgrand(ugrand);
							}
							if(ubirthday != null && ubirthday !=""){
								user.setUbirthday(new SimpleDateFormat("yyyy/MM/dd").parse(ubirthday));
							}
							if(uidcard != null && uidcard !=""){
								user.setUidcard(uidcard);
							}
							if(uphone != null && uphone !=""){
								user.setUphone(uphone);
							}
							if(uheight != null && uheight !=""){
								user.setUheight(Double.parseDouble(uheight));
							}
							if(uweight != null && uweight !=""){
								user.setUweight(Double.parseDouble(uweight));
							}
							
							
							String admin = ShiroUtils.getUser().getUsername();
							if(!"admin".equals(admin)){		//普通管理登录
								if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
									user.setMname(ShiroUtils.getUser().getUsername());
								}
							}
					    	user.setUupdatedate(new Date());
					    	
					    	Map<String,Object> map = new HashMap<String,Object>();
					    	map.put("uname", user.getUname());
					    	map.put("uorganization", user.getUorganization());
					    	map.put("uage", user.getUage());
					    	
					    	if(usersService.list(map).size() == 0){
					    		usersService.save(user);
					    	}
					    	num++;
						}else{
							return R.error("导入失败!姓名&年龄&学校不能为空");
						}
					} catch (Exception e) {
						return R.error("导入失败！第"+rowNum+"条");
					}
				}
				return R.ok("上传成功,共增加["+num+"]条");
			}else{
				return R.error("请选择导入的文件!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(book!=null)
					book.close();
				if(book!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return R.error();
	}
		
	

	/**
	 * 导出
	 * */
	@RequestMapping(value="/exportExcel")
	public void exportExcel(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "3D扫描用户列表"+format.format(new Date().getTime())+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
	try {
		Query query = new Query(params);
		String type = request.getParameter("type");
		
		//导出全部数据
		if(type.equals("2")){
			
			String admin = ShiroUtils.getUser().getUsername();
			if(!"admin".equals(admin)){		//普通管理登录
				if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
					params.put("mname", ShiroUtils.getUser().getUsername());
				}
			}
			List<Map<String, Object>> XxxDOs = usersService.exeList(params);
			if( XxxDOs == null){
				R.error("当前数据为空");
			}
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		
		//导选中部分
		if(type.equals("4")){
			
			String admin = ShiroUtils.getUser().getUsername();
			if(!"admin".equals(admin)){		//普通管理登录
				if(managersService.getIdByname(ShiroUtils.getUser().getUsername()) != null){
					params.put("mname", ShiroUtils.getUser().getUsername());
				}
			}
			query.remove("offset");
			query.remove("limit");
			System.out.println("ids:"+query.get("ids"));
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
