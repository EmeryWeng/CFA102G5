package com.activityPromotion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityPromotionJDBCDAO implements I_ActivityPromotionDAO{

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_promotion_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_PROMOTION";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_PROMOTION VALUES(?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_PROMOTION SET act_promotion_name = ?,act_promotion_start_date = ?,act_promotion_end_date = ? WHERE act_promotion_no = ?";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_PROMOTION WHERE act_promotion_no = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public ActivityPromotionVO insert(ActivityPromotionVO actPromotionVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1, null); // AI
			ps.setString(2, actPromotionVO.getActPromotionName());
			ps.setObject(3, actPromotionVO.getActPromotionStartDate());
			ps.setObject(4, actPromotionVO.getActPromotionEndDate());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actPromotionVO.setActPromotionId(rs.getInt(1));
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
		return actPromotionVO;
	}

	@Override
	public void update(ActivityPromotionVO actPromotionVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, actPromotionVO.getActPromotionName());
			ps.setObject(2, actPromotionVO.getActPromotionStartDate());
			ps.setObject(3,actPromotionVO.getActPromotionEndDate());
			ps.setInt(4, actPromotionVO.getActPromotionId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public ActivityPromotionVO findById(Integer actPromotionId) {
		ActivityPromotionVO actPromotionVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			ps.setInt(1, actPromotionId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actPromotionVO = new ActivityPromotionVO();
				actPromotionVO.setActPromotionId(rs.getInt(1));
				actPromotionVO.setActPromotionName(rs.getString(2));
				actPromotionVO.setActPromotionStartDate(rs.getDate(3).toLocalDate());
				actPromotionVO.setActPromotionEndDate(rs.getDate(4).toLocalDate());
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
		return actPromotionVO;
	}

	@Override
	public List<ActivityPromotionVO> getAll() {
		List<ActivityPromotionVO> list = new ArrayList<>();
		ActivityPromotionVO actPromotionVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();
			while (rs.next()) {
				actPromotionVO = new ActivityPromotionVO();
				actPromotionVO.setActPromotionId(rs.getInt(1));
				actPromotionVO.setActPromotionName(rs.getString(2));
				actPromotionVO.setActPromotionStartDate(rs.getDate(3).toLocalDate());
				actPromotionVO.setActPromotionEndDate(rs.getDate(4).toLocalDate());
				list.add(actPromotionVO);
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
		ActivityPromotionJDBCDAO dao = new ActivityPromotionJDBCDAO();
		ActivityPromotionVO vo = new ActivityPromotionVO();
		
		
		vo.setActPromotionName("夏天消暑活動促銷100");
		vo.setActPromotionStartDate(LocalDate.of(2021,8,23));
		vo.setActPromotionEndDate(LocalDate.of(2021,10,1));
//		dao.insert(vo);
		vo.setActPromotionId(4);
		
		dao.update(vo);
		
//		ActivityPromotionVO vo = dao.findById(2);
//		List<ActivityPromotionVO> list = dao.getAll();
//		for(ActivityPromotionVO vo : list)
		System.out.println(vo);
		
	}
}
