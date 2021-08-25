package com.activitySession.model;

import java.util.List;

public interface I_ActivitySessionDAO {
	public ActivitySessionVO insert(ActivitySessionVO actSessionVO);
	public void update(ActivitySessionVO actSessionVO);
	public ActivitySessionVO findByPk(Integer act_session_no);
	public List<ActivitySessionVO> findByActNo(Integer act_no);
	public List<ActivitySessionVO> getActSessionToFront();
	public List<ActivitySessionVO> getAll();
}
