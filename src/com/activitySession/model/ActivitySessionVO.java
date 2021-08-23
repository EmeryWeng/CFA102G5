package com.activitySession.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ActivitySessionVO implements Serializable{
	private Integer actSessionId;
	private Integer actId;
	private LocalDate actStartDate;
	private LocalDate actEndDate;
	private Integer actSessionRealNumber;
	private LocalDate actSessionStartDate;
	private LocalTime actSessionStartTime;
	private Integer actSessionUpperLimit;
	private Integer actSessionLowerLimit;
	private Boolean actSessionHoldState;
	
}
