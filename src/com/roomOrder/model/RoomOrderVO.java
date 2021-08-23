package com.roomOrder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RoomOrderVO implements Serializable {
	private Integer ord_no;
	private Integer mem_no;
	private Integer type_no;
	private Timestamp start_date;
	private Integer day_num;
	private Integer rm_num;
	private Integer price;
	private Integer total_price;
	private String note;
	private String title;
	private String name;
	private String phone;
	private String email;
	private String payment;
	private Timestamp ord_date;	
	private Integer ord_state;
	
	public RoomOrderVO() {
		super();
	}
	
	public RoomOrderVO(Integer ord_no, Integer mem_no, Integer type_no, Timestamp start_date, Integer day_num,
			Integer rm_num, Integer price, Integer total_price, String note, String title, String name, String phone,
			String email, String payment, Timestamp ord_date, Integer ord_state) {
		super();
		this.ord_no = ord_no;
		this.mem_no = mem_no;
		this.type_no = type_no;
		this.start_date = start_date;
		this.day_num = day_num;
		this.rm_num = rm_num;
		this.price = price;
		this.total_price = total_price;
		this.note = note;
		this.title = title;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.payment = payment;
		this.ord_date = ord_date;
		this.ord_state = ord_state;
	}

	public Integer getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(Integer ord_no) {
		this.ord_no = ord_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getType_no() {
		return type_no;
	}

	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Integer getDay_num() {
		return day_num;
	}

	public void setDay_num(Integer day_num) {
		this.day_num = day_num;
	}

	public Integer getRm_num() {
		return rm_num;
	}

	public void setRm_num(Integer rm_num) {
		this.rm_num = rm_num;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Timestamp getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(Timestamp ord_date) {
		this.ord_date = ord_date;
	}

	public Integer getOrd_state() {
		return ord_state;
	}

	public void setOrd_state(Integer ord_state) {
		this.ord_state = ord_state;
	}

}
