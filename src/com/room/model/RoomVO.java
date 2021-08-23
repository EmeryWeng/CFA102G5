package com.room.model;

import java.io.Serializable;

public class RoomVO implements Serializable {
	private String rm_no;
	private Integer type_no;
	private String rm_info;
	private Integer rm_state;
	private String name_title;
	
	public RoomVO() {
		super();
	}
	
	public RoomVO(String rm_no, Integer type_no, String rm_info, Integer rm_state, String name_title) {
		super();
		this.rm_no = rm_no;
		this.type_no = type_no;
		this.rm_info = rm_info;
		this.rm_state = rm_state;
		this.name_title = name_title;
	}

	public String getRm_no() {
		return rm_no;
	}

	public void setRm_no(String rm_no) {
		this.rm_no = rm_no;
	}

	public Integer getType_no() {
		return type_no;
	}

	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public String getRm_info() {
		return rm_info;
	}

	public void setRm_info(String rm_info) {
		this.rm_info = rm_info;
	}

	public Integer getRm_state() {
		return rm_state;
	}

	public void setRm_state(Integer rm_state) {
		this.rm_state = rm_state;
	}

	public String getName_title() {
		return name_title;
	}

	public void setName_title(String name_title) {
		this.name_title = name_title;
	}
	
}
