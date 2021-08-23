package com.activityImage.model;

import java.util.List;


public interface I_ActivityImageDAO {
	public ActivityImageVO insert(ActivityImageVO actImageVO);
	public void update(ActivityImageVO actImageVO);
	public ActivityImageVO findById(Integer actImageId);
	public List<ActivityImageVO> findByActId(Integer actId);
	public List<ActivityImageVO> getAll();
}
