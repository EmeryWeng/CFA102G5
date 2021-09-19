package com.roomImg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import com.roomImg.model.RoomImgService;
import com.roomImg.model.RoomImgVO;

@javax.servlet.annotation.MultipartConfig
public class RoomImgServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/jpeg");
		String action = req.getParameter("action");		
		
		// 顯示第一張圖片
		if("showFirstImages".equals(action)) {
			/***************************1.接收請求參數****************************************/
			Integer type_no= new Integer(req.getParameter("type_no"));
			
			/***************************2.開始查詢資料，篩選資料****************************************/
			RoomImgService roomImgSvc = new RoomImgService();
			List<RoomImgVO> images = roomImgSvc.getAllByType(type_no);
			
			Optional<RoomImgVO> firstImages = null;
			firstImages = images.stream().findFirst();
			
			/***************************3.輸出圖片************/
			byte[] content = firstImages.get().getType_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}
		// 連到房型圖片頁面,顯示該房型全部圖片
		if("getOneForShowImages".equals(action)) {
			/***************************1.接收請求參數****************************************/
			Integer type_no= new Integer(req.getParameter("type_no"));
			
			/***************************2.開始查詢資料，篩選資料****************************************/
			RoomImgService roomImgSvc = new RoomImgService();
			List<RoomImgVO> images = roomImgSvc.getAllByType(type_no);
			
			/***************************3.查詢完成,準備轉交************/
			req.setAttribute("images", images);
			req.setAttribute("type_no", type_no);

			String url = "/back_end/roomType/listOneTypeImages.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		// 顯示單張圖片
		if ("showImages".equals(action)) {
			/***************************1.接收請求參數****************************************/
			Integer img_no = new Integer(req.getParameter("img_no"));
			
			/***************************2.開始查詢資料****************************************/
			RoomImgService roomImgSvc = new RoomImgService();
			RoomImgVO roomImgVO = roomImgSvc.getOneRoomImg(img_no);

			/***************************3.輸出圖片************/
			byte[] content = roomImgVO.getType_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}
		
		// 刪除圖片
		if("delete".equals(action)) {
			/***************************1.接收請求參數********************/
			Integer img_no = new Integer(req.getParameter("img_no"));
			Integer type_no = new Integer(req.getParameter("type_no"));
			
			/***************************2.刪除圖片,查詢新的list************/
			RoomImgService roomImgSvc = new RoomImgService();
			roomImgSvc.deleteRoomImg(img_no);
			
			List<RoomImgVO> images = roomImgSvc.getAllByType(type_no);
			
			/***************************3.查詢完成,準備轉交************/
			req.setAttribute("images", images);
			req.setAttribute("type_no", type_no);

			String url = "/back_end/roomType/listOneTypeImages.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		// 新增圖片
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer type_no = new Integer(req.getParameter("type_no"));

			/***************************2.新增圖片,查詢新的list********************************/
			RoomImgService roomImgSvc = new RoomImgService();
			Collection<Part> parts = req.getParts();

			for(Part part : parts) {
				InputStream in = part.getInputStream();
				if(in.available()!=0 && part.getContentType()!=null) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					roomImgSvc.addRoomImg(type_no, buf);
				}else {
					errorMsgs.add("請選擇圖片新增");
				}
			}

			List<RoomImgVO> images = roomImgSvc.getAllByType(type_no);
			req.setAttribute("images", images);
			
			/**如果沒圖片**/
			req.setAttribute("type_no", type_no); 
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/roomType/listOneTypeImages.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************3.查詢完成,準備轉交************/
			String url = "/back_end/roomType/listOneTypeImages.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
	}

}
