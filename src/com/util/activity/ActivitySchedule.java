package com.util.activity;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activityOrder.model.ActivityOrderService;
import com.activityOrderDetail.model.ActivityOrderDetailService;
import com.activityOrderDetail.model.ActivityOrderDetailVO;
import com.activitySession.model.ActivitySessionService;
import com.activitySession.model.ActivitySessionVO;
import com.member.model.MemberClassVO;
import com.member.model.MemberService;

public class ActivitySchedule extends TimerTask{
	
	@Override
	public void run() {
		SendMail mail = new SendMail();
		Map<String,String> map = new HashMap<>();	
		
System.out.println("正在執行任務============");		
		ActivitySessionService sessionService = new ActivitySessionService();
		ActivityOrderService orderService = new ActivityOrderService();
		ActivityOrderDetailService orderDetailService = new ActivityOrderDetailService();
		MemberService memService = new MemberService();
		ActivityService actService = new ActivityService();
		//過濾掉 明細狀態為2(已取消的情況)
		List<ActivityOrderDetailVO> detailList = orderDetailService.getAll()
												.stream().filter(detail -> detail.getAct_order_detail_state() != 2)
												.collect(Collectors.toList());
		//抓明細
		for(ActivityOrderDetailVO datailVO : detailList) {
			//抓場次vo
			ActivitySessionVO sesionVO = sessionService.getActSessionByPk(datailVO.getAct_session_no());
			LocalDate start_date = sesionVO.getAct_session_start_date(); //已結帳下的場次的開始日期
			LocalDate now = LocalDate.now();
			
			LocalTime start_time = sesionVO.getAct_session_start_time();
			
System.out.println("Now:(day)"+now);
System.out.println("資料庫明細的日期:"+ start_date);
			Period period = Period.between(now,start_date);
System.out.println("相差天數:"+ period.getDays());		
			if(period.getDays() == 1) {
System.out.println("抓到剩餘天數==1");				
				
				Integer order_no = datailVO.getAct_order_no();
				
				ActivityVO act_vo = actService.getActByPk(sesionVO.getAct_no());
				String act_name = act_vo.getAct_name();
				String act_gather_location = act_vo.getAct_gather_location();
				
				Integer mem_no = orderService.getActOrderByPk(order_no).getMem_no();
				MemberClassVO mem = memService.getOne(mem_no);
				String email = mem.getMem_mail();
				String mem_name = mem.getMem_name();
				
				Integer session_no = sesionVO.getAct_session_no();
				
				String amOrPm = start_time.compareTo(LocalTime.of(12,0)) == -1 ? "上午" : "下午";
				
				String messageText = "親愛的 "+ mem_name + " 您好，您訂購的 " + act_name + " 活動於 "
								   + start_date + " " + amOrPm + " " + start_time + " 開始，請前往 "
								   + act_gather_location +" 集合，謝謝您，祝您遊玩愉快!";
				
				if(!map.containsKey(email) && !map.containsValue(String.valueOf(session_no))) {
					map.put("email", email);
System.out.println("map"+map);					
					map.put("sessionNo",String.valueOf(session_no));
					mail.sendMail(map.get("email"),"Feliz Hotel提醒您活動快開始了", messageText);
System.out.println("已通知該會員: "+email + " 於場次 " + session_no);
				}

System.out.println("此輪任務已結束==========");
			}
		}		
	}
}
