package com.roomImg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

public class RoomImgServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");		
		
		// 查看該房型的全部圖片 要怎麼傳入編號
		if ("getAllByType".equals(action)) {
			Integer type_no = new Integer(req.getParameter("type_no"));
			
			RoomImgService roomImgSvc = new RoomImgService();
			List<RoomImgVO> imgList = roomImgSvc.getAllByType(type_no);

			req.setAttribute("imgList", imgList);
			req.setAttribute("type_no", type_no); 
			
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/roomType/listAllRoomType.jsp");
			successView.forward(req, res);
			return;
		}
		// 顯示圖片
		if("showFirstImages".equals(action)) {
			res.setContentType("image/jpeg");
//			1.取參數
			Integer type_no= new Integer(req.getParameter("type_no"));
//			2.查圖片
			RoomImgService roomImgSvc = new RoomImgService();
			List<RoomImgVO> imgList = roomImgSvc.getAll();
			
			Optional<RoomImgVO> firstImages = null;
			firstImages = imgList.stream()
					   .filter(i -> i.getType_no().intValue() == type_no.intValue())
					   .findFirst();
//			3.輸出圖片
			byte[] content = firstImages.get().getType_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(content);
			out.close();
			return;
		}
		
		// 刪除圖片
		if ("deleteRoomImg".equals(action)) {
		}
		
	}

}
