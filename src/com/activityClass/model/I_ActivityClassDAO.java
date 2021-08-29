package com.activityClass.model;

import java.util.List;

public interface I_ActivityClassDAO {
	public ActivityClassVO insert(ActivityClassVO actClassVO);
	public void update(ActivityClassVO actClassVO);
	public ActivityClassVO findByPk(Integer act_class_no);
	public List<ActivityClassVO> getAll();
}
