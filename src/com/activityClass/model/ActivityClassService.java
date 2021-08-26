package com.activityClass.model;

import java.util.List;

public class ActivityClassService {
	private I_ActivityClassDAO dao;
	
	public ActivityClassService() {
		dao = new ActivityClassJDBCDAO();
	}
	
	
	public ActivityClassVO addActClass(ActivityClassVO actClassVO) {
		return dao.insert(actClassVO);
	}
	
	
	public void updateActClass(ActivityClassVO actClassVO) {
		dao.update(actClassVO);
	}
	
	public ActivityClassVO getActClassByPk(Integer act_class_no) {
		return dao.findByPk(act_class_no);
	}
	
	public List<ActivityClassVO> getShowInFrontActClass(){
		return dao.getActClassToFront();
	}
	public List<ActivityClassVO> getActClassAll() {
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		ActivityClassService service = new ActivityClassService();
//		ActivityClassVO v = new ActivityClassVO();
//		v.setAct_class_name("測試活動3");
//		v.setAct_class_no(7);
//		v.setAct_class_state(false);
//		ActivityClassVO vo = service.addActClass(v);
//		service.updateActClass(v);
		ActivityClassVO vo = service.getActClassByPk(1);
		
		
		System.out.println(vo);
		
//		List<ActivityClassVO> list = service.getActClassAll();
//		for(ActivityClassVO vo : list) 
//			System.out.println(vo);
		
		
	}
}
