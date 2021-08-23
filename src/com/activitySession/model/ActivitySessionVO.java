package com.activitySession.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
	
	
	
	
	@Override
	public String toString() {
		return "ActivitySessionVO [actSessionId=" + actSessionId + ", actId=" + actId + ", actStartDate=" + actStartDate
				+ ", actEndDate=" + actEndDate + ", actSessionRealNumber=" + actSessionRealNumber
				+ ", actSessionStartDate=" + actSessionStartDate + ", actSessionStartTime=" + actSessionStartTime
				+ ", actSessionUpperLimit=" + actSessionUpperLimit + ", actSessionLowerLimit=" + actSessionLowerLimit
				+ ", actSessionHoldState=" + actSessionHoldState + "]";
	}
	public Integer getActSessionId() {
		return actSessionId;
	}
	public void setActSessionId(Integer actSessionId) {
		this.actSessionId = actSessionId;
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public LocalDate getActStartDate() {
		return actStartDate;
	}
	public void setActStartDate(LocalDate actStartDate) {
		this.actStartDate = actStartDate;
	}
	public LocalDate getActEndDate() {
		return actEndDate;
	}
	public void setActEndDate(LocalDate actEndDate) {
		this.actEndDate = actEndDate;
	}
	public Integer getActSessionRealNumber() {
		return actSessionRealNumber;
	}
	public void setActSessionRealNumber(Integer actSessionRealNumber) {
		this.actSessionRealNumber = actSessionRealNumber;
	}
	public LocalDate getActSessionStartDate() {
		return actSessionStartDate;
	}
	public void setActSessionStartDate(LocalDate actSessionStartDate) {
		this.actSessionStartDate = actSessionStartDate;
	}
	public LocalTime getActSessionStartTime() {
		return actSessionStartTime;
	}
	public void setActSessionStartTime(LocalTime actSessionStartTime) {
		this.actSessionStartTime = actSessionStartTime;
	}
	public Integer getActSessionUpperLimit() {
		return actSessionUpperLimit;
	}
	public void setActSessionUpperLimit(Integer actSessionUpperLimit) {
		this.actSessionUpperLimit = actSessionUpperLimit;
	}
	public Integer getActSessionLowerLimit() {
		return actSessionLowerLimit;
	}
	public void setActSessionLowerLimit(Integer actSessionLowerLimit) {
		this.actSessionLowerLimit = actSessionLowerLimit;
	}
	public Boolean getActSessionHoldState() {
		return actSessionHoldState;
	}
	public void setActSessionHoldState(Boolean actSessionHoldState) {
		this.actSessionHoldState = actSessionHoldState;
	}
	
	
	
}
