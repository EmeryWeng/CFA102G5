package com.roomRsv.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RoomRsvVO implements Serializable {
	private Timestamp rsv_date;
	private Integer type_no;
	private Integer rm_total;
	private Integer rsv_total;
	
	public RoomRsvVO() {
		super();
	}

	public RoomRsvVO(Timestamp rsv_date, Integer type_no, Integer rm_total, Integer rsv_total) {
		super();
		this.rsv_date = rsv_date;
		this.type_no = type_no;
		this.rm_total = rm_total;
		this.rsv_total = rsv_total;
	}

	public Timestamp getRsv_date() {
		return rsv_date;
	}

	public void setRsv_date(Timestamp rsv_date) {
		this.rsv_date = rsv_date;
	}

	public Integer getType_no() {
		return type_no;
	}

	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public Integer getRm_total() {
		return rm_total;
	}

	public void setRm_total(Integer rm_total) {
		this.rm_total = rm_total;
	}

	public Integer getRsv_total() {
		return rsv_total;
	}

	public void setRsv_total(Integer rsv_total) {
		this.rsv_total = rsv_total;
	}
}
