package com.activityClass.model;

import java.util.List;

public class ActivityClassService {
	private I_ActivityClassDAO dao;
	public ActivityClassService() {
		dao = new ActivityClassJDBCDAO();
	}
	
	
	public ActivityClassVO addActClass(String actClassName,Boolean actClassState) {
		ActivityClassVO actClassVO = new ActivityClassVO();
		actClassVO.setAct_class_name(actClassName);
		actClassVO.setAct_class_state(actClassState);
		return dao.insert(actClassVO);
	}
	
	
	public ActivityClassVO updateActClass(Integer actClassId,String actClassName,Boolean actClassState) {
		ActivityClassVO actClassVO = new ActivityClassVO();
		actClassVO.setAct_class_no(actClassId);
		actClassVO.setAct_class_name(actClassName);
		actClassVO.setAct_class_state(actClassState);
		dao.update(actClassVO);
		
		return actClassVO;
	}
	
	public ActivityClassVO getActClassByPk(Integer actClassId) {
		return dao.findByPk(actClassId);
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
