package com.activityOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.activity.model.ActivityService;
import com.activityOrder.model.ActivityOrderService;
import com.activityOrder.model.OrderVO;
import com.creditcard.model.CreditcardService;
import com.google.gson.Gson;
import com.member.model.MemberClassVO;
import com.member.model.MemberService;


public class ActivityOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		ActivityOrderService actOrderService = new ActivityOrderService();
		ActivityService actService = new ActivityService();
System.out.println("action:"+action);		
		
//		同會員
		if("sameAsMember".equals(action)) {
			String mem_email = (String)request.getSession().getAttribute("mem_mail");
			response.setCharacterEncoding("UTF-8");
			MemberService memberService = new MemberService();
			CreditcardService creditCardService = new CreditcardService();
			MemberClassVO memberVO = memberService.getOneBymail(mem_email);
			String cardNumber = creditCardService.getallByMem_no(memberVO.getMem_no())
								.stream().map(card -> card.getCrd_num())
								.findFirst().get();

			
			OrderVO vo = new OrderVO();
			vo.setMem_title(memberVO.getMem_sex() == 1 ? "先生" : "女士");
			vo.setMem_name(memberVO.getMem_name());
			vo.setMem_phone(memberVO.getMem_mobile());
			vo.setMem_email(memberVO.getMem_mail());
			vo.setMem_creditCard(cardNumber);
System.out.println(vo);			
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(vo));
			out.close();
		}


//		前台立即結帳頁面
		if("immediateCheckout".equals(action)) {
			request.getRequestDispatcher("/front_end/activity/checkout.jsp")
			.forward(request, response);
			
			return;
		}

//		購物車開始
		if("actShoppingCart".equals(action)) {
			HttpSession session = request.getSession();
			List<Map<String,String>> list = (List<Map<String,String>>)session.getAttribute("shoppingCar");
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			Map<String,String> map = null;
			String car_action = request.getParameter("carAction");
System.out.println("對購物車的操作是:"+car_action);

			if("add".equals(car_action)) {
		
				String act_no = request.getParameter("actNo").trim();
				String act_name = request.getParameter("actNameInCar").trim();
				String act_date = request.getParameter("actDateInCar").trim();
				String act_session_start_time = request.getParameter("actTime").trim();
				String act_people_number = request.getParameter("actPeopleNumber").trim();
				
				String act_price = String.valueOf(actService.getActByPk(new Integer(act_no)).getAct_price());
				
System.out.println(act_name);			
System.out.println(act_date);			
System.out.println(act_session_start_time);			
System.out.println(act_people_number);
System.out.println("上半部為一開始");
				
System.out.println("現有List狀況:" + list);				
//				一開始為空
				if(list == null && map == null) {
					list = new ArrayList<>();
					map = new HashMap<>();
					map.put("act_no",act_no);
					map.put("act_name",act_name);
					map.put("act_date",act_date);
					map.put("act_session_start_time",act_session_start_time);
					map.put("act_people_number",act_people_number);
					map.put("act_price",act_price);
					list.add(map);
					
				}else {

System.out.println("目前list:"+list == null );
System.out.println("目前list是否為空:"+ !list.isEmpty() );
					if (!list.isEmpty()) { // 不先檢查 刪光的情況get會死
						for (int index = 0; index < list.size(); index++) {
							if (list.get(index).get("act_session_start_time").equals(act_session_start_time)
									&& list.get(index).get("act_date").equals(act_date)) {
								System.out.println("重複已發生");
								map = list.get(index);
								list.remove(index);
								map.put("act_no", act_no);
								map.put("act_name", act_name);
								map.put("act_date", act_date);
								map.put("act_session_start_time", act_session_start_time);
								map.put("act_people_number", act_people_number);
								map.put("act_price", act_price);

								list.add(map);
								out.write(gson.toJson("update"));
							} else {
								map = new HashMap<>();
								map.put("act_no", act_no);
								map.put("act_name", act_name);
								map.put("act_date", act_date);
								map.put("act_session_start_time", act_session_start_time);
								map.put("act_people_number", act_people_number);
								map.put("act_price", act_price);

								list.add(map);
							}
						}
					}else {
						map = new HashMap<>();
						map.put("act_no", act_no);
						map.put("act_name", act_name);
						map.put("act_date", act_date);
						map.put("act_session_start_time", act_session_start_time);
						map.put("act_people_number", act_people_number);
						map.put("act_price", act_price);

						list.add(map);
					}
				}
System.out.println("map:"+ map);
				
				out.write(gson.toJson(list.size()));
System.out.println("==============");
System.out.println(list);
System.out.println("==============");
			}
			
//			清除某項目
			if("delete".equals(car_action)) {
			int deleteIndex = Integer.parseInt(request.getParameter("deleteIndex"));
				list.remove(deleteIndex);
System.out.println("清除某項目");					
System.out.println("清除過後的List"+list);
			}
			
//			清除購物車
			if("deleteAll".equals(car_action)) {
				if(list != null) {
					list.clear();
System.out.println("已清空");				
				}
			}
			
			
System.out.println("************************");			
System.out.println("現有的list狀態"+ list);			
System.out.println("************************");			
			
			
			session.setAttribute("shoppingCar",list);
			
		}
		
		if("lookShoppingCart".equals(action)) {
			request.getRequestDispatcher("/front_end/activity/shoppingCar.jsp")
			.forward(request, response);
			
			return;
		}
		
		//查看訂單列表
		if("getAll".equals(action)) {
			
			request.getRequestDispatcher("/back_end/activity/actOrder/selectActOrder.jsp")
			.forward(request, response);
			return;
		}
		
		//新增待做
	}

}

