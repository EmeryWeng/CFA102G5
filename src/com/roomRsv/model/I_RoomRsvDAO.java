package com.roomRsv.model;

import java.sql.Date;
import java.util.List;

public interface I_RoomRsvDAO {
	public void insert(RoomRsvVO roomRsvVO);

//	改期=先用舊日期cancel，再用新日期reserve
	public void reserve(Integer qty, Integer type_no, Date start_date, Date end_date);

	public void cancel(Integer qty, Integer type_no, Date start_date, Date end_date);

	public RoomRsvVO getOneByDateType(Date rsv_date, Integer type_no);

	public List<RoomRsvVO> getOneDayByDate(Date rsv_date); // 同一天的各房型預訂

	public List<RoomRsvVO> getNotRsv(Integer stay, Integer type_no); // 取得不可預約的日期 前台房型詳情用

	public List<RoomRsvVO> getAll(); // 全部

	public List<RoomRsvVO> getAllByType(Integer type_no); // 同房型的每天預訂
	// **修改房間的房型時更新預定表裡的rm_total
	// public void renewRmTotal(Integer type_no, Integer rm_total);
	// **改期用，同房型且剩餘間數都要大於還有連續天數的問題，待解....CTE
	// public List<RoomRsvVO> getAllByTypeLeft(Integer type_no);
}
