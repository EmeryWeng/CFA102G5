package com.roomRsv.model;

import java.time.LocalDate;
import java.util.List;

public interface I_RoomRsvDAO {
//	如果用CTE排程，sql裡面放放長長的CTE
	public void insert(RoomRsvVO roomRsvVO);
//	改期=先用舊日期cancel，再用新日期reserve
//	需要用到RoomOrderVO(入住日 天數 間數)
	public void reserve(RoomRsvVO roomRsvVO);
	public void cancel(RoomRsvVO roomRsvVO);		
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date); //同一天的各房型預訂
	public List<RoomRsvVO> getAll(); // 全部
	public List<RoomRsvVO> getAllByType(Integer type_no); //同房型的每天預訂

	//	**修改房間的房型時更新預定表裡的rm_total
	//	public void renewRmTotal(Integer type_no, Integer rm_total);
	//	**改期用，同房型且剩餘間數都要大於還有連續天數的問題，待解....CTE
	//	public List<RoomRsvVO> getAllByTypeLeft(Integer type_no); 
}
