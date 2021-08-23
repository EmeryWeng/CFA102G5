package com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityJDBCDAO implements I_ActivityDAO{

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String GET_JOIN_NUMBER_SQL = "SELECT act_join_number FROM ACTIVITY WHERE act_no = ?";
	private final String UPDATE_SQL = "UPDATE ACTIVITY SET act_class_no = ?,act_name = ?,act_location = ?,act_schedule_time = ?,act_instruction = ?"
			+ ",act_gather_location = ?,act_location_longitude = ?,act_location_latitude = ?,act_sell_number = ?,act_join_number = ?,act_evaluation_number = ?"
			+ ",act_average_star_number = ?,act_state = ? WHERE act_no = ?"; //字串串接 where前要空一行
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY WHERE act_no = ?";
	private final String SELECT_BY_NAME_SQL = "SELECT * FROM ACTIVITY WHERE act_name LIKE ?";
	private final String SELECT_BY_ACTIVITY_CLASS_SQL = "SELECT * FROM ACTIVITY WHERE act_class_no = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ActivityVO insert(ActivityVO actVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1, null); // AI
			ps.setInt(2, actVO.getActClassId());
			ps.setString(3,actVO.getActName());
			ps.setString(4,actVO.getActLocation());
			ps.setInt(5,actVO.getActScheduleTime());
			ps.setString(6,actVO.getActInstruction());
			ps.setString(7,actVO.getActGatherLocation());
			ps.setDouble(8,actVO.getActLocationLongitude());
			ps.setDouble(9,actVO.getActLocationLatitude());
			ps.setInt(10,actVO.getActSellNumber());
			ps.setInt(11,actVO.getActJoinNumber());
			ps.setInt(12,actVO.getActEvaluationNumber());
			ps.setDouble(13,actVO.getActAverageStarNumber());
			ps.setBoolean(14,actVO.getActState());
			
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				actVO.setActId(rs.getInt(1));
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
		return actVO;
	}

	@Override
	public void update(ActivityVO actVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, actVO.getActClassId());
			ps.setString(2,actVO.getActName());
			ps.setString(3,actVO.getActLocation());
			ps.setInt(4,actVO.getActScheduleTime());
			ps.setString(5,actVO.getActInstruction());
			ps.setString(6,actVO.getActGatherLocation());
			ps.setDouble(7,actVO.getActLocationLongitude());
			ps.setDouble(8,actVO.getActLocationLatitude());
			ps.setInt(9,actVO.getActSellNumber());
			ps.setInt(10,actVO.getActJoinNumber());
			ps.setInt(11,actVO.getActEvaluationNumber());
			ps.setDouble(12,actVO.getActAverageStarNumber());
			ps.setBoolean(13,actVO.getActState());
			ps.setInt(14,actVO.getActId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<ActivityVO> findByName(String actName) {
		List<ActivityVO> list = new ArrayList<>();
		ActivityVO actVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME_SQL)) {
			
			ps.setString(1, "%" + actName + "%"); //塞選名稱有包含泛舟之類的活動
			rs = ps.executeQuery();
			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActId(rs.getInt(1));
				actVO.setActClassId(rs.getInt(2)); 
				actVO.setActName(rs.getString(3));
				actVO.setActLocation(rs.getString(4));
				actVO.setActScheduleTime(rs.getInt(5));
				actVO.setActInstruction(rs.getString(6));
				actVO.setActGatherLocation(rs.getString(7));
				actVO.setActLocationLongitude(rs.getDouble(8));
				actVO.setActLocationLatitude(rs.getDouble(9));
				actVO.setActSellNumber(rs.getInt(10));
				actVO.setActJoinNumber(rs.getInt(11));
				actVO.setActEvaluationNumber(rs.getInt(12));
				actVO.setActAverageStarNumber(rs.getDouble(13));
				actVO.setActState(rs.getBoolean(14));
				list.add(actVO);
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
	public ActivityVO findById(Integer actId) {
		ActivityVO actVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActId(rs.getInt(1));
				actVO.setActClassId(rs.getInt(2)); // AI
				actVO.setActName(rs.getString(3));
				actVO.setActLocation(rs.getString(4));
				actVO.setActScheduleTime(rs.getInt(5));
				actVO.setActInstruction(rs.getString(6));
				actVO.setActGatherLocation(rs.getString(7));
				actVO.setActLocationLongitude(rs.getDouble(8));
				actVO.setActLocationLatitude(rs.getDouble(9));
				actVO.setActSellNumber(rs.getInt(10));
				actVO.setActJoinNumber(rs.getInt(11));
				actVO.setActEvaluationNumber(rs.getInt(12));
				actVO.setActAverageStarNumber(rs.getDouble(13));
				actVO.setActState(rs.getBoolean(14));
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
		return actVO;
	}

	@Override
	public List<ActivityVO> findByActClassId(Integer actClassId) {
		List<ActivityVO> list = new ArrayList<>();
		ActivityVO actVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_CLASS_SQL)) {
			
			ps.setInt(1,actClassId);
			rs = ps.executeQuery();
			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActId(rs.getInt(1));
				actVO.setActClassId(rs.getInt(2)); 
				actVO.setActName(rs.getString(3));
				actVO.setActLocation(rs.getString(4));
				actVO.setActScheduleTime(rs.getInt(5));
				actVO.setActInstruction(rs.getString(6));
				actVO.setActGatherLocation(rs.getString(7));
				actVO.setActLocationLongitude(rs.getDouble(8));
				actVO.setActLocationLatitude(rs.getDouble(9));
				actVO.setActSellNumber(rs.getInt(10));
				actVO.setActJoinNumber(rs.getInt(11));
				actVO.setActEvaluationNumber(rs.getInt(12));
				actVO.setActAverageStarNumber(rs.getDouble(13));
				actVO.setActState(rs.getBoolean(14));
				list.add(actVO);
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

	@Override			//第x個活動可參與的人數
	public Integer getJoinNumber(Integer actId) {
		Integer number = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_JOIN_NUMBER_SQL)) {
			
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1);
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
		return number;
	}

	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list = new ArrayList<>();
		ActivityVO actVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();
			while (rs.next()) {
				actVO = new ActivityVO();
				actVO.setActId(rs.getInt(1));// AI
				actVO.setActClassId(rs.getInt(2)); 
				actVO.setActName(rs.getString(3));
				actVO.setActLocation(rs.getString(4));
				actVO.setActScheduleTime(rs.getInt(5));
				actVO.setActInstruction(rs.getString(6));
				actVO.setActGatherLocation(rs.getString(7));
				actVO.setActLocationLongitude(rs.getDouble(8));
				actVO.setActLocationLatitude(rs.getDouble(9));
				actVO.setActSellNumber(rs.getInt(10));
				actVO.setActJoinNumber(rs.getInt(11));
				actVO.setActEvaluationNumber(rs.getInt(12));
				actVO.setActAverageStarNumber(rs.getDouble(13));
				actVO.setActState(rs.getBoolean(14));
				list.add(actVO);
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
		ActivityJDBCDAO dao = new ActivityJDBCDAO();
//		ActivityVO vo = new ActivityVO();
//		ActivityVO vo = dao.findById(2);
//		List<ActivityVO> vo = dao.findByName("泛");
//		for(ActivityVO v : vo) {
//			System.out.println(v);
//		}
//		Integer number = dao.getJoinNumber(2);
//		System.out.println(number);
//		List<ActivityVO> list = dao.findByActClassId(1);
		List<ActivityVO> list = dao.getAll();
//		List<ActivityVO> list = dao.findByName("人");
		for(ActivityVO vo : list) 
			System.out.println(vo);
//		System.out.println(number);
//		vo.setActId(3);
//		vo.setActClassId(2);
//		vo.setActName("泛舟達人300");
//		vo.setActLocation("花蓮市");
//		vo.setActScheduleTime(2);
//		vo.setActInstruction("很好玩");
//		vo.setActGatherLocation("花蓮區");
//		vo.setActLocationLongitude(123.456);
//		vo.setActLocationLatitude(456.78);
//		vo.setActSellNumber(10);
//		vo.setActJoinNumber(3);
//		vo.setActEvaluationNumber(8);
//		vo.setActAverageStarNumber(4.5);
//		vo.setActState(true);
//		dao.insert(vo);
//		dao.update(vo);
//		System.out.println(vo);
	}
}
