package com.activityImage.model;

import java.io.Serializable;
import java.util.Arrays;

import lombok.Data;

@Data
public class ActivityImageVO implements Serializable{
	private Integer act_img_no;
	private Integer act_no;
	private byte[] act_img;
	
}
