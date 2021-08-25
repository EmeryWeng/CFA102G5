package com.coupon.model;

import java.util.List;

public class CouponTest {

	public static void main(String[] args) {
		I_CouponDAO dao = new CouponJDBCDAO();
		
		// 新增
//		CouponVO coupon = new CouponVO();
//		coupon.setMem_no(2);
//		coupon.setCoupon_value(825);
//		dao.insert(coupon);
		
		// 刪除
//		dao.delete(12);
		
		// 查詢同個會員所有未過期折價券
//		List<CouponVO> list = dao.getAllByMem(2);
//		for (CouponVO coupon : list) {
//			System.out.print(coupon.getCoupon_no() + ",");
//			System.out.print(coupon.getMem_no() + ",");
//			System.out.print(coupon.getCoupon_value() + ",");
//			System.out.println(coupon.getCoupon_expiry());
//		}
	}
}
