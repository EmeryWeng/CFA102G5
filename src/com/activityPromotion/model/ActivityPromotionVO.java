package com.activityPromotion.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ActivityPromotionVO implements Serializable{
	
	private Integer actPromotionId;
	private String  actPromotionName;
	private LocalDate actPromotionStartDate;
	private LocalDate actPromotionEndDate;
	
	
}
