package com.saomiao.information.domain;

import java.io.Serializable;

public class ManagerTempDO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long mid;
	
	private String url;
	
	private String img;

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "ManagerTempDO [mid=" + mid + ", url=" + url + ", img=" + img + "]";
	}
	
	
	
}
