package com.activityOrder.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActivityOrderVO implements Serializable{
	private Integer actOrderId;
	private Integer memId;
	private LocalDateTime actBookingDate;
	private Integer actOrderTotalPrice;
	private String actOrderTitle;
	private String actOrderName;
	private String actOrderPhone;
	private String actOrderEmail;
	private String actOrderCreditCard;
	
	
	@Override
	public String toString() {
		return "ActivityOrderVO [actOrderId=" + actOrderId + ", memId=" + memId + ", actBookingDate=" + actBookingDate
				+ ", actOrderTotalPrice=" + actOrderTotalPrice + ", actOrderTitle=" + actOrderTitle + ", actOrderName="
				+ actOrderName + ", actOrderPhone=" + actOrderPhone + ", actOrderEmail=" + actOrderEmail
				+ ", actOrderCreditCard=" + actOrderCreditCard + "]";
	}
	
	public Integer getActOrderId() {
		return actOrderId;
	}
	public void setActOrderId(Integer actOrderId) {
		this.actOrderId = actOrderId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public LocalDateTime getActBookingDate() {
		return actBookingDate;
	}
	public void setActBookingDate(LocalDateTime actBookingDate) {
		this.actBookingDate = actBookingDate;
	}
	public Integer getActOrderTotalPrice() {
		return actOrderTotalPrice;
	}
	public void setActOrderTotalPrice(Integer actOrderTotalPrice) {
		this.actOrderTotalPrice = actOrderTotalPrice;
	}
	public String getActOrderTitle() {
		return actOrderTitle;
	}
	public void setActOrderTitle(String actOrderTitle) {
		this.actOrderTitle = actOrderTitle;
	}
	public String getActOrderName() {
		return actOrderName;
	}
	public void setActOrderName(String actOrderName) {
		this.actOrderName = actOrderName;
	}
	public String getActOrderPhone() {
		return actOrderPhone;
	}
	public void setActOrderPhone(String actOrderPhone) {
		this.actOrderPhone = actOrderPhone;
	}
	public String getActOrderEmail() {
		return actOrderEmail;
	}
	public void setActOrderEmail(String actOrderEmail) {
		this.actOrderEmail = actOrderEmail;
	}
	public String getActOrderCreditCard() {
		return actOrderCreditCard;
	}
	public void setActOrderCreditCard(String actOrderCreditCard) {
		this.actOrderCreditCard = actOrderCreditCard;
	}
	
}

