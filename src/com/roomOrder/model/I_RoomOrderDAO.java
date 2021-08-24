package com.roomOrder.model;

import java.util.List;
import java.util.Set;
import com.roomOrderDetail.model.RoomOrderDetailVO;

public interface I_RoomOrderDAO {
	public void insert(RoomOrderVO roomordervo); // 新增訂單
	public void update(RoomOrderVO roomordervo); // 已完成
	public void cancel(Integer ord_no, Integer total_price, Integer ord_state); // 已取消
	public void change(RoomOrderVO roomordervo); // 已改期
//	type_no	start_date	day_num	rm_num ord_state
	
	public RoomOrderVO getOne(Integer ord_no); // 一筆訂單
	public List<RoomOrderVO> getAllByMem(Integer mem_id); // 前台：會員中心 訂房訂單管理
	public List<RoomOrderVO> getAll(); // 後台：訂單管理 
	public Set<RoomOrderDetailVO> getDetailsByOrdNo(Integer ord_no);    // 某訂房訂單的明細(一對多)(回傳Set)
	
	// 同時 新增訂單 與 新增訂單明細 與 預定表
	// 同時 取消訂單(改訂單狀態) 與 更新預定表

	public List<RoomOrderVO> getAllBeforeToday(); 	// 當天待入住訂單
	public List<RoomOrderVO> getAllDateOut();	// 當天待退房訂單
	
	public List<RoomOrderVO> getAllByStatus(Integer ord_state);	// 依訂單狀態查詢訂單
}
