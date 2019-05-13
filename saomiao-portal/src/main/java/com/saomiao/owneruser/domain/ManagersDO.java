package com.saomiao.owneruser.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;



/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-05 15:25:41
 */
public class ManagersDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long mid;
	//用户名
	private String username;
	//密码
	private String password;
	//单位
	private String morganization;
	//职务
	private String mduty;
	//联系电话
	private String mphone;
	//身份 0管理员 1普通
	private Integer mlevel;
	//创建时间
	private Date mupdatedate;
	//是否删除 0是 1否
	private Integer deleteFlag;
	
	private String munionid;
	
	
	public String getMunionid() {
		return munionid;
	}
	public void setMunionid(String munionid) {
		this.munionid = munionid;
	}
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMorganization() {
		return morganization;
	}
	public void setMorganization(String morganization) {
		this.morganization = morganization;
	}
	public String getMduty() {
		return mduty;
	}
	public void setMduty(String mduty) {
		this.mduty = mduty;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public Integer getMlevel() {
		return mlevel;
	}
	public void setMlevel(Integer mlevel) {
		this.mlevel = mlevel;
	}
	public Date getMupdatedate() {
		return mupdatedate;
	}
	public void setMupdatedate(Date mupdatedate) {
		this.mupdatedate = mupdatedate;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
