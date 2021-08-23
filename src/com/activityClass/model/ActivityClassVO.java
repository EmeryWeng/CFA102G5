package com.activityClass.model;

import java.io.Serializable;

public class ActivityClassVO implements Serializable{

	private Integer actClassId;
	private String  actClassName;
	private Boolean actClassState;
	

	@Override
	public String toString() {
		return "ActivityClassVO [actClassId=" + actClassId + ", actClassName=" + actClassName + ", actClassState="
				+ actClassState + "]";
	}

	public Integer getActClassId() {
		return actClassId;
	}

	public void setActClassId(Integer actClassId) {
		this.actClassId = actClassId;
	}

	public String getActClassName() {
		return actClassName;
	}

	public void setActClassName(String actClassName) {
		this.actClassName = actClassName;
	}

	public Boolean getActClassState() {
		return actClassState;
	}

	public void setActClassState(Boolean actClassState) {
		this.actClassState = actClassState;
	}
	
}
