package com.roomOrderDetail.model;

import java.time.LocalDate;
import java.util.List;

public class RoomOrderDetailTest {
	
	public static void main(String[] args) {
		I_RoomOrderDetailDAO dao = new RoomOrderDetailJDBCDAO();

		// 新增
//		RoomOrderDetailVO detailvo = new RoomOrderDetailVO();
//		detailvo.setOrd_no(5);
//		dao.insert(detailvo);
	
		// 入住
//		RoomOrderDetailVO detailvo = new RoomOrderDetailVO();
//		detailvo.setRm_no("305");
//		detailvo.setDetail_no(2);
//		dao.checkin(detailvo);
		
		// 退房
//		RoomOrderDetailVO detailvo = new RoomOrderDetailVO();
//		detailvo.setDetail_no(2);
//		dao.checkout(detailvo);
		
		// 查詢一筆 用PK
//		RoomOrderDetailVO detailvo = dao.getOne(1);
//		System.out.print(detailvo.getDetail_no() + ",");
//		System.out.print(detailvo.getOrd_no() + ",");
//		System.out.print(detailvo.getCheckin_date() + ",");
//		System.out.print(detailvo.getCheckout_date() + ",");
//		System.out.print(detailvo.getRm_no() + ",");
//		System.out.println(detailvo.getDetail_state());
		
		// 查詢全部
//		List<RoomOrderDetailVO> list = dao.getAll();
//		for (RoomOrderDetailVO detailvo : list) {
//			System.out.print(detailvo.getDetail_no() + ",");
//			System.out.print(detailvo.getOrd_no() + ",");
//			System.out.print(detailvo.getCheckin_date() + ",");
//			System.out.print(detailvo.getCheckout_date() + ",");
//			System.out.print(detailvo.getRm_no() + ",");
//			System.out.println(detailvo.getDetail_state());
//		}
		
		// 查詢全部 用訂單編號
//		List<RoomOrderDetailVO> list = dao.getAllByOrdno(3);
//		for (RoomOrderDetailVO detailvo : list) {
//			System.out.print(detailvo.getDetail_no() + ",");
//			System.out.print(detailvo.getOrd_no() + ",");
//			System.out.print(detailvo.getCheckin_date() + ",");
//			System.out.print(detailvo.getCheckout_date() + ",");
//			System.out.print(detailvo.getRm_no() + ",");
//			System.out.println(detailvo.getDetail_state());
//		}
	}
}
