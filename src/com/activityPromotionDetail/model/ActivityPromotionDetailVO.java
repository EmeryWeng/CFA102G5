package com.activityPromotionDetail.model;

import java.io.Serializable;

public class ActivityPromotionDetailVO implements Serializable{
	private Integer actPromotionId;
	private Integer actClassNo;
	private Double actDiscountPrice;
	
	
	@Override
	public String toString() {
		return "ActivityPromotionDetail [actPromotionId=" + actPromotionId + ", actClassNo=" + actClassNo
				+ ", actDiscountPrice=" + actDiscountPrice + "]";
	}
	public Integer getActPromotionId() {
		return actPromotionId;
	}
	public void setActPromotionId(Integer actPromotionId) {
		this.actPromotionId = actPromotionId;
	}
	public Integer getActClassNo() {
		return actClassNo;
	}
	public void setActClassNo(Integer actClassNo) {
		this.actClassNo = actClassNo;
	}
	public Double getActDiscountPrice() {
		return actDiscountPrice;
	}
	public void setActDiscountPrice(Double actDiscountPrice) {
		this.actDiscountPrice = actDiscountPrice;
	}
	
}
