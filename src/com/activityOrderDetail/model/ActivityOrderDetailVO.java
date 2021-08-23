package com.activityOrderDetail.model;

import java.io.Serializable;

public class ActivityOrderDetailVO implements Serializable{
	private Integer actOrderId;
	private Integer actSessionId;
	private Integer actRealJoinNumber;
	private Integer	actOrderPrice;
	private Integer	actCouponPrice;
	private Integer actPriceTotal;
	private Integer actOrderState;
	@Override
	public String toString() {
		return "ActivityOrderDetailVO [actOrderId=" + actOrderId + ", actSessionId=" + actSessionId
				+ ", actRealJoinNumber=" + actRealJoinNumber + ", actOrderPrice=" + actOrderPrice + ", actCouponPrice="
				+ actCouponPrice + ", actPriceTotal=" + actPriceTotal + ", actOrderState=" + actOrderState + "]";
	}
	public Integer getActOrderId() {
		return actOrderId;
	}
	public void setActOrderId(Integer actOrderId) {
		this.actOrderId = actOrderId;
	}
	public Integer getActSessionId() {
		return actSessionId;
	}
	public void setActSessionId(Integer actSessionId) {
		this.actSessionId = actSessionId;
	}
	public Integer getActRealJoinNumber() {
		return actRealJoinNumber;
	}
	public void setActRealJoinNumber(Integer actRealJoinNumber) {
		this.actRealJoinNumber = actRealJoinNumber;
	}
	public Integer getActOrderPrice() {
		return actOrderPrice;
	}
	public void setActOrderPrice(Integer actOrderPrice) {
		this.actOrderPrice = actOrderPrice;
	}
	public Integer getActCouponPrice() {
		return actCouponPrice;
	}
	public void setActCouponPrice(Integer actCouponPrice) {
		this.actCouponPrice = actCouponPrice;
	}
	public Integer getActPriceTotal() {
		return actPriceTotal;
	}
	public void setActPriceTotal(Integer actPriceTotal) {
		this.actPriceTotal = actPriceTotal;
	}
	public Integer getActOrderState() {
		return actOrderState;
	}
	public void setActOrderState(Integer actOrderState) {
		this.actOrderState = actOrderState;
	}
	
}
