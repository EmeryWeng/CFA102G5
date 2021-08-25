package com.roomOrder.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RoomOrderVO implements Serializable {
	private Integer ord_no;
	private Integer mem_no;
	private Integer type_no;
	private LocalDate start_date;
	private LocalDate end_date;
	private Integer rm_num;
	private Integer price;
	private Integer total_price;
	private String note;
	private String title;
	private String name;
	private String phone;
	private String email;
	private String payment;
	private LocalDate ord_date;	
	private Integer ord_state;
}
