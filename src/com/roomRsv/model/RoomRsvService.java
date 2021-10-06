package com.roomRsv.model;

import java.sql.Date;
import java.util.List;

public class RoomRsvService {

	private I_RoomRsvDAO dao;

	public RoomRsvService() {
		dao = new RoomRsvDAO();
	}

	public void addRoomRsv(Date rsv_date, Integer type_no, Integer rm_total, Integer rsv_total) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setRsv_date(rsv_date);
		roomRsvVO.setType_no(type_no);
		roomRsvVO.setRm_total(rm_total);
		roomRsvVO.setRsv_total(rsv_total);

		dao.insert(roomRsvVO);
	}

	public void reserveRoomRsv(Integer qty, Integer type_no, Date start_date, Date end_date) {
		dao.reserve(qty, type_no, start_date, end_date);
	}

	public void canceleRoomRsv(Integer qty, Integer type_no, Date start_date, Date end_date) {
		dao.cancel(qty, type_no, start_date, end_date);
	}

	public void getOneDayRoomRsv(Date rsv_date) {

		RoomRsvVO roomRsvVO = new RoomRsvVO();
		roomRsvVO.setRsv_date(rsv_date);

		dao.getOneDayByDate(rsv_date);
	}

	public List<RoomRsvVO> getNotRsv(Integer qty, Integer type_no) {
		return dao.getNotRsv(qty, type_no);
	}

	public List<RoomRsvVO> getAllRoomRsv() {
		return dao.getAll();
	}

	public List<RoomRsvVO> getAllByTypeRoomRsv(Integer type_no) {
		return dao.getAllByType(type_no);
	}
}
