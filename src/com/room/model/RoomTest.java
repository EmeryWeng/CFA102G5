package com.room.model;

import java.util.List;

public class RoomTest {
	
	public static void main(String[] args) {
		I_RoomDAO dao = new RoomJDBCDAO();
		
		// 新增一個房間物件,設定值，再用dao裡面的方法insert新增這個物件
//		RoomVO room = new RoomVO();
//		room.setRm_no("111");
//		room.setType_no(1);
//		room.setRm_info("XXX風景好美！");
//		dao.insert(room);
		
		// 修改
//		RoomVO room = new RoomVO();
//		room.setRm_no("111");
//		room.setType_no(3);
//		room.setRm_info("CC");
//		room.setRm_state(0);
//		room.setName_title("C");
//		dao.update(room);
		
		// 修改 入住
//		RoomVO room = new RoomVO();
//		room.setRm_no("111");
//		room.setName_title("郭老師");
//		dao.updateCheckin(room);
		
		// 修改 退房
//		RoomVO room = new RoomVO();
//		room.setRm_no("111");
//		dao.updateCheckout(room);
		
		// 查詢一筆 用PK
//		RoomVO room = dao.getOne("111");
//		System.out.print(room.getRm_no() + ",");
//		System.out.print(room.getType_no() + ",");
//		System.out.print(room.getRm_info() + ",");
//		System.out.print(room.getRm_state() + ",");
//		System.out.println(room.getName_title());
		
		// 查詢全部
//		List<RoomVO> list = dao.getAll();
//		for (RoomVO room : list) {
//			System.out.print(room.getRm_no() + ",");
//			System.out.print(room.getType_no() + ",");
//			System.out.print(room.getRm_info() + ",");
//			System.out.print(room.getRm_state() + ",");
//			System.out.println(room.getName_title());
//		}
		
		// 查詢該房型的空房，入住時選房間用
//		List<RoomVO> list = dao.getAllByTypeState(5);
//		for (RoomVO room : list) {
//			System.out.print(room.getRm_no() + ",");
//			System.out.print(room.getType_no() + ",");
//			System.out.print(room.getRm_info() + ",");
//			System.out.print(room.getRm_state() + ",");
//			System.out.println(room.getName_title());
//		}
		
	}
}
