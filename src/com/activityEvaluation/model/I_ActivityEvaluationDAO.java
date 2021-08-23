package com.activityEvaluation.model;

import java.util.List;


public interface I_ActivityEvaluationDAO {
	public ActivityEvaluationVO insert(ActivityEvaluationVO actEvaluationVO);
	public void update(ActivityEvaluationVO actEvaluationVO);
	public ActivityEvaluationVO findById(Integer actEvaluationId);
	public List<ActivityEvaluationVO> findByActId(Integer actId);
	public List<ActivityEvaluationVO> findByMemberId(Integer memberId);
	public List<ActivityEvaluationVO> getAll();
}
