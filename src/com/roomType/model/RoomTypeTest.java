package com.roomType.model;

import java.util.List;

public class RoomTypeTest {
	
	public static void main(String[] args) {
		I_RoomTypeDAO dao = new RoomTypeJDBCDAO();

		// 新增一個房型物件,設定值，再用dao裡面的方法insert新增這個物件
//		RoomTypeVO roomtype = new RoomTypeVO();
//		roomtype.setType_name("VV房型名稱");
//		roomtype.setType_qty(3);
//		roomtype.setType_price(33);
//		roomtype.setType_size(3);
//		roomtype.setBed_size("4張單人床");
//		roomtype.setType_info("這個房間有三張分很開的單人床");
//		roomtype.setType_facility("有樸克牌");
//		dao.insert(roomtype);
		
		// 修改
//		RoomTypeVO roomtype = new RoomTypeVO();
//		roomtype.setType_no(7);
//		roomtype.setType_name("修改的房型名稱");
//		roomtype.setType_qty(1);
//		roomtype.setType_price(111);
//		roomtype.setType_size(11);
//		roomtype.setBed_size("333張單人床");
//		roomtype.setType_info("這個房間有11張分很開的單人床");
//		roomtype.setType_facility("有樸克牌");
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
		
		// 查詢全部
//		List<RoomTypeVO> list = dao.getAll();
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
