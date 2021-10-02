package com.roomRsv.controller;

import java.io.IOException;
import java.util.Arrays;
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

		// 從首頁搜尋，房型列表顯示符合的結果
		if ("getEnoughType".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String rangedate = req.getParameter("rangedate");
			Integer qty = new Integer(req.getParameter("qty"));
			Integer guest = new Integer(req.getParameter("guest"));

			// 將收到的住宿期間分割成 起始日 和 結束日
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(rangedate.split(" to "));
			String start_date = dateList.get(0);
			String end_date = dateList.get(1);

			/*************************** 2.開始查詢資料 ****************************************/
			RoomTypeService roomTypeSvc = new RoomTypeService();
			// 符合條件的
			List<RoomTypeVO> ableList = roomTypeSvc.getEnoughType(java.sql.Date.valueOf(start_date),
					java.sql.Date.valueOf(end_date), qty, guest);
			// 不符合條件的
			List<RoomTypeVO> notList = roomTypeSvc.getNotEnoughType(java.sql.Date.valueOf(start_date),
					java.sql.Date.valueOf(end_date), qty, guest);

			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("qty", qty);
			session.setAttribute("guest", guest);
			session.setAttribute("ableList", ableList);
			session.setAttribute("notList", notList);
			String url = "/front_end/room/roomList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的roomList.jsp
			successView.forward(req, res);
		}

		if ("payment".equals(action)) {
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

			String url = "/front_end/room/payment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的writeInfo.jsp
			successView.forward(req, res);
		}

	}
}
