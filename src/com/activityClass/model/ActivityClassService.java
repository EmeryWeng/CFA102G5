package com.activityClass.model;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityClassService {
	private I_ActivityClassDAO dao;
	
	public ActivityClassService() {
		dao = new ActivityClassDAO();
	}
	
	public ActivityClassVO addActClass(String act_class_name,
			String act_class_state) {
		
		ActivityClassVO vo = new ActivityClassVO();
		vo.setAct_class_name(act_class_name);
		vo.setAct_class_state(new Boolean(act_class_state));
		return dao.insert(vo);
	}
	
	public ActivityClassVO addActClass(String act_class_no,
			String act_class_name,String act_class_state) {
		
		ActivityClassVO vo = new ActivityClassVO();
		vo.setAct_class_no(new Integer(act_class_no));
		vo.setAct_class_name(act_class_name);
		vo.setAct_class_state(new Boolean(act_class_state));
		
		return dao.insert(vo);
	}
	
	public ActivityClassVO getActClassByPk(String act_class_no) {
		return dao.findByPk(new Integer(act_class_no));
	}
	
	public List<ActivityClassVO> getAll() {
		return dao.getAll().stream()
				.filter(act -> act.getAct_class_state() == true)
				.collect(Collectors.toList());
	}
	
}
