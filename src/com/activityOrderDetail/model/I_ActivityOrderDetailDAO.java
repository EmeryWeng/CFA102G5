package com.activityOrderDetail.model;

import java.util.List;

public interface I_ActivityOrderDetailDAO {
	public ActivityOrderDetailVO insert(ActivityOrderDetailVO actOrderDetailVO);
	public void update(ActivityOrderDetailVO actOrderDetailVO,Integer actOrderId,Integer actSessionId);
	
	public List<ActivityOrderDetailVO> findByActOrderId(Integer actOrderId);
	public List<ActivityOrderDetailVO> findByActSessionId(Integer actSessionId);
	public List<ActivityOrderDetailVO> getAll();
}
