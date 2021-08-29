package com.room.model;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RoomVO implements Serializable {
	private String rm_no;
	private Integer type_no;
	private String rm_info;
	private Integer rm_state;
	private String name_title;
}