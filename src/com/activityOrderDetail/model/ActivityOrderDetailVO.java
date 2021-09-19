package com.activityOrderDetail.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActivityOrderDetailVO implements Serializable{
	private Integer act_order_detail_no;
	private Integer act_order_no;
	private Integer act_session_no;
	private Integer act_real_join_number;
	private Integer	act_order_price;
	private Integer	act_coupon_price;
	private Integer act_price_total;
	private Integer act_order_detail_state = 1;
	//狀態有1 2 3 4 5
}
