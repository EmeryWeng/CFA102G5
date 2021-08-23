package com.activityEvaluationReport.model;

import java.util.List;

import com.activityEvaluation.model.ActivityEvaluationVO;

public interface I_ActivityEvaluationReportDAO {
	public ActivityEvaluationReportVO insert(ActivityEvaluationReportVO actEvaluationReportVO);
	public void update(ActivityEvaluationReportVO actEvaluationReportVO,Integer actEvaluationId,Integer memberId);
	
	public List<ActivityEvaluationReportVO> findByMemberId(Integer memberId);
	public List<ActivityEvaluationReportVO> findByActEvaluationId(Integer actEvaluationId);
	public List<ActivityEvaluationReportVO> getAll();
}
