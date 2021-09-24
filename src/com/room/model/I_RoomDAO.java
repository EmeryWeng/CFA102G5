package com.room.model;

import java.util.List;

public interface I_RoomDAO {
	public RoomVO insert(RoomVO roomVO);
	public RoomVO update(RoomVO roomVO);  		 // 後台修改：房型編號 房間介紹 房間狀態(0停用/1空房) 入住人姓名
	public void updateCheckin(RoomVO roomVO);	 // 入住修改：房間狀態(2入住中) 入住人姓名稱謂(董董女士)
	public void updateCheckout(RoomVO roomVO);  // 退房修改：房間狀態(1空房) 入住人姓名稱謂(null)
	public RoomVO getOne(String rm_no);	
	public Integer getRmTotal(Integer type_no);	// 取得房型的非停用的房間數量
	public List<RoomVO> getAll();
	public List<RoomVO> getAllByTypeState(Integer type_no); //後台：入住時選房間用 房型+1空房
	public List<RoomVO> getAllByRmState(Integer rm_state); //後台房間列表使用
}
