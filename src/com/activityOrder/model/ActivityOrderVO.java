package com.activityOrder.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
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
	
}

