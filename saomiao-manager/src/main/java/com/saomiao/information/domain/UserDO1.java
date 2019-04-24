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
public class UserDO1 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long uId;
	//
	private String uName;
	//单位或学校
	private String uOrganization;
	//性别  0男  1女
	private Integer uGender;
	//年级或职业
	private String uGrand;
	//年龄
	private Integer uAge;
	//出生日期
	private Date uBirthday;
	//身份证号
	private String uIdcard;
	//联系电话
	private String uPhone;
	//身高 单位cm
	private Double uHeight;
	//体重 单位kg
	private Double uWeight;
	//3D扫描数据存放目录
	private String uFolder;
	//负责人（医生）
	private String mName;
	//创建时间
	private Date uUpdatedate;

	/**
	 * 设置：主键
	 */
	public void setUId(Long uId) {
		this.uId = uId;
	}
	/**
	 * 获取：主键
	 */
	public Long getUId() {
		return uId;
	}
	/**
	 * 设置：
	 */
	public void setUName(String uName) {
		this.uName = uName;
	}
	/**
	 * 获取：
	 */
	public String getUName() {
		return uName;
	}
	/**
	 * 设置：单位或学校
	 */
	public void setUOrganization(String uOrganization) {
		this.uOrganization = uOrganization;
	}
	/**
	 * 获取：单位或学校
	 */
	public String getUOrganization() {
		return uOrganization;
	}
	/**
	 * 设置：性别  0男  1女
	 */
	public void setUGender(Integer uGender) {
		this.uGender = uGender;
	}
	/**
	 * 获取：性别  0男  1女
	 */
	public Integer getUGender() {
		return uGender;
	}
	/**
	 * 设置：年级或职业
	 */
	public void setUGrand(String uGrand) {
		this.uGrand = uGrand;
	}
	/**
	 * 获取：年级或职业
	 */
	public String getUGrand() {
		return uGrand;
	}
	/**
	 * 设置：年龄
	 */
	public void setUAge(Integer uAge) {
		this.uAge = uAge;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getUAge() {
		return uAge;
	}
	/**
	 * 设置：出生日期
	 */
	public void setUBirthday(Date uBirthday) {
		this.uBirthday = uBirthday;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getUBirthday() {
		return uBirthday;
	}
	/**
	 * 设置：身份证号
	 */
	public void setUIdcard(String uIdcard) {
		this.uIdcard = uIdcard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getUIdcard() {
		return uIdcard;
	}
	/**
	 * 设置：联系电话
	 */
	public void setUPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getUPhone() {
		return uPhone;
	}
	/**
	 * 设置：身高 单位cm
	 */
	public void setUHeight(Double uHeight) {
		this.uHeight = uHeight;
	}
	/**
	 * 获取：身高 单位cm
	 */
	public Double getUHeight() {
		return uHeight;
	}
	/**
	 * 设置：体重 单位kg
	 */
	public void setUWeight(Double uWeight) {
		this.uWeight = uWeight;
	}
	/**
	 * 获取：体重 单位kg
	 */
	public Double getUWeight() {
		return uWeight;
	}
	/**
	 * 设置：3D扫描数据存放目录
	 */
	public void setUFolder(String uFolder) {
		this.uFolder = uFolder;
	}
	/**
	 * 获取：3D扫描数据存放目录
	 */
	public String getUFolder() {
		return uFolder;
	}
	/**
	 * 设置：负责人（医生）
	 */
	public void setMName(String mName) {
		this.mName = mName;
	}
	/**
	 * 获取：负责人（医生）
	 */
	public String getMName() {
		return mName;
	}
	/**
	 * 设置：创建时间
	 */
	public void setUUpdatedate(Date uUpdatedate) {
		this.uUpdatedate = uUpdatedate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getUUpdatedate() {
		return uUpdatedate;
	}
}
