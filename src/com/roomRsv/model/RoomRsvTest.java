package com.roomRsv.model;

import java.util.List;
import java.sql.Timestamp;

public class RoomRsvTest {
	
	public static void main(String[] args) {
		I_RoomRsvDAO dao = new RoomRsvJDBCDAO();
		
		// 新增 timestamp問題
//		RoomRsvVO roomrsv = new RoomRsvVO();
//		roomrsv.setRsv_date("2021-09-23");
//		roomrsv.setType_no(5);
//		roomrsv.setRm_total(2);
//		roomrsv.setRsv_total(1);
//		dao.insert(roomrsv);
		
		// 修改 預定
//		RoomRsvVO roomrsv = new RoomRsvVO();
//		roomrsv.setType_no(5);
//		dao.reserve(roomrsv);
		
		// 修改 取消
//		RoomRsvVO roomrsv = new RoomRsvVO();
//		roomrsv.setType_no(5);
//		dao.cancel(roomrsv);
		
		// 查詢 同一天的各房型預訂 timestamp問題
//		List<RoomRsvVO> list = dao.getOneDayByDate(2021-09-01);
//		for (RoomRsvVO roomrsv : list) {
//			System.out.print(roomrsv.getRsv_date() + ",");
//			System.out.print(roomrsv.getType_no() + ",");
//			System.out.print(roomrsv.getRm_total() + ",");
//			System.out.println(roomrsv.getRsv_total());
//		}
		
		// 查詢 全部
//		List<RoomRsvVO> list = dao.getAll();
//		for (RoomRsvVO roomrsv : list) {
//			System.out.print(roomrsv.getRsv_date() + ",");
//			System.out.print(roomrsv.getType_no() + ",");
//			System.out.print(roomrsv.getRm_total() + ",");
//			System.out.println(roomrsv.getRsv_total());
//		}
		
		// 查詢 同房型的每天預訂
//		List<RoomRsvVO> list = dao.getAllByType(5);
//		for (RoomRsvVO roomrsv : list) {
//			System.out.print(roomrsv.getRsv_date() + ",");
//			System.out.print(roomrsv.getType_no() + ",");
//			System.out.print(roomrsv.getRm_total() + ",");
//			System.out.println(roomrsv.getRsv_total());
//		}
	
	}
}
