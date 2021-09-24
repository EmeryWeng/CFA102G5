package com.roomType.model;

import java.util.List;

public interface I_RoomTypeDAO {	
	public RoomTypeVO insert(RoomTypeVO roomTypeVO);
	public RoomTypeVO update(RoomTypeVO roomTypeVO);
	public RoomTypeVO getOne(Integer type_no);
	public List<RoomTypeVO> getAll();
	public List<RoomTypeVO> getAllFront();
	public void changeState(Integer type_no,Boolean type_state);
}
