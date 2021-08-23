package com.coupon.model;

import java.util.List;

public interface I_CouponDAO {
	public void insert(CouponVO couponvo);
	public void delete(Integer coupon_no);
	public List<CouponVO> getAllByMem(Integer mem_no);
}
