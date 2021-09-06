package com.activityClass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.activityClass.model.ActivityClassService;
import com.activityClass.model.ActivityClassVO;

public class ActivityClassServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		ActivityClassService actClassService = new ActivityClassService();
		
		if("forwardAct".equals(action)) {
			response.sendRedirect(request.getContextPath()+"/back_end/activity/actIndex.jsp");
			return;
		}
		
		if("addActClass".equals(action)) {
			
			String act_class_name = request.getParameter("actClassName");
			ActivityClassVO actClassVO = actClassService.addActClass(act_class_name);
			request.setAttribute("actClassVO",actClassVO);
			
			request.getRequestDispatcher("/back_end/activity/actIndex.jsp")
			.forward(request, response);
			
			return;
		}
		
		if("updateActClass".equals(action)) {
			
		}
		
		if("switchActClassState".equals(action)) {
			
		}
		
		if("getAllActClass".equals(action)) {
			
			List<ActivityClassVO> list = actClassService.getAll();
			request.setAttribute("list",list);
			
			request.getRequestDispatcher("/back_end/activity/actIndex.jsp")
			.forward(request, response);
			
			return;
		}
		

	}
	
}