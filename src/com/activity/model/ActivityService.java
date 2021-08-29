package com.activity.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityService {
	private I_ActivityDAO dao;
	
	public ActivityService() {
		dao = new ActivityDAO();
	}
	
	public ActivityVO addAct(String act_class_no,String act_name,
			String act_price,String act_location,String act_schedule_time,
			String act_instruction,String act_gather_location,
			String act_location_longitude,String act_location_latitude,
			String act_sell_number,String act_join_number,
			String act_evaluation_number,String act_average_star_number,
			String act_state) {
		
		ActivityVO vo = new ActivityVO();
		vo.setAct_class_no(new Integer(act_class_no));
		vo.setAct_name(act_name);
		vo.setAct_price(new Integer(act_price));
		vo.setAct_location(act_location);
		vo.setAct_schedule_time(new Integer(act_schedule_time));
		vo.setAct_instruction(act_instruction);
		vo.setAct_gather_location(act_gather_location);
		vo.setAct_location_longitude(new Double(act_location_longitude));
		vo.setAct_location_latitude(new Double(act_location_latitude));
		vo.setAct_sell_number(new Integer(act_sell_number));
		vo.setAct_join_number(new Integer(act_join_number));
		vo.setAct_evaluation_number(new Integer(act_evaluation_number));
		vo.setAct_average_star_number(new Double(act_average_star_number));
		vo.setAct_state(new Boolean(act_state));
		return dao.insert(vo);
	}
	
	public void updateAct(String act_no,String act_class_no,String act_name,
			String act_price,String act_location,String act_schedule_time,
			String act_instruction,String act_gather_location,
			String act_location_longitude,String act_location_latitude,
			String act_sell_number,String act_join_number,
			String act_evaluation_number,String act_average_star_number,
			String act_state) {
		
		ActivityVO vo = new ActivityVO();
		vo.setAct_no(new Integer(act_no));
		vo.setAct_class_no(new Integer(act_class_no));
		vo.setAct_name(act_name);
		vo.setAct_price(new Integer(act_price));
		vo.setAct_location(act_location);
		vo.setAct_schedule_time(new Integer(act_schedule_time));
		vo.setAct_instruction(act_instruction);
		vo.setAct_gather_location(act_gather_location);
		vo.setAct_location_longitude(new Double(act_location_longitude));
		vo.setAct_location_latitude(new Double(act_location_latitude));
		vo.setAct_sell_number(new Integer(act_sell_number));
		vo.setAct_join_number(new Integer(act_join_number));
		vo.setAct_evaluation_number(new Integer(act_evaluation_number));
		vo.setAct_average_star_number(new Double(act_average_star_number));
		vo.setAct_state(new Boolean(act_state));
		
		dao.insert(vo);
	}
	
	public ActivityVO getActByPk(String act_no){
		return dao.findByPk(new Integer(act_no));
	}
	
	public List<ActivityVO> getActByName(String act_name){
		return dao.findByName(act_name);
	}
	
	public List<ActivityVO> getActByClassNo(String act_class_no){
		return dao.findByActClassNo(new Integer(act_class_no));
	}
	
	public List<ActivityVO> getPopularAct(){
		return dao.getPopularAct();
	}
	//取得上架活動
	public List<ActivityVO> getAll(){
		return dao.getAll().stream()
				.filter(act -> act.getAct_state() == true)
				.collect(Collectors.toList());
	}
	
}
