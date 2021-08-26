package com.instantMessaging.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InstantMessagingVO implements java.io.Serializable {
	//Primary key msg_no
	private Integer msg_no;
	private Integer mem_no;
	private Boolean msg_direct;
	private String msg;
	private byte[] msg_img;
	private LocalDateTime now_clk;
	
	//空建構子
	public InstantMessagingVO() {}
	
	
}
