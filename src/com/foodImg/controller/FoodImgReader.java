package com.foodImg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodImg.model.FoodImgService;
import com.foodImg.model.FoodImgVO;


@WebServlet("/FoodImgReader")
public class FoodImgReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/jpeg");
		Integer fd_img_no = new Integer(req.getParameter("fd_img_no").trim());
		
		FoodImgService vic = new FoodImgService();
		
		ServletOutputStream out = res.getOutputStream();
	    out.write(vic.findByPk(fd_img_no).getFd_img());
	    out.close();
		  	
	}


}
