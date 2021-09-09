package com.roomOrder.model;

import java.util.List;

public interface I_RoomOrderDAO {
	public RoomOrderVO insert(RoomOrderVO roomOrderVO); // 新增訂單
	
	public void update(RoomOrderVO roomOrderVO); // 已完成
	public void cancel(RoomOrderVO roomOrderVO); // 已取消：改總金額、狀態，再改預約表
	public void change(RoomOrderVO roomOrderVO); // 已改期
	
	public RoomOrderVO getOne(Integer ord_no); // 一筆訂單
	public List<RoomOrderVO> getAll(); // 後台：訂單管理 
	public List<RoomOrderVO> getAllByMem(Integer mem_no); // 前台：會員中心 訂房訂單管理
	
	// **新增 訂單 與 新增訂單明細 與 預定表
	// **更新 訂單(訂單狀態) 預計入住日期到了就改已完成 
	// **取消 訂單(改金額、訂單狀態) 與 更新預定表
	// **更改 訂單(改入住日期、訂單狀態) 與 更新預定表

//	public List<RoomOrderVO> getAllBeforeToday(); 	// 當天待入住訂單
//	public List<RoomOrderVO> getAllDateOut();	// 當天待退房訂單
}
