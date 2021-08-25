package com.roomOrderDetail.model;

import java.util.List;

public interface I_RoomOrderDetailDAO {
	public void insert(RoomOrderDetailVO detailvo);
	public void checkin(RoomOrderDetailVO detailvo); // 入住：入住日期、填房號、狀態
	public void checkout(RoomOrderDetailVO detailvo); // 退房；退房日期、房號清空、狀態
	public RoomOrderDetailVO getOne(Integer detail_no); 
	public List<RoomOrderDetailVO> getAll();
	public List<RoomOrderDetailVO> getAllByOrdno(Integer ord_no);
}
