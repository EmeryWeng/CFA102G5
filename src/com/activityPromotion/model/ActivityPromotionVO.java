package com.activityPromotion.model;

import java.io.Serializable;
import java.time.LocalDate;


public class ActivityPromotionVO implements Serializable{
	
	private Integer actPromotionId;
	private String  actPromotionName;
	private LocalDate actPromotionStartDate;
	private LocalDate actPromotionEndDate;
	
	
	@Override
	public String toString() {
		return "ActivityPromotion [actPromotionId=" + actPromotionId + ", actPromotionName=" + actPromotionName
				+ ", actPromotionStartDate=" + actPromotionStartDate + ", actPromotionEndDate=" + actPromotionEndDate
				+ "]";
	}
	public Integer getActPromotionId() {
		return actPromotionId;
	}
	public void setActPromotionId(Integer actPromotionId) {
		this.actPromotionId = actPromotionId;
	}
	public String getActPromotionName() {
		return actPromotionName;
	}
	public void setActPromotionName(String actPromotionName) {
		this.actPromotionName = actPromotionName;
	}
	public LocalDate getActPromotionStartDate() {
		return actPromotionStartDate;
	}
	public void setActPromotionStartDate(LocalDate actPromotionStartDate) {
		this.actPromotionStartDate = actPromotionStartDate;
	}
	public LocalDate getActPromotionEndDate() {
		return actPromotionEndDate;
	}
	public void setActPromotionEndDate(LocalDate actPromotionEndDate) {
		this.actPromotionEndDate = actPromotionEndDate;
	}
	
}
