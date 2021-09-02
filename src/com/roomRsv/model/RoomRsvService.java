package com.roomRsv.model;

import java.time.LocalDate;
import java.util.List;

public class RoomRsvService {
	
	private I_RoomRsvDAO dao;

	public RoomRsvService() {
		dao = new RoomRsvDAO();
	}
	
	public void addRoomRsv(LocalDate rsv_date, Integer type_no, Integer rm_total, Integer rsv_total) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setRsv_date(rsv_date);
		roomRsvVO.setType_no(type_no);
		roomRsvVO.setRm_total(rm_total);
		roomRsvVO.setRsv_total(rsv_total);
		
		dao.insert(roomRsvVO);
	}
	
	public void reserveRoomRsv(LocalDate rsv_date, Integer type_no, Integer rm_total, Integer rsv_total) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setType_no(type_no);
		
		dao.reserve(roomRsvVO);
	}
	
	public void canceleRoomRsv(LocalDate rsv_date, Integer type_no, Integer rm_total, Integer rsv_total) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setType_no(type_no);
		
		dao.cancel(roomRsvVO);
	}
	
	public void getOneDayRoomRsv(LocalDate rsv_date) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setRsv_date(rsv_date);
		
		dao.getOneDayByDate(rsv_date);
	}
	
	public List<RoomRsvVO> getAllRoomRsv() {
		return dao.getAll();
	}
	
	public List<RoomRsvVO> getAllByTypeRoomRsv(Integer type_no) {
		return dao.getAllByType(type_no);
	}
}
