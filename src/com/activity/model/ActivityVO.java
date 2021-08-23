package com.activity.model;

import java.io.Serializable;

import lombok.Data;

@Data
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
	
}
