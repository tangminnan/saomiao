package com.saomiao.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2019-05-08 18:53:14
 */
public class ManagerTempDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer mid;
	//
	private String url;
	//
	private String img;

	/**
	 * 设置：
	 */
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	/**
	 * 获取：
	 */
	public Integer getMid() {
		return mid;
	}
	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：
	 */
	public String getImg() {
		return img;
	}
}
