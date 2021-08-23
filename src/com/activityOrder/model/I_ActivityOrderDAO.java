package com.activityOrder.model;

import java.util.List;


public interface I_ActivityOrderDAO {
	public ActivityOrderVO insert(ActivityOrderVO actOrderVO);
	public void update(ActivityOrderVO actOrderVO);
	
	public ActivityOrderVO findById(Integer actOrderId);
	public List<ActivityOrderVO> findByMemberId(Integer memberId);
	public List<ActivityOrderVO> getAll();
}
