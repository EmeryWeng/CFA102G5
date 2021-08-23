package com.roomOrderDetail.model;

import java.sql.Timestamp;
import java.util.List;

public interface I_RoomOrderDetailDAO {
	
	public void insert(RoomOrderDetailVO roomorderdetailvo);
	public void update(RoomOrderDetailVO roomorderdetailvo);
	public void earlyCheckout(Integer detail_no); // 提早退房
	
	public void updateCheckin(Timestamp checkin_date, String rm_no, Integer detail_state);  // 入住
	public void updateCheckout(Timestamp checkout_date, Integer detail_state);  // 退房
	
	public List<RoomOrderDetailVO> getAllInRoom();	// 入住中的訂單明細，可提前退房

//	public RoomOrderDetailVO getOne(Integer detail_no); // 不知道要不要
	public List<RoomOrderDetailVO> getAllRoomOrderDetail(); // 後台：所有訂單明細
}
