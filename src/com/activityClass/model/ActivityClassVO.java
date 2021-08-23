package com.activityClass.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class ActivityClassVO implements Serializable{

	private Integer actClassId;
	private String  actClassName;
	private Boolean actClassState;
	
}
