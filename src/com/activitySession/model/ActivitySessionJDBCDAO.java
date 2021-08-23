package com.activitySession.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ActivitySessionJDBCDAO implements I_ActivitySessionDAO{

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_session_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_SESSION";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_SESSION VALUES(?,?,?,?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_SESSION SET act_no = ?,act_start_date = ?,act_end_date = ?,act_session_real_number = ?,act_session_start_date = ?,act_session_start_time = ?,act_session_upper_limit = ?,act_session_lower_limit = ?,act_session_hold_state = ? WHERE act_session_no = ?";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_SESSION WHERE act_session_no = ?";
	private final String SELECT_BY_ACTIVITY_ID_SQL = "SELECT * FROM ACTIVITY_SESSION WHERE act_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public ActivitySessionVO insert(ActivitySessionVO actSessionVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1,null);
			ps.setInt(2, actSessionVO.getActId());
			ps.setObject(3,actSessionVO.getActStartDate());
			ps.setObject(4,actSessionVO.getActEndDate());
			ps.setInt(5,actSessionVO.getActSessionRealNumber() );
			ps.setObject(6,actSessionVO.getActSessionStartDate());
			ps.setObject(7,actSessionVO.getActSessionStartTime());
			ps.setInt(8,actSessionVO.getActSessionUpperLimit());
			ps.setInt(9,actSessionVO.getActSessionLowerLimit());
			ps.setBoolean(10,actSessionVO.getActSessionHoldState());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actSessionVO.setActSessionId(rs.getInt(1));
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
		return actSessionVO;
	}

	@Override
	public void update(ActivitySessionVO actSessionVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, actSessionVO.getActId());
			ps.setObject(2,actSessionVO.getActStartDate());
			ps.setObject(3,actSessionVO.getActEndDate());
			ps.setInt(4,actSessionVO.getActSessionRealNumber() );
			ps.setObject(5,actSessionVO.getActSessionStartDate());
			ps.setObject(6,actSessionVO.getActSessionStartTime());
			ps.setInt(7,actSessionVO.getActSessionUpperLimit());
			ps.setInt(8,actSessionVO.getActSessionLowerLimit());
			ps.setBoolean(9,actSessionVO.getActSessionHoldState());
			ps.setInt(10,actSessionVO.getActSessionId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public List<ActivitySessionVO> findByActId(Integer actId) {
		List<ActivitySessionVO> list = new ArrayList<>();
		ActivitySessionVO actSessionVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ID_SQL)) {
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actSessionVO = new ActivitySessionVO();
				actSessionVO.setActSessionId(rs.getInt(1));
				actSessionVO.setActId(rs.getInt(2));
				actSessionVO.setActStartDate(rs.getDate(3).toLocalDate());
				actSessionVO.setActEndDate(rs.getDate(4).toLocalDate());
				actSessionVO.setActSessionRealNumber(rs.getInt(5));
				actSessionVO.setActSessionStartDate(rs.getDate(6).toLocalDate());
				actSessionVO.setActSessionStartTime(rs.getTime(7).toLocalTime());
				actSessionVO.setActSessionUpperLimit(rs.getInt(8));
				actSessionVO.setActSessionLowerLimit(rs.getInt(9));
				actSessionVO.setActSessionHoldState(rs.getBoolean(10));			
				list.add(actSessionVO);
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
	public ActivitySessionVO findById(Integer actSessionId) {
		ActivitySessionVO actSessionVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			ps.setInt(1, actSessionId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actSessionVO = new ActivitySessionVO();
				actSessionVO.setActSessionId(rs.getInt(1));
				actSessionVO.setActId(rs.getInt(2));
				actSessionVO.setActStartDate(rs.getDate(3).toLocalDate());
				actSessionVO.setActEndDate(rs.getDate(4).toLocalDate());
				actSessionVO.setActSessionRealNumber(rs.getInt(5));
				actSessionVO.setActSessionStartDate(rs.getDate(6).toLocalDate());
				actSessionVO.setActSessionStartTime(rs.getTime(7).toLocalTime());
				actSessionVO.setActSessionUpperLimit(rs.getInt(8));
				actSessionVO.setActSessionLowerLimit(rs.getInt(9));
				actSessionVO.setActSessionHoldState(rs.getBoolean(10));			
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
		return actSessionVO;
	}

	@Override
	public List<ActivitySessionVO> getAll() {
		List<ActivitySessionVO> list = new ArrayList<>();
		ActivitySessionVO actSessionVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();
			while (rs.next()) {
				actSessionVO = new ActivitySessionVO();
				actSessionVO.setActSessionId(rs.getInt(1));
				actSessionVO.setActId(rs.getInt(2));
				actSessionVO.setActStartDate(rs.getDate(3).toLocalDate());
				actSessionVO.setActEndDate(rs.getDate(4).toLocalDate());
				actSessionVO.setActSessionRealNumber(rs.getInt(5));
				actSessionVO.setActSessionStartDate(rs.getDate(6).toLocalDate());
				actSessionVO.setActSessionStartTime(rs.getTime(7).toLocalTime());
				actSessionVO.setActSessionUpperLimit(rs.getInt(8));
				actSessionVO.setActSessionLowerLimit(rs.getInt(9));
				actSessionVO.setActSessionHoldState(rs.getBoolean(10));			
				list.add(actSessionVO);
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
		ActivitySessionJDBCDAO dao = new ActivitySessionJDBCDAO();
//		ActivitySessionVO vo = new ActivitySessionVO();
//		vo.setActSessionId(1);
//		vo.setActId(3);
//		vo.setActStartDate(LocalDate.of(2021,9,1));
//		vo.setActEndDate(LocalDate.of(2021,12,1));
//		vo.setActSessionRealNumber(20);
//		vo.setActSessionStartDate(LocalDate.of(2021,8,23));
//		vo.setActSessionStartTime(LocalTime.of(22,0));
//		vo.setActSessionUpperLimit(10);
//		vo.setActSessionLowerLimit(3);
//		vo.setActSessionHoldState(true);
//		dao.insert(vo);
//		dao.update(vo);
//		ActivitySessionVO vo =dao.findById(1);
//		List<ActivitySessionVO> list = dao.findByActId(3);
		List<ActivitySessionVO> list = dao.getAll();
		for(ActivitySessionVO vo : list)
		System.out.println(vo);
	}
}
