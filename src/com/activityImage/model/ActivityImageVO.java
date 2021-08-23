package com.activityImage.model;

import java.io.Serializable;
import java.util.Arrays;

public class ActivityImageVO implements Serializable{
	private Integer actImgId;
	private Integer actId;
	private byte[] actImg;
	
	
	
	@Override
	public String toString() {
		return "ActivityImage [actImgId=" + actImgId + ", actId=" + actId + ", actImg=" + Arrays.toString(actImg) + "]";
	}
	
	public Integer getActImgId() {
		return actImgId;
	}
	public void setActImgId(Integer actImgId) {
		this.actImgId = actImgId;
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public byte[] getActImg() {
		return actImg;
	}
	public void setActImg(byte[] actImg) {
		this.actImg = actImg;
	}
	
}
