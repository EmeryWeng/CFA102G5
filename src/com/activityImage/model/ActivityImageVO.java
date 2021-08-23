package com.activityImage.model;

import java.io.Serializable;
import java.util.Arrays;

import lombok.Data;

@Data
public class ActivityImageVO implements Serializable{
	private Integer actImgId;
	private Integer actId;
	private byte[] actImg;
	
}
