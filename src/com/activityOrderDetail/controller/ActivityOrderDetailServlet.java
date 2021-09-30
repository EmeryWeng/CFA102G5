package com.activityOrderDetail.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.activityOrderDetail.model.ActivityOrderDetailService;
import com.activityOrderDetail.model.ActivityOrderDetailVO;
import com.activitySession.model.ActivitySessionService;
import com.activitySession.model.ActivitySessionVO;
import com.google.gson.Gson;


public class ActivityOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ActivityOrderDetailServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		ActivityOrderDetailService actOrderDetailService = new ActivityOrderDetailService();
		ActivitySessionService actSessionService = new ActivitySessionService();
logger.info(action);
		
//		來自前台人數檢查
		if("checkSessionPeopleNumber".equals(action)) {
			Integer act_session_no = new Integer(request.getParameter("sessionNo"));

			Integer act_people_number = actOrderDetailService
								 .getActOrderDetailByActSessionNo(act_session_no)
								 .stream().mapToInt(detail -> detail.getAct_real_join_number())
								 .sum();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(act_people_number));
		}
		
		
//	後台	
		if("getAll".equals(action)) {
			
			request.getRequestDispatcher("/back_end/activity/actOrderDetail/selectActOrderDetail.jsp")
			.forward(request, response);
			return;
		}
		
		if("switchActOrderDetailState".equals(action)) {
			Integer act_order_detail_no = new Integer(request.getParameter("updateActOrderDetailNo").trim());
			Integer act_order_detail_state = new Integer(request.getParameter("switchActOrderDetailStateSelect").trim());
			
			actOrderDetailService.switchOrderDetailState(act_order_detail_no, act_order_detail_state);
			
			request.getRequestDispatcher("/back_end/activity/actOrderDetail/selectActOrderDetail.jsp")
			.forward(request, response);
			return;
		}
		
		
		
		if("updateActOrderDetail".equals(action)) {
			//要修改的明細PK
			Integer act_order_detail_no = new Integer(request.getParameter("updateActOrderDetailNo"));
			ActivityOrderDetailVO actOrderDetailVO = actOrderDetailService.getActOrderDetailByPk(act_order_detail_no);
			//該筆明細的場次PK
			Integer act_session_no = new Integer(request.getParameter("actSessionNo"));
			//findByPk -> 活動場次VO
			ActivitySessionVO actSessionVO = actSessionService.getActSessionByPk(act_session_no);
			//找到該場次下的活動 會有多筆
			List<ActivitySessionVO> actSessionListByActNo = actSessionService.getActSessionByActNo(actSessionVO.getAct_no());
			
			Integer actNo = actSessionListByActNo.stream()
					.mapToInt(session -> session.getAct_no()).distinct()
					.findFirst().getAsInt();
						
			request.setAttribute("actNo", actNo);
			request.setAttribute("actSessionVO", actSessionVO);
			request.setAttribute("actOrderDetailVO", actOrderDetailVO);	
			request.setAttribute("actSessionListByActNo", actSessionListByActNo);	
			request.getRequestDispatcher("/back_end/activity/actOrderDetail/updateActOrderDetail.jsp")
			.forward(request, response);
			return;
		}
		
		if("checkChangeSession".equals(action)) {
			//要更換的場次編號
			Integer change_act_session_no = new Integer(request.getParameter("changeSessionNo"));
			//哪筆訂單  訂單編號與場次編號 形成該筆的訂單明細編號
			Integer act_order_no = new Integer(request.getParameter("orderNo"));
			
			//可選擇要改場次的人數，確認人數(要更換的) 但有可能明細沒有該場次 這時候就直接動場次
			Integer change_act_people_number = new Integer(request.getParameter("changeActPeopleSelect"));
			
			Integer change_session_act_real_join_number = null;
			try {
				change_session_act_real_join_number = actOrderDetailService.getAll().stream()
						.filter(detail -> detail.getAct_order_no() == act_order_no.intValue() && detail.getAct_session_no() == change_act_session_no.intValue())
						.mapToInt(detail -> detail.getAct_real_join_number())
						.findFirst()
						.getAsInt();
			}catch(NoSuchElementException ex) {
				change_session_act_real_join_number = 0;

			}		
			
			Gson gson = new Gson();
			
			if(change_act_people_number + change_session_act_real_join_number <= 10) {
				response.getWriter().write(gson.toJson(true));
			}else {
				response.getWriter().write(gson.toJson(false));
			}
			
		}
		
		if("updateActOrderDetailSure".equalsIgnoreCase(action)) {
			//活動場次單價
			Integer act_session_price = new Integer(request.getParameter("actSessionPrice"));
			//實際報名人數(原本的場次)
			Integer old_act_session_people_number = new Integer(request.getParameter("changeActPeopleSelect"));			
				
			//原本的場次編號
			Integer old_act_session_no = new Integer(request.getParameter("updateActSessionNo"));
			
			//要更換的場次(不存在的情況要注意)
			Integer change_act_session_no = new Integer(request.getParameter("actSessionTimeSelect"));

			//訂單編號
			Integer act_order_no = new Integer(request.getParameter("updateOrderNo"));
			
			//要換過去的場次的人數
			Integer change_act_people_number = null;
			//總人數
			Integer totalPeople = null;
			//總金額
			Integer act_price_total = null;
			try {
			
			change_act_people_number = actOrderDetailService.getAll()
									.stream().filter(detail -> detail.getAct_order_no() == act_order_no.intValue() && detail.getAct_session_no() == change_act_session_no.intValue())
								 	.mapToInt(detail -> detail.getAct_real_join_number())
								 	.findFirst()
								 	.getAsInt();
			
			totalPeople = old_act_session_people_number+change_act_people_number;

			act_price_total = act_session_price * totalPeople;

			actOrderDetailService.orderDetailUpdate(totalPeople, act_price_total, act_order_no, change_act_session_no);

			}catch(NoSuchElementException ex) {

				act_price_total = old_act_session_people_number * act_session_price;
				actOrderDetailService.addActOrderDetail(act_order_no, change_act_session_no, old_act_session_people_number, act_session_price, 0, act_price_total, 1);
			}
//同時也要扣除相對應明細的人數以及總金額
			totalPeople = actOrderDetailService.getAll().stream()
					 .filter(detail -> detail.getAct_order_no() == act_order_no.intValue() && detail.getAct_session_no() == old_act_session_no.intValue())
					 .mapToInt(detail -> detail.getAct_real_join_number())
					 .findFirst()
					 .getAsInt();
			
			totalPeople -= old_act_session_people_number;
	
			Integer old_act_price_total = totalPeople * act_session_price;
			actOrderDetailService.orderDetailUpdate(totalPeople, old_act_price_total, act_order_no, old_act_session_no);


			request.getRequestDispatcher("/back_end/activity/actOrderDetail/selectActOrderDetail.jsp")
			.forward(request, response);
			return;
		}
		
		//根據分類查詢 (已付款、已取消、已改期)
		if("canceled".equals(action)) {
			List<ActivityOrderDetailVO> selectByState = actOrderDetailService.getActOrderDetailByState(2);
			
			request.setAttribute("selectByState",selectByState);
			request.getRequestDispatcher("/back_end/activity/actOrderDetail/selectActOrderDetailByState.jsp")
			.forward(request, response);
			return;
		}
		
		if("changeDate".equals(action)) {
			List<ActivityOrderDetailVO> selectByState = actOrderDetailService.getActOrderDetailByState(3);
			
			request.setAttribute("selectByState",selectByState);
			request.getRequestDispatcher("/back_end/activity/actOrderDetail/selectActOrderDetailByState.jsp")
			.forward(request, response);
			return;
		}
	}

}
