package com.activityPromotionDetail.model;

import java.util.List;

public interface I_ActivityPromotionDetailDAO {
	public ActivityPromotionDetailVO insert(ActivityPromotionDetailVO actPromotionDetailVO);
	public void update(ActivityPromotionDetailVO actPromotionDetailVO,Integer actPromotionId,Integer actClassId);
	public List<ActivityPromotionDetailVO> findByActPromotionId(Integer actPromotionId);
	public List<ActivityPromotionDetailVO> findByActClassId(Integer actClassId);
	public List<ActivityPromotionDetailVO> getAll();
}
