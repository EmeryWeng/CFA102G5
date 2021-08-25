package com.roomOrder.model;

import java.time.LocalDate;
import java.util.List;

public class RoomOrderTest {
	
	public static void main(String[] args) {
		I_RoomOrderDAO dao = new RoomOrderJDBCDAO();

		// 新增
//		RoomOrderVO roomorder = new RoomOrderVO();
//		roomorder.setMem_no(2);
//		roomorder.setType_no(1);
//		roomorder.setStart_date(LocalDate.of(2021,9,1));
//		roomorder.setEnd_date(LocalDate.of(2021,9,2));
//		roomorder.setRm_num(1);
//		roomorder.setPrice(3000);
//		roomorder.setTotal_price(3000);
//		roomorder.setNote("希望能提前入住");
//		roomorder.setTitle("女士");
//		roomorder.setName("我不說");	
//		roomorder.setPhone("0911111222");
//		roomorder.setEmail("sfsfsd@gmail.com");	
//		roomorder.setPayment("000011112222");	
//		dao.insert(roomorder);
		
		// UPDATE 已完成
//		RoomOrderVO roomorder = new RoomOrderVO();
//		dao.update(roomorder);
		
		// 已取消
//		RoomOrderVO roomorder = new RoomOrderVO();
//		roomorder.setTotal_price(99);
//		roomorder.setOrd_no(4);
//		dao.cancel(roomorder);
		
		// 已改期
//		RoomOrderVO roomorder = new RoomOrderVO();
//		roomorder.setStart_date(LocalDate.of(2021,9,11));
//		roomorder.setEnd_date(LocalDate.of(2021,9,12));
//		roomorder.setOrd_no(3);
//		dao.change(roomorder);
		
		// 查詢一筆 用PK
//		RoomOrderVO roomorder = dao.getOne(1);
//		System.out.print(roomorder.getOrd_no() + ",");
//		System.out.print(roomorder.getMem_no() + ",");
//		System.out.print(roomorder.getType_no() + ",");
//		System.out.print(roomorder.getStart_date() + ",");
//		System.out.print(roomorder.getEnd_date() + ",");
//		System.out.print(roomorder.getRm_num() + ",");
//		System.out.print(roomorder.getPrice() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getNote() + ",");
//		System.out.print(roomorder.getTitle() + ",");
//		System.out.print(roomorder.getName() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getPhone() + ",");
//		System.out.print(roomorder.getEmail() + ",");
//		System.out.print(roomorder.getPayment() + ",");
//		System.out.print(roomorder.getOrd_date() + ",");
//		System.out.println(roomorder.getOrd_state());
		
		// 查詢 全部
//		List<RoomOrderVO> list = dao.getAll();
//		for (RoomOrderVO roomorder : list) {
//		System.out.print(roomorder.getOrd_no() + ",");
//		System.out.print(roomorder.getMem_no() + ",");
//		System.out.print(roomorder.getType_no() + ",");
//		System.out.print(roomorder.getStart_date() + ",");
//		System.out.print(roomorder.getEnd_date() + ",");
//		System.out.print(roomorder.getRm_num() + ",");
//		System.out.print(roomorder.getPrice() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getNote() + ",");
//		System.out.print(roomorder.getTitle() + ",");
//		System.out.print(roomorder.getName() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getPhone() + ",");
//		System.out.print(roomorder.getEmail() + ",");
//		System.out.print(roomorder.getPayment() + ",");
//		System.out.print(roomorder.getOrd_date() + ",");
//		System.out.println(roomorder.getOrd_state());
//		}
		
		// 查詢 會員的全部訂單
//		List<RoomOrderVO> list = dao.getAllByMem(2);
//		for (RoomOrderVO roomorder : list) {
//		System.out.print(roomorder.getOrd_no() + ",");
//		System.out.print(roomorder.getMem_no() + ",");
//		System.out.print(roomorder.getType_no() + ",");
//		System.out.print(roomorder.getStart_date() + ",");
//		System.out.print(roomorder.getEnd_date() + ",");
//		System.out.print(roomorder.getRm_num() + ",");
//		System.out.print(roomorder.getPrice() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getNote() + ",");
//		System.out.print(roomorder.getTitle() + ",");
//		System.out.print(roomorder.getName() + ",");
//		System.out.print(roomorder.getTotal_price() + ",");
//		System.out.print(roomorder.getPhone() + ",");
//		System.out.print(roomorder.getEmail() + ",");
//		System.out.print(roomorder.getPayment() + ",");
//		System.out.print(roomorder.getOrd_date() + ",");
//		System.out.println(roomorder.getOrd_state());
//		}
		
	}
}
