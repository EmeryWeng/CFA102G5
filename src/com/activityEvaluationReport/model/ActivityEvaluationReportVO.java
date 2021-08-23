package com.activityEvaluationReport.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ActivityEvaluationReportVO implements Serializable {
	
	private Integer	actEvaluationId;
	private Integer memId;
	private LocalDateTime actReportDate;
	private Integer actEvaluationReportReason;
	private Integer actEvaluationReportState;
	
}
