package com.roomImg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
		res.setContentType("img/jepg");
		String action = req.getParameter("action");		
		
		// 查看該房型的全部圖片
		if ("getAllByType".equals(action)) {
			Integer type_no = new Integer(req.getParameter("type_no"));
			
			RoomImgService roomImgSvc = new RoomImgService();
			List<RoomImgVO> list = roomImgSvc.getAllByType(type_no);

			byte[] type_img = list.get(0).getType_img();
			ServletOutputStream out = res.getOutputStream();
			out.write(type_img);
			out.close();
		}
		
		// 刪除圖片
		if ("deleteRoomImg".equals(action)) {
		}
		
	}

}
