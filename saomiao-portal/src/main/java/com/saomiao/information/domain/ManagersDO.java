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

	// 主键
	private Long mid;
	// 姓名
	private String username;
	// 单位
	private String morganization;
	// 职务
	private String mduty;
	// 联系电话
	private String mphone;
	// 身份 0管理员 1普通
	private Integer mlevel;
	// 创建时间
	private Date mupdatedate;

	private String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
