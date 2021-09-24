package com.roomRsv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.roomRsv.model.RoomRsvService;
import com.roomRsv.model.RoomRsvVO;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class RoomRsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = null;

		if ("booking".equals(action)) {
			try {
				Integer type_no = new Integer(req.getParameter("type_no"));
				String date = req.getParameter("date");
				String stay = req.getParameter("stay");
				Integer qty = Integer.parseInt(req.getParameter("qty"));
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				RoomTypeService roomTypeSvc = new RoomTypeService();
				JSONObject jsonObj = new JSONObject();
				List<RoomRsvVO> rsvvoList = new LinkedList<>();
				List<RoomTypeVO> rmtypeList = roomTypeSvc.getAllRoomType();
				for (RoomTypeVO rmtypevo : rmtypeList) {
					RoomRsvVO rsvvo = new RoomRsvVO();
					Integer rmLeft = rsvSvc.roomCheck(rsv_date, Integer.parseInt(stay), rmtypevo.getType_no());
					if (rmLeft >= qty) { // 只放有足夠數量的房間
						rsvvo.setRm_total(rmLeft); // 借放一下Q_Q
						rsvvo.setType_no(rmtypevo.getType_no());
						rsvvo.setRsv_date(rsv_date);
						rsvvoList.add(rsvvo);
					}
				}
				HttpSession session = req.getSession();
				jsonObj.put("stay", stay); // 回傳額外訊息
				jsonObj.put("qty", qty);
				jsonObj.put("startDate", date);
				jsonObj.put("leaveDate", rsv_date.plusDays(Integer.parseInt(stay)).toString());
				session.setAttribute("type_no", type_no);
				session.setAttribute("rsvvoList", rsvvoList);
				session.setAttribute("infoJson", jsonObj);
				res.sendRedirect(req.getContextPath() + "/front_end/room/booking.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}

		if ("roomCheck".equals(action)) {
			out = res.getWriter();
			try {
				String type_no = req.getParameter("type_no");
				String date = req.getParameter("date");
				Integer stay = Integer.parseInt(req.getParameter("stay"));
				Integer qty = Integer.parseInt(req.getParameter("qty"));
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				RoomTypeService roomTypeSvc = new RoomTypeService();
				JSONObject jsonObj = new JSONObject();

				Integer rmLeft = rsvSvc.roomCheck(rsv_date, stay, Integer.parseInt(type_no));
				if (rmLeft >= qty) {
					jsonObj.put("type_no", rmLeft);
					jsonObj.put("Zext", rsv_date.plusDays(1L));
				} else if (rmLeft == 0) {
					jsonObj.put("Zext", rsv_date.plusDays(stay));
					jsonObj.put("isMam", "true");
				} else {
					jsonObj.put("Zext", rsv_date.plusDays(stay));
					jsonObj.put("isFull", "true");
				}
				out.print(jsonObj);

				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}

	}
}
