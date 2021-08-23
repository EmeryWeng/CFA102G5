package com.activityOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityOrderJDBCDAO implements I_ActivityOrderDAO {

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_order_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_ORDER";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_ORDER VALUES(?,?,?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_ORDER SET mem_no = ?,act_booking_date = ?,act_order_total_price = ?,act_order_title = ?,act_order_name = ?"
			+ ",act_order_phone = ?,act_order_email = ?,act_order_credit_card = ? WHERE act_order_no = ?"; //字串串接 where前要空一行
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_ORDER WHERE act_order_no = ?";
	private final String SELECT_BY_MEMBER_ID_SQL = "SELECT * FROM ACTIVITY_ORDER WHERE mem_no = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	@Override
	public ActivityOrderVO insert(ActivityOrderVO actOrderVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1, null); // AI
			ps.setInt(2, actOrderVO.getMemId());
			ps.setObject(3, actOrderVO.getActBookingDate());
			ps.setInt(4, actOrderVO.getActOrderTotalPrice());
			ps.setString(5,actOrderVO.getActOrderTitle());
			ps.setString(6, actOrderVO.getActOrderName());
			ps.setString(7, actOrderVO.getActOrderPhone());
			ps.setString(8, actOrderVO.getActOrderEmail());
			ps.setString(9,actOrderVO.getActOrderCreditCard());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actOrderVO.setActOrderId(rs.getInt(1));
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
		return actOrderVO;
	}

	@Override
	public void update(ActivityOrderVO actOrderVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, actOrderVO.getMemId());
			ps.setObject(2, actOrderVO.getActBookingDate());
			ps.setInt(3, actOrderVO.getActOrderTotalPrice());
			ps.setString(4,actOrderVO.getActOrderTitle());
			ps.setString(5, actOrderVO.getActOrderName());
			ps.setString(6, actOrderVO.getActOrderPhone());
			ps.setString(7, actOrderVO.getActOrderEmail());
			ps.setString(8,actOrderVO.getActOrderCreditCard());
			ps.setInt(9, actOrderVO.getActOrderId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ActivityOrderVO findById(Integer actOrderId) {
		ActivityOrderVO actOrderVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			ps.setInt(1, actOrderId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderVO = new ActivityOrderVO();
				actOrderVO.setActOrderId(rs.getInt(1));
				actOrderVO.setMemId(rs.getInt(2));
				actOrderVO.setActBookingDate(rs.getTimestamp(3).toLocalDateTime());
				actOrderVO.setActOrderTotalPrice(rs.getInt(4));
				actOrderVO.setActOrderTitle(rs.getString(5));
				actOrderVO.setActOrderName(rs.getString(6));
				actOrderVO.setActOrderPhone(rs.getString(7));
				actOrderVO.setActOrderEmail(rs.getString(8));
				actOrderVO.setActOrderCreditCard(rs.getString(9));
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
		return actOrderVO;
	}

	@Override
	public List<ActivityOrderVO> findByMemberId(Integer memberId) {
		List<ActivityOrderVO> list = new ArrayList<>();
		ActivityOrderVO actOrderVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_MEMBER_ID_SQL)) {
			ps.setInt(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderVO = new ActivityOrderVO();
				actOrderVO.setActOrderId(rs.getInt(1));
				actOrderVO.setMemId(rs.getInt(2));
				actOrderVO.setActBookingDate(rs.getTimestamp(3).toLocalDateTime());
				actOrderVO.setActOrderTotalPrice(rs.getInt(4));
				actOrderVO.setActOrderTitle(rs.getString(5));
				actOrderVO.setActOrderName(rs.getString(6));
				actOrderVO.setActOrderPhone(rs.getString(7));
				actOrderVO.setActOrderEmail(rs.getString(8));
				actOrderVO.setActOrderCreditCard(rs.getString(9));
				list.add(actOrderVO);
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
	public List<ActivityOrderVO> getAll() {
		List<ActivityOrderVO> list = new ArrayList<>();
		ActivityOrderVO actOrderVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();			
			while (rs.next()) {
				actOrderVO = new ActivityOrderVO();
				actOrderVO.setActOrderId(rs.getInt(1));
				actOrderVO.setMemId(rs.getInt(2));
				actOrderVO.setActBookingDate(rs.getTimestamp(3).toLocalDateTime());
				actOrderVO.setActOrderTotalPrice(rs.getInt(4));
				actOrderVO.setActOrderTitle(rs.getString(5));
				actOrderVO.setActOrderName(rs.getString(6));
				actOrderVO.setActOrderPhone(rs.getString(7));
				actOrderVO.setActOrderEmail(rs.getString(8));
				actOrderVO.setActOrderCreditCard(rs.getString(9));
				list.add(actOrderVO);
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
		ActivityOrderJDBCDAO dao = new ActivityOrderJDBCDAO();
//		List<ActivityOrderVO> list = dao.findByMemberId(1);
//		ActivityOrderVO vo = new ActivityOrderVO();
//		ActivityOrderVO vo = dao.findById(1);
//		List<ActivityOrderVO> list = dao.getAll();
//		vo.setActOrderId(1);
//		vo.setMemId(1);
//		vo.setActBookingDate(LocalDateTime.now());
//		vo.setActOrderTotalPrice(2000);
//		vo.setActOrderTitle("先生");
//		vo.setActOrderName("測試用1");
//		vo.setActOrderPhone("0912345631");
//		vo.setActOrderEmail("xxxxx@gmail.com");
//		vo.setActOrderCreditCard("abcdefg123456789");
//		dao.insert(vo);
//		vo.setActOrderId(1);
//		
//		dao.update(vo);
		
//		ActivityOrderVO vo = dao.findById(2);
		List<ActivityOrderVO> list = dao.getAll();
		for(ActivityOrderVO vo : list)
		System.out.println(vo);
		
	}
	
}
