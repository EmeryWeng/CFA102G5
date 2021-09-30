package com.roomRsv.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class RoomRsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getEnoughType".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String rangedate = req.getParameter("rangedate");
			Integer qty = new Integer(req.getParameter("qty"));
			Integer guest = new Integer(req.getParameter("guest"));

			// 將收到的住宿期間分割成 起始日 和 結束日
			List<String> dateList = new LinkedList<String>();
			String[] split = rangedate.split(" to ");
			for (int i = 0; i < split.length; i++) {
				dateList.add(split[i]);
			}
			String start_date = split[0];
			String end_date = split[1];

			/*************************** 2.開始查詢資料 ****************************************/
			RoomTypeService roomTypeSvc = new RoomTypeService();
			List<RoomTypeVO> list = roomTypeSvc.getEnoughType(java.sql.Date.valueOf(start_date),
					java.sql.Date.valueOf(end_date), qty, guest);

			/*************************** 3.查詢完成,準備轉交 ************/
			req.setAttribute("list", list);
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("start_date", start_date);
			session.setAttribute("end_date", end_date);
			session.setAttribute("qty", qty);
			session.setAttribute("guest", guest);
			String url = "/front_end/room/roomList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的roomList.jsp
			successView.forward(req, res);
		}

		if ("writeInfo".equals(action)) {
			String rangedate = req.getParameter("rangedate");
			Integer qty = new Integer(req.getParameter("qty"));
			Integer type_no = new Integer(req.getParameter("type_no"));

			// 將收到的住宿期間分割成 起始日 和 結束日
			List<String> dateList = new LinkedList<String>();
			String[] split = rangedate.split(" to ");
			for (int i = 0; i < split.length; i++) {
				dateList.add(split[i]);
			}
			String start_date = split[0];
			String end_date = split[1];

			/**************** 可能更新的值都存入session，同時存入req完成，準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("start_date", start_date);
			session.setAttribute("end_date", end_date);
			session.setAttribute("qty", qty);

			req.setAttribute("rangedate", rangedate);
			req.setAttribute("start_date", start_date);
			req.setAttribute("end_date", end_date);
			req.setAttribute("type_no", type_no);
			req.setAttribute("qty", qty);

			String url = "/front_end/room/writeInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的writeInfo.jsp
			successView.forward(req, res);
		}

//		if ("booking".equals(action)) {
//			try {
//				Integer type_no = new Integer(req.getParameter("type_no"));
//				String date = req.getParameter("date");
//				String stay = req.getParameter("stay");
//				Integer qty = Integer.parseInt(req.getParameter("qty"));
//				LocalDate rsv_date = LocalDate.parse(date);
//				RoomRsvService rsvSvc = new RoomRsvService();
//				RoomTypeService roomTypeSvc = new RoomTypeService();
//				JSONObject jsonObj = new JSONObject();
//				List<RoomRsvVO> rsvvoList = new LinkedList<>();
//				List<RoomTypeVO> rmtypeList = roomTypeSvc.getAllRoomType();
//				for (RoomTypeVO rmtypevo : rmtypeList) {
//					RoomRsvVO rsvvo = new RoomRsvVO();
//					Integer rmLeft = rsvSvc.roomCheck(rsv_date, Integer.parseInt(stay), rmtypevo.getType_no());
//					if (rmLeft >= qty) { // 只放有足夠數量的房間
//						rsvvo.setRm_total(rmLeft); // 借放一下Q_Q
//						rsvvo.setType_no(rmtypevo.getType_no());
//						rsvvo.setRsv_date(rsv_date);
//						rsvvoList.add(rsvvo);
//					}
//				}
//				HttpSession session = req.getSession();
//				jsonObj.put("stay", stay); // 回傳額外訊息
//				jsonObj.put("qty", qty);
//				jsonObj.put("startDate", date);
//				jsonObj.put("leaveDate", rsv_date.plusDays(Integer.parseInt(stay)).toString());
//				session.setAttribute("type_no", type_no);
//				session.setAttribute("rsvvoList", rsvvoList);
//				session.setAttribute("infoJson", jsonObj);
//				res.sendRedirect(req.getContextPath() + "/front_end/room/booking.jsp");
//				return;
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			}
//		}
//
//		if ("roomCheck".equals(action)) {
//			out = res.getWriter();
//			try {
//				String type_no = req.getParameter("type_no");
//				String date = req.getParameter("date");
//				Integer stay = Integer.parseInt(req.getParameter("stay"));
//				Integer qty = Integer.parseInt(req.getParameter("qty"));
//				LocalDate rsv_date = LocalDate.parse(date);
//				RoomRsvService rsvSvc = new RoomRsvService();
//				RoomTypeService roomTypeSvc = new RoomTypeService();
//				JSONObject jsonObj = new JSONObject();
//
//				Integer rmLeft = rsvSvc.roomCheck(rsv_date, stay, Integer.parseInt(type_no));
//				if (rmLeft >= qty) {
//					jsonObj.put("type_no", rmLeft);
//					jsonObj.put("Zext", rsv_date.plusDays(1L));
//				} else if (rmLeft == 0) {
//					jsonObj.put("Zext", rsv_date.plusDays(stay));
//					jsonObj.put("isMam", "true");
//				} else {
//					jsonObj.put("Zext", rsv_date.plusDays(stay));
//					jsonObj.put("isFull", "true");
//				}
//				out.print(jsonObj);
//
//				return;
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			}
//		}

	}
}
