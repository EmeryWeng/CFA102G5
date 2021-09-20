package com.activityOrder.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activityOrder.model.ActivityOrderService;
import com.activityOrder.model.ActivityOrderVO;


public class ActivityOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		ActivityOrderService actOrderService = new ActivityOrderService();
		
		//查看訂單列表
		if("getAll".equals(action)) {
			
			request.getRequestDispatcher("/back_end/activity/actOrder/selectActOrder.jsp")
			.forward(request, response);
			return;
		}
		
		//新增待做
	}

}
