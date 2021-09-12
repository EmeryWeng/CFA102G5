package com.activity.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activityEvaluation.model.ActivityEvaluationService;
import com.activityOrderDetail.model.ActivityOrderDetailService;

public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		ActivityService actService = new ActivityService(); //活動
		ActivityEvaluationService actEvaluationService = new ActivityEvaluationService(); //活動評價
		ActivityOrderDetailService actOrderDetailService = new ActivityOrderDetailService(); //活動訂單明細
		final Integer act_join_number = 10; //可參與人數
		final String act_location = "臺灣-花蓮"; //活動地點
		Boolean act_state = true;
		Integer act_evaluation_number = (int)actEvaluationService.getAll()	//活動評價總人數
											.stream().count();
		
		Integer act_sell_number = (int)actOrderDetailService.getAll()
											.stream().filter(orderDetail -> orderDetail.getAct_order_detail_state() != 2)
											.count(); //寫錯的 之後等活動場次來 才能追加 同場活動 計算累計人數
		Double act_average_star_number = (double)actEvaluationService.getAll().stream()
															.mapToInt(actEva -> actEva.getAct_evaluation_star_number())
															.sum()/act_evaluation_number; 
//											活動評價總星數 / 活動有平價的人數 = 活動平均星數
		
		
		List<Integer> actClassNoList = actService.getAll().stream()
				.map(act -> act.getAct_class_no()).distinct()
				.collect(Collectors.toList());
		
//		第一次點擊新增時
		if("beginActAdd".equals(action)) {
			request.setAttribute("addAct_actClassNoList", actClassNoList);
			request.getRequestDispatcher("/back_end/activity/act/addAct.jsp")
			.forward(request, response);

			return;
		}
		
//		查詢某活動類別
		if("queryByActClass".equals(action)) {
			Integer queryId = new Integer(request.getParameter("queryActClass"));
			List<ActivityVO> list = actService.getAll().stream()
									.filter(act -> act.getAct_class_no() == queryId.intValue())
									.collect(Collectors.toList());

			request.setAttribute("selectAct_queryList",list); //根據活動類別查詢完的
			request.getRequestDispatcher("/back_end/activity/act/queryAct.jsp")
				   .forward(request, response);
			return;
		}
		
//		切換上下架狀態		
		if("switchActState".equals(action)) {
			Integer act_no = new Integer(request.getParameter("updateActNo").trim()); //為了跟修改一樣 回到當筆 就用一樣變數名稱
			Boolean actState = actService.getActByPk(act_no).getAct_state() == true ? false : true;
			actService.switchActState(act_no, actState);

			request.getRequestDispatcher("/back_end/activity/act/selectAct.jsp")
				   .forward(request, response);
			return;
		}
		
//		一開始從sidebar 查全部  -> selectAct.jsp
		if("getAll".equals(action)) {
			
			request.getRequestDispatcher("/back_end/activity/act/selectAct.jsp")
				   .forward(request, response);
			return;
		}
		
		//進入修改表單
		if("updateAct".equals(action)) {
			Integer act_no = new Integer(request.getParameter("updatePk").trim());
			ActivityVO actVO = actService.getActByPk(act_no);
			// 修改完 返回當筆
			int whichPage = 0;
			if((act_no%5) == 0) {
				whichPage = act_no/5;
			}else {
				whichPage = (act_no/5)+1;
			}
			String requestURL = "/act/selectAct.jsp";
		
			request.setAttribute("requestURL",requestURL);
			request.setAttribute("whichPage",whichPage);
			request.setAttribute("updateAct_actVO",actVO); 
			request.getRequestDispatcher("/back_end/activity/act/updateAct.jsp")
				   .forward(request, response);
			return;
		}
		
		//修改表單送出後
		if("updateActSure".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			Integer act_class_no = null;
			Integer act_no = null;
			
			try {
//				活動編號(修改用)
				act_no = new Integer(request.getParameter("updateActNo").trim());
				
//				活動類別編號
				act_class_no = new Integer(request.getParameter("actClassNoSelect").trim());
		
//				活動名稱
				String act_name = request.getParameter("actName").trim();			
				if(act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動名稱:請勿空白");
				}else if(!act_name.matches("^[\\u4E00-\\u9FA5]+[a-zA-Z0-9]*")) {
					errorMsgs.add("活動名稱:開頭請輸入中文");
				}
				
//				活動價格
				String actPrice = request.getParameter("actPrice").trim();
					if(actPrice == null || actPrice.length() == 0) {
						errorMsgs.add("活動價格:請勿空白");
					}else if(!actPrice.matches("[0-9]+")) {
						errorMsgs.add("活動價格:請填數字");
					}
					
//				活動說明
				String act_instruction = request.getParameter("actInstruction").trim();			
					if(act_instruction == null || act_instruction.length() == 0) {
						errorMsgs.add("活動說明:請勿空白");
					}	
	
//				活動行程時間
				String actScheduleTime = request.getParameter("actScheduleTime").trim();
					if(actScheduleTime == null || actScheduleTime.length() == 0) {
						errorMsgs.add("活動行程時間:請勿空白");
					}else if(!actScheduleTime.matches("[0-9]+")) {
						errorMsgs.add("活動行程時間:請填數字");
					}									
				
//				活動集合地點
				String act_gather_location = request.getParameter("actGatherLocation").trim();
				
					if(act_gather_location == null || act_gather_location.length() == 0) {
						errorMsgs.add("活動集合地點:請勿空白");
					}else if(!act_gather_location.matches("^[\\u4E00-\\u9FA5]+[0-9]{0,3}[\\u4E00-\\u9FA5]{1,}")) {
						errorMsgs.add("活動集合地點:開頭請輸入中文");
					}
				
//				活動地點的經度
				String actLocationLongitude = request.getParameter("actLocationLongitude").trim();
					if(actLocationLongitude == null || actLocationLongitude.length() == 0) {
						errorMsgs.add("活動地點的經度:請勿空白");
					}else if(!actLocationLongitude.matches("^\\d{3}.\\d{7}$")) {
						errorMsgs.add("活動地點的經度不符合格式 :數字{3} . 數字{7}");
					}													
				
//				活動地點的緯度
				String actLocationLatitude = request.getParameter("actLocationLatitude").trim();
				if(actLocationLatitude == null || actLocationLatitude.length() == 0) {
					errorMsgs.add("活動地點的緯度:請勿空白");
				}else if(!actLocationLatitude.matches("^\\d{2}.\\d{7}$")) {
					errorMsgs.add("活動地點的緯度不符合格式 :數字{2} . 數字{7}");
				}							
					
				
				Integer act_price = null;	
				act_price = new Integer(actPrice);
				
				Integer act_schedule_time = null;	
				act_schedule_time = new Integer(actScheduleTime);
				
				Double act_location_longitude = null;
				act_location_longitude = new Double(actLocationLongitude);
				
				Double act_location_latitude = null;
				act_location_latitude = new Double(actLocationLatitude);
							
				
//				若有錯誤			
				if(!errorMsgs.isEmpty()) {
//					Integer selectedNumber = act_class_no;
					ActivityVO actVO = actService.getActByPk(act_no);					
					request.setAttribute("updateAct_actVO", actVO);//修改失敗 保留一開始的內容
//					request.setAttribute("addAct_selected",selectedNumber); //新增失敗時 存當下選取的select value
					request.getRequestDispatcher("/back_end/activity/act/updateAct.jsp")
					.forward(request, response);
					return;
				}
						
			//活動評價總人數、活動平均星數  要靠撈活動評價table 以及 會員評價總星數/活動評價總人數			
			actService.updateAct(act_no,act_class_no, act_name, act_price, act_location, act_schedule_time, act_instruction, act_gather_location, act_location_longitude, act_location_latitude, act_sell_number, act_join_number, act_evaluation_number, act_average_star_number, act_state);
			//修改時 欄位無誤的情況	-> selectAct.jsp

			request.getRequestDispatcher("/back_end/activity/act/selectAct.jsp")
				   .forward(request, response);
			return;
			
			}catch(Exception ex) {
				errorMsgs.add("修改失敗:"+ex.getMessage());
				ActivityVO actVO = actService.getActByPk(act_no);
				//修改失敗 回到該筆的內容
				request.setAttribute("updateAct_actVO", actVO);
				request.getRequestDispatcher("/back_end/activity/act/updateAct.jsp")
					   .forward(request, response);
				return;
			}
		}
		
		
//			新增開始
		if("addAct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			Integer act_class_no = null;
			ActivityVO actVO = new ActivityVO();
			
			String act_name = null;
			Integer act_price = null;
			String act_instruction = null;
			Integer act_schedule_time = null;
			String act_gather_location = null;
			Double act_location_longitude = null;
			Double act_location_latitude = null;
			
			try {
//				活動類別編號
				act_class_no = new Integer(request.getParameter("actClassNoSelect").trim());
			
//				活動名稱
				act_name = request.getParameter("actName");			
				if(act_name == null || act_name.trim().length() == 0) {
					errorMsgs.add("活動名稱:請勿空白");
				}else if(!act_name.matches("^[\\u4E00-\\u9FA5]+[a-zA-Z0-9]*")) {
					errorMsgs.add("活動名稱:開頭請輸入中文");
				}
				
//				活動價格
				String actPrice = request.getParameter("actPrice").trim();
					if(actPrice == null || actPrice.length() == 0) {
						errorMsgs.add("活動價格:請勿空白");
					}else if(!actPrice.matches("[0-9]+")) {
						errorMsgs.add("活動價格:請填數字");
					}
					
//				活動說明
				act_instruction = request.getParameter("actInstruction").trim();
					if(act_instruction == null || act_instruction.length() == 0) {
						errorMsgs.add("活動說明:請勿空白");
					}	
	
//				活動行程時間
				String actScheduleTime = request.getParameter("actScheduleTime").trim();
					if(actScheduleTime == null || actScheduleTime.length() == 0) {
						errorMsgs.add("活動行程時間:請勿空白");
					}else if(!actScheduleTime.matches("[0-9]+")) {
						errorMsgs.add("活動行程時間:請填數字");
					}									
				
//				活動集合地點
				act_gather_location = request.getParameter("actGatherLocation").trim();
					if(act_gather_location == null || act_gather_location.length() == 0) {
						errorMsgs.add("活動集合地點:請勿空白");
					}else if(!act_gather_location.matches("^[\\u4E00-\\u9FA5]+[0-9]{0,3}[\\u4E00-\\u9FA5]{1,}")) {
						errorMsgs.add("活動集合地點:開頭請輸入中文");
					}
				
//				活動地點的經度
				String actLocationLongitude = request.getParameter("actLocationLongitude").trim();
					if(actLocationLongitude == null || actLocationLongitude.length() == 0) {
						errorMsgs.add("活動地點的經度:請勿空白");
					}else if(!actLocationLongitude.matches("^\\d{3}.\\d{7}$")) {
						errorMsgs.add("活動地點的經度不符合格式 :數字{3} . 數字{7}");
					}													
				
//				活動地點的緯度
			String actLocationLatitude = request.getParameter("actLocationLatitude").trim();
				if(actLocationLatitude == null || actLocationLatitude.length() == 0) {
					errorMsgs.add("活動地點的緯度:請勿空白");
				}else if(!actLocationLatitude.matches("^\\d{2}.\\d{7}$")) {
					errorMsgs.add("活動地點的緯度不符合格式 :數字{2} . 數字{7}");
				}	
				
				act_price = new Integer(actPrice);
				
				act_schedule_time = new Integer(actScheduleTime);
				
				act_location_longitude = new Double(actLocationLongitude);
				
				act_location_latitude = new Double(actLocationLatitude);
				
	
//				若有錯誤
			if(!errorMsgs.isEmpty()) {
				actVO.setAct_name(act_name);
				actVO.setAct_price(act_price);
				actVO.setAct_instruction(act_instruction);
				actVO.setAct_schedule_time(act_schedule_time);
				actVO.setAct_gather_location(act_gather_location);
				actVO.setAct_location_longitude(act_location_longitude);
				actVO.setAct_location_latitude(act_location_latitude);
				
				Integer selectedNumber = act_class_no;
				
				request.setAttribute("addAct_actVO",actVO);
				request.setAttribute("addAct_actClassNoList", actClassNoList);//新增失敗 把處理好的List(全部act_class_no)放進去request範圍當中
				request.setAttribute("addAct_selected",selectedNumber); //新增失敗時 存當下選取的select value
				request.getRequestDispatcher("/back_end/activity/act/addAct.jsp")
					   .forward(request, response);
				return;
			}
			
			
			//活動評價總人數、活動平均星數  要靠撈活動評價table 以及 會員評價總星數/活動評價總人數
			actService.addAct(act_class_no, act_name, act_price, act_location, act_schedule_time, act_instruction, act_gather_location, act_location_longitude, act_location_latitude, act_sell_number, act_join_number, act_evaluation_number, act_average_star_number, act_state);
		
			//新增時 欄位無誤的情況	-> selectAct.jsp
			
			request.getRequestDispatcher("/back_end/activity/act/selectAct.jsp")
				   .forward(request, response);
			return;
			
			}catch(Exception ex) {
				errorMsgs.add("新增失敗:"+ex.getMessage());
				Integer selectedNumber = act_class_no;
				
				actVO.setAct_name(act_name);
				actVO.setAct_price(act_price);
				actVO.setAct_instruction(act_instruction);
				actVO.setAct_schedule_time(act_schedule_time);
				actVO.setAct_gather_location(act_gather_location);
				actVO.setAct_location_longitude(act_location_longitude);
				actVO.setAct_location_latitude(act_location_latitude);
				request.setAttribute("addAct_actVO",actVO);
				
				//新增失敗 把處理好的List(全部act_class_no)放進去request範圍當中
				request.setAttribute("addAct_actClassNoList", actClassNoList);
				//新增失敗時 存當下選取的select value
				request.setAttribute("addAct_selected",selectedNumber);
				request.getRequestDispatcher("/back_end/activity/act/addAct.jsp")
					   .forward(request, response);
				return;
			}
		}
	}
}
