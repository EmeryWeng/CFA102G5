package com.activityEvaluationReport.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActivityEvaluationReportVO implements Serializable {
	
	private Integer	actEvaluationId;
	private Integer memId;
	private LocalDateTime actReportDate;
	private Integer actEvaluationReportReason;
	private Integer actEvaluationReportState;
	
	
	@Override
	public String toString() {
		return "ActivityEvaluationReportVO [actEvaluationId=" + actEvaluationId + ", memId=" + memId
				+ ", actReportDate=" + actReportDate + ", actEvaluationReportReason=" + actEvaluationReportReason
				+ ", actEvaluationReportState=" + actEvaluationReportState + "]";
	}
	public Integer getActEvaluationId() {
		return actEvaluationId;
	}
	public void setActEvaluationId(Integer actEvaluationId) {
		this.actEvaluationId = actEvaluationId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public LocalDateTime getActReportDate() {
		return actReportDate;
	}
	public void setActReportDate(LocalDateTime actReportDate) {
		this.actReportDate = actReportDate;
	}
	public Integer getActEvaluationReportReason() {
		return actEvaluationReportReason;
	}
	public void setActEvaluationReportReason(Integer actEvaluationReportReason) {
		this.actEvaluationReportReason = actEvaluationReportReason;
	}
	public Integer getActEvaluationReportState() {
		return actEvaluationReportState;
	}
	public void setActEvaluationReportState(Integer actEvaluationReportState) {
		this.actEvaluationReportState = actEvaluationReportState;
	}
	
	
}
