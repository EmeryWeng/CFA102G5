package com.activityOrderDetail.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActivityOrderDetailVO implements Serializable{
	private Integer actOrderId;
	private Integer actSessionId;
	private Integer actRealJoinNumber;
	private Integer	actOrderPrice;
	private Integer	actCouponPrice;
	private Integer actPriceTotal;
	private Integer actOrderState;
	
}
