package com.activityEvaluation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityEvaluationJDBCDAO implements I_ActivityEvaluationDAO {

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_evaluation_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_EVALUATION";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_EVALUATION VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_EVALUATION SET act_no = ?,mem_no = ?,act_evaluation_star_number = ?,act_evaluation_message = ?"
			+ ",act_evaluation_date = ?,act_evaluation_state = ? WHERE act_evaluation_no = ?"; //字串串接 where前要空一行
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_EVALUATION WHERE act_evaluation_no = ?";
	private final String SELECT_BY_ACTIVITY_ID_SQL = "SELECT * FROM ACTIVITY_EVALUATION WHERE act_no = ?";
	private final String SELECT_BY_MEMBER_ID_SQL = "SELECT * FROM ACTIVITY_EVALUATION WHERE mem_no = ?";

	
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public ActivityEvaluationVO insert(ActivityEvaluationVO actEvaluationVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1, null); // AI
			ps.setInt(2,actEvaluationVO.getActId());
			ps.setInt(3,actEvaluationVO.getMemId());
			ps.setInt(4,actEvaluationVO.getActEvaluationStarNumber());
			ps.setString(5,actEvaluationVO.getActEvaluationMessage());
			ps.setObject(6,actEvaluationVO.getActEvaluationDate());
			ps.setBoolean(7,actEvaluationVO.getActEvaluationState());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actEvaluationVO.setActEvaluationId(rs.getInt(1));
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
		return actEvaluationVO;
	}

	@Override
	public void update(ActivityEvaluationVO actEvaluationVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			
			ps.setInt(1,actEvaluationVO.getActId());
			ps.setInt(2,actEvaluationVO.getMemId());
			ps.setInt(3,actEvaluationVO.getActEvaluationStarNumber());
			ps.setString(4,actEvaluationVO.getActEvaluationMessage());
			ps.setObject(5,actEvaluationVO.getActEvaluationDate());
			ps.setBoolean(6,actEvaluationVO.getActEvaluationState());
			ps.setInt(7,actEvaluationVO.getActEvaluationId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ActivityEvaluationVO findById(Integer actEvaluationId) {
		ActivityEvaluationVO actEvaluationVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			ps.setInt(1, actEvaluationId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actEvaluationVO = new ActivityEvaluationVO();
				actEvaluationVO.setActEvaluationId(rs.getInt(1));
				actEvaluationVO.setActId(rs.getInt(2));
				actEvaluationVO.setMemId(rs.getInt(3));
				actEvaluationVO.setActEvaluationStarNumber(rs.getInt(4));
				actEvaluationVO.setActEvaluationMessage(rs.getString(5));
				actEvaluationVO.setActEvaluationDate(rs.getTimestamp(6).toLocalDateTime());
				actEvaluationVO.setActEvaluationState(rs.getBoolean(7));
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
		return actEvaluationVO;
	}

	@Override
	public List<ActivityEvaluationVO> findByActId(Integer actId) {
		List<ActivityEvaluationVO> list = new ArrayList<>();
		ActivityEvaluationVO actEvaluationVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ID_SQL)) {
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actEvaluationVO = new ActivityEvaluationVO();
				actEvaluationVO.setActEvaluationId(rs.getInt(1));
				actEvaluationVO.setActId(rs.getInt(2));
				actEvaluationVO.setMemId(rs.getInt(3));
				actEvaluationVO.setActEvaluationStarNumber(rs.getInt(4));
				actEvaluationVO.setActEvaluationMessage(rs.getString(5));
				actEvaluationVO.setActEvaluationDate(rs.getTimestamp(6).toLocalDateTime());
				actEvaluationVO.setActEvaluationState(rs.getBoolean(7));
				list.add(actEvaluationVO);
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
	public List<ActivityEvaluationVO> findByMemberId(Integer memberId) {
		List<ActivityEvaluationVO> list = new ArrayList<>();
		ActivityEvaluationVO actEvaluationVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_MEMBER_ID_SQL)) {
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actEvaluationVO = new ActivityEvaluationVO();
				actEvaluationVO.setActEvaluationId(rs.getInt(1));
				actEvaluationVO.setActId(rs.getInt(2));
				actEvaluationVO.setMemId(rs.getInt(3));
				actEvaluationVO.setActEvaluationStarNumber(rs.getInt(4));
				actEvaluationVO.setActEvaluationMessage(rs.getString(5));
				actEvaluationVO.setActEvaluationDate(rs.getTimestamp(6).toLocalDateTime());
				actEvaluationVO.setActEvaluationState(rs.getBoolean(7));
				list.add(actEvaluationVO);
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
	public List<ActivityEvaluationVO> getAll() {
		List<ActivityEvaluationVO> list = new ArrayList<>();
		ActivityEvaluationVO actEvaluationVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();			
			while (rs.next()) {
				actEvaluationVO = new ActivityEvaluationVO();
				actEvaluationVO.setActEvaluationId(rs.getInt(1));
				actEvaluationVO.setActId(rs.getInt(2));
				actEvaluationVO.setMemId(rs.getInt(3));
				actEvaluationVO.setActEvaluationStarNumber(rs.getInt(4));
				actEvaluationVO.setActEvaluationMessage(rs.getString(5));
				actEvaluationVO.setActEvaluationDate(rs.getTimestamp(6).toLocalDateTime());
				actEvaluationVO.setActEvaluationState(rs.getBoolean(7));
				list.add(actEvaluationVO);
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
		ActivityEvaluationJDBCDAO dao = new ActivityEvaluationJDBCDAO();
//		List<ActivityOrderVO> list = dao.findByMemberId(1);
//		ActivityEvaluationVO vo = new ActivityEvaluationVO();
//		ActivityOrderVO vo = dao.findById(1);
//		List<ActivityOrderVO> list = dao.getAll();
//		vo.setActId(2);
//		vo.setMemId(1);
//		vo.setActEvaluationStarNumber(4);
//		vo.setActEvaluationMessage("超級好玩的體驗33!");
//		vo.setActEvaluationDate(LocalDateTime.now());
//		vo.setActEvaluationState(true);
//		dao.insert(vo);
//		vo.setActEvaluationId(2);
		
//		dao.update(vo);
		
//		ActivityEvaluationVO vo = dao.findById(2);
//		List<ActivityEvaluationVO> list = dao.findByMemberId(1);
//		List<ActivityEvaluationVO> list = dao.findByActId(2);
		List<ActivityEvaluationVO> list = dao.getAll();
		for(ActivityEvaluationVO vo : list)
		System.out.println(vo);
		
	}
}
