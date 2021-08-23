package com.activity.model;
import java.util.List;


public interface I_ActivityDAO {
	public ActivityVO insert(ActivityVO actVO);
	public void update(ActivityVO actVO);
	
	public ActivityVO findById(Integer actId);
	public List<ActivityVO> findByName(String actName);//活動篩選
	public List<ActivityVO> findByActClassId(Integer actClassId);
	public Integer getJoinNumber(Integer actId);//活動可參與人數
	public List<ActivityVO> getAll();
}
