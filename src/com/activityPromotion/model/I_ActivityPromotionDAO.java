package com.activityPromotion.model;

import java.util.List;

public interface I_ActivityPromotionDAO {
	public ActivityPromotionVO insert(ActivityPromotionVO actPromotionVO);
	public void update(ActivityPromotionVO actPromotionVO);
	
	public ActivityPromotionVO findById(Integer actPromotionId);
	public List<ActivityPromotionVO> getAll();
}
