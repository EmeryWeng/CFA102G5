package com.room.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.room.model.*;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class RoomServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("getAllByRmState".equals(action)) { ********切換
//
//			/***************************1.接收請求參數****************************************/
//			Integer rm_state = new Integer(req.getParameter("rm_state"));
//				
//			/***************************2.開始查詢資料****************************************/
//			RoomService roomSvc = new RoomService();
//			List<RoomVO> list = roomSvc.getAllRoom();
//								
//			/***************************3.查詢完成,準備轉交(Send the Success view)************/
//			req.setAttribute("list", list);         // 資料庫取出的VO物件,存入req
//			String url = "/back_end/room/listAllRoom.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
		
		if ("getOneForUpdate".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String rm_no = req.getParameter("rm_no");
				
				/***************************2.開始查詢資料****************************************/
				RoomService roomSvc = new RoomService();
				RoomVO roomVO = roomSvc.getOneRoom(rm_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("roomVO", roomVO);         // 資料庫取出的VO物件,存入req
				String url = "/back_end/room/updateRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("失敗");
			}
		}
		
	}
}
