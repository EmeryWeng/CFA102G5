package com.activityEvaluation.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ActivityEvaluationVO implements Serializable {
	
	private Integer actEvaluationId;
	private Integer actId;
	private Integer memId;
	private Integer actEvaluationStarNumber;
	private String  actEvaluationMessage;
	private LocalDateTime actEvaluationDate;
	private Boolean actEvaluationState;
	
}
