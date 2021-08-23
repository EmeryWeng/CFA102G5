package com.roomType.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable {
	private Integer type_no;
	private String type_name;
	private Integer type_qty;
	private Integer type_price;
	private Integer type_size;
	private String bed_size;
	private String type_info;
	private String type_facility;
	private Boolean type_state;
	
//	沒參數的建構子
	public RoomTypeVO() {
		super();
	}

//	有參數的建構子
	public RoomTypeVO(Integer type_no, String type_name, Integer type_qty, Integer type_price, Integer type_size,
			String bed_size, String type_info, String type_facility, Boolean type_state) {
		super();
		this.type_no = type_no;
		this.type_name = type_name;
		this.type_qty = type_qty;
		this.type_price = type_price;
		this.type_size = type_size;
		this.bed_size = bed_size;
		this.type_info = type_info;
		this.type_facility = type_facility;
		this.type_state = type_state;
	}

	public Integer getType_no() {
		return type_no;
	}

	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public Integer getType_qty() {
		return type_qty;
	}

	public void setType_qty(Integer type_qty) {
		this.type_qty = type_qty;
	}

	public Integer getType_price() {
		return type_price;
	}

	public void setType_price(Integer type_price) {
		this.type_price = type_price;
	}

	public Integer getType_size() {
		return type_size;
	}

	public void setType_size(Integer type_size) {
		this.type_size = type_size;
	}

	public String getBed_size() {
		return bed_size;
	}

	public void setBed_size(String bed_size) {
		this.bed_size = bed_size;
	}

	public String getType_info() {
		return type_info;
	}

	public void setType_info(String type_info) {
		this.type_info = type_info;
	}

	public String getType_facility() {
		return type_facility;
	}

	public void setType_facility(String type_facility) {
		this.type_facility = type_facility;
	}

	public Boolean getType_state() {
		return type_state;
	}

	public void setType_state(Boolean type_state) {
		this.type_state = type_state;
	}
	
}
