package com.saomiao.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-04-24 16:22:03
 */
public class ManagersDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long mId;
	//姓名
	private String mName;
	//单位
	private String mOrganization;
	//职务
	private String mDuty;
	//联系电话
	private String mPhone;
	//身份 0管理员 1普通
	private Integer mLevel;
	//创建时间
	private Date mUpdatedate;

	/**
	 * 设置：主键
	 */
	public void setMId(Long mId) {
		this.mId = mId;
	}
	/**
	 * 获取：主键
	 */
	public Long getMId() {
		return mId;
	}
	/**
	 * 设置：姓名
	 */
	public void setMName(String mName) {
		this.mName = mName;
	}
	/**
	 * 获取：姓名
	 */
	public String getMName() {
		return mName;
	}
	/**
	 * 设置：单位
	 */
	public void setMOrganization(String mOrganization) {
		this.mOrganization = mOrganization;
	}
	/**
	 * 获取：单位
	 */
	public String getMOrganization() {
		return mOrganization;
	}
	/**
	 * 设置：职务
	 */
	public void setMDuty(String mDuty) {
		this.mDuty = mDuty;
	}
	/**
	 * 获取：职务
	 */
	public String getMDuty() {
		return mDuty;
	}
	/**
	 * 设置：联系电话
	 */
	public void setMPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getMPhone() {
		return mPhone;
	}
	/**
	 * 设置：身份 0管理员 1普通
	 */
	public void setMLevel(Integer mLevel) {
		this.mLevel = mLevel;
	}
	/**
	 * 获取：身份 0管理员 1普通
	 */
	public Integer getMLevel() {
		return mLevel;
	}
	/**
	 * 设置：创建时间
	 */
	public void setMUpdatedate(Date mUpdatedate) {
		this.mUpdatedate = mUpdatedate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getMUpdatedate() {
		return mUpdatedate;
	}
}
