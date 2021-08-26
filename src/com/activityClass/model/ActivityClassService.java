package com.activityClass.model;

import java.util.List;

public class ActivityClassService {
	private I_ActivityClassDAO dao;
	
	public ActivityClassService() {
		dao = new ActivityClassJDBCDAO();
	}
	
	
	public ActivityClassVO addActClass(String act_class_name,Boolean act_class_state) {
		ActivityClassVO actClassVO = new ActivityClassVO();
		actClassVO.setAct_class_name(act_class_name);
		actClassVO.setAct_class_state(act_class_state);
		return dao.insert(actClassVO);
	}
	
	
	public ActivityClassVO updateActClass(Integer act_class_no,String act_class_name,Boolean act_class_state) {
		ActivityClassVO actClassVO = new ActivityClassVO();
		actClassVO.setAct_class_no(act_class_no);
		actClassVO.setAct_class_name(act_class_name);
		actClassVO.setAct_class_state(act_class_state);
		dao.update(actClassVO);
		
		return actClassVO;
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
//		ActivityClassVO vo = service.addActClass("水上活動2", true);
//		ActivityClassVO vo = service.updateActClass(6, "水上活動6", false);
//		ActivityClassVO vo = service.getActClassById(1);
		
		
//		System.out.println(vo);
		
		List<ActivityClassVO> list = service.getActClassAll();
		for(ActivityClassVO vo : list) {
			System.out.println(vo);
		}
		
	}
}
