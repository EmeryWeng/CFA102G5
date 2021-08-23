package com.activity.model;

import java.io.Serializable;

public class ActivityVO implements Serializable{
	private Integer actId;
	private Integer actClassId;
	private String  actName;
	private String  actLocation;
	private Integer actScheduleTime;
	private String  actInstruction;
	private String  actGatherLocation;
	private Double  actLocationLongitude;
	private Double  actLocationLatitude;
	private Integer	actSellNumber;
	private Integer	actJoinNumber;
	private Integer	actEvaluationNumber;
	private Double  actAverageStarNumber;
	private Boolean actState;
	
	
	
	@Override
	public String toString() {
		return "ActivityVO [actId=" + actId + ", actClassId=" + actClassId + ", actName=" + actName + ", actLocation="
				+ actLocation + ", actScheduleTime=" + actScheduleTime + ", actInstruction=" + actInstruction
				+ ", actGatherLocation=" + actGatherLocation + ", actLocationLongitude=" + actLocationLongitude
				+ ", actLocationLatitude=" + actLocationLatitude + ", actSellNumber=" + actSellNumber
				+ ", actJoinNumber=" + actJoinNumber + ", actEvaluationNumber=" + actEvaluationNumber
				+ ", actAverageStarNumber=" + actAverageStarNumber + ", actState=" + actState + "]";
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getActClassId() {
		return actClassId;
	}
	public void setActClassId(Integer actClassId) {
		this.actClassId = actClassId;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getActLocation() {
		return actLocation;
	}
	public void setActLocation(String actLocation) {
		this.actLocation = actLocation;
	}
	public Integer getActScheduleTime() {
		return actScheduleTime;
	}
	public void setActScheduleTime(Integer actScheduleTime) {
		this.actScheduleTime = actScheduleTime;
	}
	public String getActInstruction() {
		return actInstruction;
	}
	public void setActInstruction(String actInstruction) {
		this.actInstruction = actInstruction;
	}
	public String getActGatherLocation() {
		return actGatherLocation;
	}
	public void setActGatherLocation(String actGatherLocation) {
		this.actGatherLocation = actGatherLocation;
	}
	public Double getActLocationLongitude() {
		return actLocationLongitude;
	}
	public void setActLocationLongitude(Double actLocationLongitude) {
		this.actLocationLongitude = actLocationLongitude;
	}
	public Double getActLocationLatitude() {
		return actLocationLatitude;
	}
	public void setActLocationLatitude(Double actLocationLatitude) {
		this.actLocationLatitude = actLocationLatitude;
	}
	public Integer getActSellNumber() {
		return actSellNumber;
	}
	public void setActSellNumber(Integer actSellNumber) {
		this.actSellNumber = actSellNumber;
	}
	public Integer getActJoinNumber() {
		return actJoinNumber;
	}
	public void setActJoinNumber(Integer actJoinNumber) {
		this.actJoinNumber = actJoinNumber;
	}
	public Integer getActEvaluationNumber() {
		return actEvaluationNumber;
	}
	public void setActEvaluationNumber(Integer actEvaluationNumber) {
		this.actEvaluationNumber = actEvaluationNumber;
	}
	public Double getActAverageStarNumber() {
		return actAverageStarNumber;
	}
	public void setActAverageStarNumber(Double actAverageStarNumber) {
		this.actAverageStarNumber = actAverageStarNumber;
	}
	public Boolean getActState() {
		return actState;
	}
	public void setActState(Boolean actState) {
		this.actState = actState;
	}
	
}
