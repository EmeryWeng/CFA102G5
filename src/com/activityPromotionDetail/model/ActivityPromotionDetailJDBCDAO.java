package com.activityPromotionDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityPromotionDetailJDBCDAO implements I_ActivityPromotionDetailDAO{

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_promotion_no","act_class_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_PROMOTION_DETAIL";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_PROMOTION_DETAIL VALUES(?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_PROMOTION_DETAIL SET act_promotion_no = ?,act_class_no = ?,act_discount_price = ? WHERE act_promotion_no = ? and act_class_no = ?";
	private final String SELECT_BY_ACTIVITY_PROMOTION_ID_SQL = "SELECT * FROM ACTIVITY_PROMOTION_DETAIL WHERE act_promotion_no = ?";
	private final String SELECT_BY_ACTIVITY_CLASS_ID_SQL = "SELECT * FROM ACTIVITY_PROMOTION_DETAIL WHERE act_class_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public ActivityPromotionDetailVO insert(ActivityPromotionDetailVO actPromotionDetailVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setInt(1, actPromotionDetailVO.getActPromotionId()); 
			ps.setInt(2, actPromotionDetailVO.getActClassNo());
			ps.setDouble(3, actPromotionDetailVO.getActDiscountPrice());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actPromotionDetailVO.setActPromotionId(rs.getInt(1));
				actPromotionDetailVO.setActClassNo(rs.getInt(2));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return actPromotionDetailVO;
	}

	@Override
	public void update(ActivityPromotionDetailVO actPromotionDetailVO,Integer actPromotionId,Integer actClassId) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1,actPromotionDetailVO.getActPromotionId()); 
			ps.setInt(2,actPromotionDetailVO.getActClassNo());
			ps.setDouble(3, actPromotionDetailVO.getActDiscountPrice());
			ps.setInt(4,actPromotionId); 
			ps.setInt(5,actClassId);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public List<ActivityPromotionDetailVO> findByActPromotionId(Integer actPromotionId) {
		List<ActivityPromotionDetailVO> list = new ArrayList<>();
		ActivityPromotionDetailVO actPromotionDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_PROMOTION_ID_SQL)) {
			ps.setInt(1, actPromotionId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actPromotionDetailVO = new ActivityPromotionDetailVO();
				actPromotionDetailVO.setActPromotionId(rs.getInt(1));
				actPromotionDetailVO.setActClassNo(rs.getInt(2));
				actPromotionDetailVO.setActDiscountPrice(rs.getDouble(3));
				list.add(actPromotionDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<ActivityPromotionDetailVO> findByActClassId(Integer actClassId) {
		List<ActivityPromotionDetailVO> list = new ArrayList<>();
		ActivityPromotionDetailVO actPromotionDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_CLASS_ID_SQL)) {
			ps.setInt(1, actClassId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actPromotionDetailVO = new ActivityPromotionDetailVO();
				actPromotionDetailVO.setActPromotionId(rs.getInt(1));
				actPromotionDetailVO.setActClassNo(rs.getInt(2));
				actPromotionDetailVO.setActDiscountPrice(rs.getDouble(3));
				list.add(actPromotionDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<ActivityPromotionDetailVO> getAll() {
		List<ActivityPromotionDetailVO> list = new ArrayList<>();
		ActivityPromotionDetailVO actPromotionDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();
			while (rs.next()) {
				actPromotionDetailVO = new ActivityPromotionDetailVO();
				actPromotionDetailVO.setActPromotionId(rs.getInt(1));
				actPromotionDetailVO.setActClassNo(rs.getInt(2));
				actPromotionDetailVO.setActDiscountPrice(rs.getDouble(3));
				list.add(actPromotionDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {
		ActivityPromotionDetailJDBCDAO dao = new ActivityPromotionDetailJDBCDAO();
//		ActivityPromotionDetailVO vo = new ActivityPromotionDetailVO();
//		vo.setActPromotionId(4);
//		vo.setActClassNo(4);
//		vo.setActDiscountPrice(0.52);
//		dao.insert(vo);
//		dao.update(vo,2,2);

//		List<ActivityPromotionDetailVO> list = dao.findByActPromotionId(4);
//		List<ActivityPromotionDetailVO> list = dao.findByActClassId(2);
		List<ActivityPromotionDetailVO> list = dao.getAll();
		for(ActivityPromotionDetailVO vo : list)
		System.out.println(vo);
	}
}
