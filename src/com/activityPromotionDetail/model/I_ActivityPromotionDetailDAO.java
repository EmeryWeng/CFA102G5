package com.activityPromotionDetail.model;

import java.util.List;

public interface I_ActivityPromotionDetailDAO {
	public ActivityPromotionDetailVO insert(ActivityPromotionDetailVO actPromotionDetailVO);
	public void update(ActivityPromotionDetailVO actPromotionDetailVO,Integer act_promotion_no,Integer act_class_no);
	public List<ActivityPromotionDetailVO> findByActPromotionNo(Integer act_promotion_no);
	public List<ActivityPromotionDetailVO> findByActClassNo(Integer act_class_no);
	public List<ActivityPromotionDetailVO> getAll();
}
