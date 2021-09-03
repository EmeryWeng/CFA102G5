package com.roomType.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.roomType.model.*;

public class RoomTypeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne".equals(action)) { 
			String str = req.getParameter("type_no");
			Integer type_no = new Integer(str);

			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(type_no);

			req.setAttribute("roomTypeVO", roomTypeVO);
			String url = "/room/listOneRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}
}
