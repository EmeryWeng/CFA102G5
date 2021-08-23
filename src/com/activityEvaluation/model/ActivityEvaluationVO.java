package com.activityEvaluation.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActivityEvaluationVO implements Serializable {
	private Integer actEvaluationId;
	private Integer actId;
	private Integer memId;
	private Integer actEvaluationStarNumber;
	private String  actEvaluationMessage;
	private LocalDateTime actEvaluationDate;
	private Boolean actEvaluationState;
	@Override
	public String toString() {
		return "ActivityEvaluationVO [actEvaluationId=" + actEvaluationId + ", actId=" + actId + ", memId=" + memId
				+ ", actEvaluationStarNumber=" + actEvaluationStarNumber + ", actEvaluationMessage="
				+ actEvaluationMessage + ", actEvaluationDate=" + actEvaluationDate + ", actEvaluationState="
				+ actEvaluationState + "]";
	}
	public Integer getActEvaluationId() {
		return actEvaluationId;
	}
	public void setActEvaluationId(Integer actEvaluationId) {
		this.actEvaluationId = actEvaluationId;
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getActEvaluationStarNumber() {
		return actEvaluationStarNumber;
	}
	public void setActEvaluationStarNumber(Integer actEvaluationStarNumber) {
		this.actEvaluationStarNumber = actEvaluationStarNumber;
	}
	public String getActEvaluationMessage() {
		return actEvaluationMessage;
	}
	public void setActEvaluationMessage(String actEvaluationMessage) {
		this.actEvaluationMessage = actEvaluationMessage;
	}
	public LocalDateTime getActEvaluationDate() {
		return actEvaluationDate;
	}
	public void setActEvaluationDate(LocalDateTime actEvaluationDate) {
		this.actEvaluationDate = actEvaluationDate;
	}
	public Boolean getActEvaluationState() {
		return actEvaluationState;
	}
	public void setActEvaluationState(Boolean actEvaluationState) {
		this.actEvaluationState = actEvaluationState;
	}
	
	
}
