package com.activitySession.model;

import java.util.List;

public interface I_ActivitySessionDAO {
	public ActivitySessionVO insert(ActivitySessionVO actSessionVO);
	public void update(ActivitySessionVO actSessionVO);
	public ActivitySessionVO findById(Integer actSessionId);
	public List<ActivitySessionVO> findByActId(Integer actId);
	public List<ActivitySessionVO> getAll();
}
