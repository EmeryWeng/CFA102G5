package com.activity.model;

import java.util.List;
import java.util.Map;

public class ActivityService {
	private I_ActivityDAO dao;
	
	public ActivityService() {
		dao = new ActivityDAO();
	}
	
	public ActivityVO addAct(ActivityVO actVO) {
		return dao.insert(actVO);
	}
	
	public void updateAct(ActivityVO actVO) {
		dao.update(actVO);
	}
	
	public ActivityVO getActByPk(Integer act_no){
		return dao.findByPk(act_no);
	}
	
	public List<ActivityVO> getActByName(String act_name){
		return dao.findByName(act_name);
	}
	
	public List<ActivityVO> getActByClassNo(Integer act_class_no){
		return dao.findByActClassNo(act_class_no);
	}
	
	public Integer getActJoinNumberByPk(Integer act_no){
		return dao.getJoinNumber(act_no);
	}
	
	public List<ActivityVO> getPopularAct(){
		return dao.getPopularAct();
	}
	
	public List<ActivityVO> getShowActToFront(){
		return dao.getActToFront();
	}
	
	public List<ActivityVO> getAllAct(){
		return dao.getAll();
	}
	
	public Map<String,String[]> getActJoinActClass(){
		return dao.getActJoinActClass();
	}
}
