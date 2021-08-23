package com.activityEvaluationReport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ActivityEvaluationReportJDBCDAO implements I_ActivityEvaluationReportDAO {

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_evaluation_no","mem_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_EVALUATION_REPORT";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_EVALUATION_REPORT VALUES(?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_EVALUATION_REPORT SET act_evaluation_no = ?,mem_no = ?,act_report_date = ?,act_evaluation_report_reason = ?"
			+ ",act_evaluation_report_state = ? WHERE act_evaluation_no = ? and mem_no = ?"; //字串串接 where前要空一行
	private final String SELECT_BY_ACTIVITY_EVALUATION_ID_SQL = "SELECT * FROM ACTIVITY_EVALUATION_REPORT WHERE act_evaluation_no = ?";
	private final String SELECT_BY_MEMBER_ID_SQL = "SELECT * FROM ACTIVITY_EVALUATION_REPORT WHERE mem_no = ?";

	
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public ActivityEvaluationReportVO insert(ActivityEvaluationReportVO actEvaluationReportVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setInt(1,actEvaluationReportVO.getActEvaluationId());
			ps.setInt(2,actEvaluationReportVO.getMemId());
			ps.setObject(3,actEvaluationReportVO.getActReportDate());
			ps.setInt(4,actEvaluationReportVO.getActEvaluationReportReason());
			ps.setInt(5,actEvaluationReportVO.getActEvaluationReportState());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actEvaluationReportVO.setActEvaluationId(rs.getInt(1));
				actEvaluationReportVO.setMemId(rs.getInt(2));
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
		return actEvaluationReportVO;
	}

	@Override
	public void update(ActivityEvaluationReportVO actEvaluationReportVO,Integer actEvaluationId,Integer memberId) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1,actEvaluationReportVO.getActEvaluationId());
			ps.setInt(2,actEvaluationReportVO.getMemId());
			ps.setObject(3,actEvaluationReportVO.getActReportDate());
			ps.setInt(4,actEvaluationReportVO.getActEvaluationReportReason());
			ps.setInt(5,actEvaluationReportVO.getActEvaluationReportState());
			ps.setInt(6,actEvaluationId);
			ps.setInt(7,memberId);
			ps.executeUpdate();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<ActivityEvaluationReportVO> findByActEvaluationId(Integer actEvaluationId) {
		List<ActivityEvaluationReportVO> list = new ArrayList<>();
		ActivityEvaluationReportVO actEvaluationReportVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_EVALUATION_ID_SQL)) {
			ps.setInt(1, actEvaluationId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actEvaluationReportVO = new ActivityEvaluationReportVO();
				actEvaluationReportVO.setActEvaluationId(rs.getInt(1));
				actEvaluationReportVO.setMemId(rs.getInt(2));
				actEvaluationReportVO.setActReportDate(rs.getTimestamp(3).toLocalDateTime());
				actEvaluationReportVO.setActEvaluationReportReason(rs.getInt(4));
				actEvaluationReportVO.setActEvaluationReportState(rs.getInt(5));
				list.add(actEvaluationReportVO);
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
	public List<ActivityEvaluationReportVO> findByMemberId(Integer memberId) {
		List<ActivityEvaluationReportVO> list = new ArrayList<>();
		ActivityEvaluationReportVO actEvaluationReportVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_MEMBER_ID_SQL)) {
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actEvaluationReportVO = new ActivityEvaluationReportVO();
				actEvaluationReportVO.setActEvaluationId(rs.getInt(1));
				actEvaluationReportVO.setMemId(rs.getInt(2));
				actEvaluationReportVO.setActReportDate(rs.getTimestamp(3).toLocalDateTime());
				actEvaluationReportVO.setActEvaluationReportReason(rs.getInt(4));
				actEvaluationReportVO.setActEvaluationReportState(rs.getInt(5));
				list.add(actEvaluationReportVO);
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
	public List<ActivityEvaluationReportVO> getAll() {
		List<ActivityEvaluationReportVO> list = new ArrayList<>();
		ActivityEvaluationReportVO actEvaluationReportVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();		
			while (rs.next()) {
				actEvaluationReportVO = new ActivityEvaluationReportVO();
				actEvaluationReportVO.setActEvaluationId(rs.getInt(1));
				actEvaluationReportVO.setMemId(rs.getInt(2));
				actEvaluationReportVO.setActReportDate(rs.getTimestamp(3).toLocalDateTime());
				actEvaluationReportVO.setActEvaluationReportReason(rs.getInt(4));
				actEvaluationReportVO.setActEvaluationReportState(rs.getInt(5));
				list.add(actEvaluationReportVO);
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
		ActivityEvaluationReportJDBCDAO dao = new ActivityEvaluationReportJDBCDAO();
//		ActivityEvaluationReportVO vo = new ActivityEvaluationReportVO();
//		
//		vo.setActEvaluationId(4);
//		vo.setMemId(1);
//		vo.setActReportDate(LocalDateTime.now());
//		vo.setActEvaluationReportReason(2);
//		vo.setActEvaluationReportState(2);
		
//		dao.insert(vo);
//		dao.update(vo,3,1);
//		List<ActivityEvaluationReportVO> list = dao.findByActEvaluationId(4);
//		List<ActivityEvaluationReportVO> list = dao.findByMemberId(1);
		List<ActivityEvaluationReportVO> list = dao.getAll();
		for(ActivityEvaluationReportVO vo : list)
		System.out.println(vo);
	}
}
