package com.activityImage.model;

import java.util.List;


public interface I_ActivityImageDAO {
	public ActivityImageVO insert(ActivityImageVO actImageVO);
	public void update(ActivityImageVO act_image_no);
	public void delete(Integer act_image_no);
	public ActivityImageVO findByPk(Integer act_image_no);
	public List<ActivityImageVO> findByActNo(Integer act_no);
	public List<ActivityImageVO> getAll();
}
