package com.roomType.model;

import java.util.List;

public class RoomTypeTest {
	
	public static void main(String[] args) {
		I_RoomTypeDAO dao = new RoomTypeJDBCDAO();

		// 新增一個房型物件,設定值，再用dao裡面的方法insert新增這個物件
//		RoomTypeVO roomtype = new RoomTypeVO();
//		roomtype.setType_name("測試的房型名稱");
//		roomtype.setType_qty(3);
//		roomtype.setType_price(888);
//		roomtype.setType_size(88);
//		roomtype.setBed_size("3張單人床");
//		roomtype.setType_info("這個房間有三張分很開的單人床");
//		roomtype.setType_facility("有樸克牌");
//		dao.insert(roomtype);
		
		// 修改
//		RoomTypeVO roomtype = new RoomTypeVO();
//		roomtype.setType_no(6);
//		roomtype.setType_name("修改的房型名稱");
//		roomtype.setType_qty(1);
//		roomtype.setType_price(111);
//		roomtype.setType_size(11);
//		roomtype.setBed_size("21張單人床");
//		roomtype.setType_info("這個房間有11張分很開的單人床");
//		roomtype.setType_facility("X有樸克牌");
//		roomtype.setType_state(false);
//		dao.update(roomtype);
		
		// 查詢一筆 用PK
//		RoomTypeVO roomtype = dao.getOne(6);
//		System.out.print(roomtype.getType_no() + ",");
//		System.out.print(roomtype.getType_name() + ",");
//		System.out.print(roomtype.getType_qty() + ",");
//		System.out.print(roomtype.getType_price() + ",");
//		System.out.print(roomtype.getType_size() + ",");
//		System.out.print(roomtype.getBed_size() + ",");
//		System.out.print(roomtype.getType_info() + ",");
//		System.out.print(roomtype.getType_facility() + ",");
//		System.out.println(roomtype.getType_state());
		
		// 查詢全部 前台不顯示狀態0房型
//		List<RoomTypeVO> list = dao.getAllByFront();
//		for (RoomTypeVO roomtype : list) {
//			System.out.print(roomtype.getType_no() + ",");
//			System.out.print(roomtype.getType_name() + ",");
//			System.out.print(roomtype.getType_qty() + ",");
//			System.out.print(roomtype.getType_price() + ",");
//			System.out.print(roomtype.getType_size() + ",");
//			System.out.print(roomtype.getBed_size() + ",");
//			System.out.print(roomtype.getType_info() + ",");
//			System.out.print(roomtype.getType_facility() + ",");
//			System.out.println(roomtype.getType_state());
//		}
		
		// 查詢全部 後台
//		List<RoomTypeVO> list = dao.getAllByBack();
//		for (RoomTypeVO roomtype : list) {
//			System.out.print(roomtype.getType_no() + ",");
//			System.out.print(roomtype.getType_name() + ",");
//			System.out.print(roomtype.getType_qty() + ",");
//			System.out.print(roomtype.getType_price() + ",");
//			System.out.print(roomtype.getType_size() + ",");
//			System.out.print(roomtype.getBed_size() + ",");
//			System.out.print(roomtype.getType_info() + ",");
//			System.out.print(roomtype.getType_facility() + ",");
//			System.out.println(roomtype.getType_state());
//		}
		
	}
}
