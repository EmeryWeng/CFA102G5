package com.roomImg.model;

import java.io.Serializable;

public class RoomImgVO implements Serializable {
	private Integer img_no;
	private Integer type_no;
	private byte[] type_img;
	
	public RoomImgVO() {
		super();
	}

	public RoomImgVO(Integer img_no, Integer type_no, byte[] type_img) {
		super();
		this.img_no = img_no;
		this.type_no = type_no;
		this.type_img = type_img;
	}

	public Integer getImg_no() {
		return img_no;
	}

	public void setImg_no(Integer img_no) {
		this.img_no = img_no;
	}

	public Integer getType_no() {
		return type_no;
	}

	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public byte[] getType_img() {
		return type_img;
	}

	public void setType_img(byte[] type_img) {
		this.type_img = type_img;
	}
	
}
