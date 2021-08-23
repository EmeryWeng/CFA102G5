package com.coupon.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponVO implements Serializable {
	private Integer coupon_no;
	private Integer mem_no;
	private Integer coupon_value;
	private Timestamp coupon_expiry;
	
	public CouponVO() {
		super();
	}

	public CouponVO(Integer coupon_no, Integer mem_no, Integer coupon_value, Timestamp coupon_expiry) {
		super();
		this.coupon_no = coupon_no;
		this.mem_no = mem_no;
		this.coupon_value = coupon_value;
		this.coupon_expiry = coupon_expiry;
	}

	public Integer getCoupon_no() {
		return coupon_no;
	}

	public void setCoupon_no(Integer coupon_no) {
		this.coupon_no = coupon_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getCoupon_value() {
		return coupon_value;
	}

	public void setCoupon_value(Integer coupon_value) {
		this.coupon_value = coupon_value;
	}

	public Timestamp getCoupon_expiry() {
		return coupon_expiry;
	}

	public void setCoupon_expiry(Timestamp coupon_expiry) {
		this.coupon_expiry = coupon_expiry;
	}

}
