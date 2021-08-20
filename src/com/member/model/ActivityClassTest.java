package com.member.model;

import java.io.Serializable;

public class ActivityClassTest implements Serializable{
	
	private Integer actClassNo;
	private String actClassName;
	private Boolean actClassState;
	
	public ActivityClassTest() {}
	
	public ActivityClassTest(Integer actClassNo, String actClassName, boolean actClassState) {
		super();
		this.actClassNo = actClassNo;
		this.actClassName = actClassName;
		this.actClassState = actClassState;
	}
	public Integer getActClassNo() {
		return actClassNo;
	}
	public void setActClassNo(Integer actClassNo) {
		this.actClassNo = actClassNo;
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
