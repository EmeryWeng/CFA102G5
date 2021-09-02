package com.roomOrder.model;

import java.time.LocalDate;
import java.util.List;

public class RoomOrderService {
	
	private I_RoomOrderDAO dao;

	public RoomOrderService() {
		dao = new RoomOrderDAO();
	}
	
	public RoomOrderVO addRoomOrder(Integer mem_no, Integer type_no, LocalDate start_date, LocalDate end_date, 
			Integer rm_num, Integer price, Integer total_price, String note, String title,
			String name, String phone, String email, String payment) {

		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setMem_no(mem_no);
		roomOrderVO.setType_no(type_no);
		roomOrderVO.setStart_date(start_date);
		roomOrderVO.setEnd_date(end_date);
		roomOrderVO.setRm_num(rm_num);
		roomOrderVO.setPrice(price);
		roomOrderVO.setTotal_price(total_price);
		roomOrderVO.setNote(note);
		roomOrderVO.setTitle(title);
		roomOrderVO.setName(name);	
		roomOrderVO.setPhone(phone);
		roomOrderVO.setEmail(email);	
		roomOrderVO.setPayment(payment);	
		
		return dao.insert(roomOrderVO);
	}
	
	public void updateRoomOrder() {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		dao.update(roomOrderVO);
	}

	public void cancelRoomOrder(Integer ord_no, Integer total_price) {

		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setOrd_no(ord_no);
		roomOrderVO.setTotal_price(total_price);	
		
		dao.cancel(roomOrderVO);
	}
	
	public void changeRoomOrder(Integer ord_no, LocalDate start_date, LocalDate end_date) {

		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setOrd_no(ord_no);	
		roomOrderVO.setStart_date(start_date);
		roomOrderVO.setEnd_date(end_date);
		
		dao.change(roomOrderVO);
	}
	
	public RoomOrderVO getOneRoomOrder(Integer ord_no) {
		return dao.getOne(ord_no);
	}
	
	public List<RoomOrderVO> getAllRoomOrder() {
		return dao.getAll();
	}
	
	public List<RoomOrderVO> getAllByMemRoomOrder(Integer mem_no) {
		return dao.getAllByMem(mem_no);
	}
}
