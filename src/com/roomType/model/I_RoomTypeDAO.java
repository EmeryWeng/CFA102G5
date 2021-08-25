package com.roomType.model;

import java.util.List;

public interface I_RoomTypeDAO {	
	public void insert(RoomTypeVO roomtypevo);
	public void update(RoomTypeVO roomtypevo);
	public RoomTypeVO getOne(Integer type_no);
	public List<RoomTypeVO> getAll();
}
