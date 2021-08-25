package com.activity.model;
import java.util.List;


public interface I_ActivityDAO {
	public ActivityVO insert(ActivityVO actVO);
	public void update(ActivityVO actVO);
	
	public ActivityVO findByPk(Integer act_no);
	public List<ActivityVO> findByName(String act_name);//活動篩選
	public List<ActivityVO> findByActClassNo(Integer act_class_no);
	public Integer getJoinNumber(Integer act_no);//活動可參與人數
	public List<ActivityVO> getPopularAct();//取得熱門活動(前三)
	public List<ActivityVO> getActToFront();//取得上架活動
	public List<ActivityVO> getAll();
}
