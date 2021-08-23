package com.activityPromotionDetail.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActivityPromotionDetailVO implements Serializable{
	private Integer actPromotionId;
	private Integer actClassNo;
	private Double actDiscountPrice;
	
}
