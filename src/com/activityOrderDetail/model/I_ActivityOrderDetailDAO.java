package com.activityOrderDetail.model;

import java.util.List;

//狀態
public interface I_ActivityOrderDetailDAO {
	public ActivityOrderDetailVO insert(ActivityOrderDetailVO actOrderDetailVO);
	public void update(ActivityOrderDetailVO actOrderDetailVO,Integer act_order_no,Integer act_session_no);
	
	public List<ActivityOrderDetailVO> findByActOrderNo(Integer act_order_no);
	public List<ActivityOrderDetailVO> findByActSessionNo(Integer act_session_no);
	public List<ActivityOrderDetailVO> getActOrderDetailToFront();
	public List<ActivityOrderDetailVO> getAll();
}
