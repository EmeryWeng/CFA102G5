package com.activityImage.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

import lombok.Data;

@Data
public class ActivityImageVO implements Serializable{
	private Integer act_img_no;
	private Integer act_no;
	private byte[] act_img;
	
	public String imgToBase64(byte[] imgArray) {
		return Base64.getEncoder().encodeToString(imgArray);
	}
}
