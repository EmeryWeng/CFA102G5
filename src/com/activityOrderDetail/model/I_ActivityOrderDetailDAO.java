package com.activityOrderDetail.model;

import java.util.List;

//狀態
public interface I_ActivityOrderDetailDAO {
	public ActivityOrderDetailVO insert(ActivityOrderDetailVO actOrderDetailVO);
	public void update(ActivityOrderDetailVO actOrderDetailVO);
	//更新 只能針對該活動訂單明細編號 做額外資訊的更改 然後狀態要改成3(已改期)
	public ActivityOrderDetailVO findByPk(Integer act_order_detail_no);
	public List<ActivityOrderDetailVO> findByActOrderNo(Integer act_order_no);
	public List<ActivityOrderDetailVO> findByActSessionNo(Integer act_session_no);
	public List<ActivityOrderDetailVO> getActOrderDetailState(Integer act_order_detail_state);
	public List<ActivityOrderDetailVO> getAll();
}
