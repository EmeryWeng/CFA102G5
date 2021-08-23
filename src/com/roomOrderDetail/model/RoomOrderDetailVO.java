package com.roomOrderDetail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RoomOrderDetailVO implements Serializable {
	private Integer detail_no;
	private Integer ord_no;
	private Timestamp checkin_date;
	private Timestamp checkout_date;
	private String rm_no;
	private Integer detail_state;
	
	public RoomOrderDetailVO() {
		super();
	}

	public RoomOrderDetailVO(Integer detail_no, Integer ord_no, Timestamp checkin_date, Timestamp checkout_date, String rm_no,
			Integer detail_state) {
		super();
		this.detail_no = detail_no;
		this.ord_no = ord_no;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.rm_no = rm_no;
		this.detail_state = detail_state;
	}

	public Integer getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(Integer detail_no) {
		this.detail_no = detail_no;
	}

	public Integer getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(Integer ord_no) {
		this.ord_no = ord_no;
	}

	public Timestamp getCheckin_date() {
		return checkin_date;
	}

	public void setCheckin_date(Timestamp checkin_date) {
		this.checkin_date = checkin_date;
	}

	public Timestamp getCheckout_date() {
		return checkout_date;
	}

	public void setCheckout_date(Timestamp checkout_date) {
		this.checkout_date = checkout_date;
	}

	public String getRm_no() {
		return rm_no;
	}

	public void setRm_no(String rm_no) {
		this.rm_no = rm_no;
	}

	public Integer getDetail_state() {
		return detail_state;
	}

	public void setDetail_state(Integer detail_state) {
		this.detail_state = detail_state;
	}
}
