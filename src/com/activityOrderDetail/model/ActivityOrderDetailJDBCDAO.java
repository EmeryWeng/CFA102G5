package com.activityOrderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityOrderDetailJDBCDAO implements I_ActivityOrderDetailDAO {

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_order_no","act_session_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_ORDER_DETAIL VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_ORDER_DETAIL SET act_order_no = ?,act_session_no = ?,act_real_join_number = ?,act_order_price = ?,act_coupon_price = ? "
			+ ",act_price_total = ?,act_order_state = ? WHERE act_order_no = ? and act_session_no = ?";
	private final String SELECT_BY_ACTIVITY_ORDER_ID_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL WHERE act_order_no = ?";
	private final String SELECT_BY_ACTIVITY_SESSION_ID_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL WHERE act_session_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public ActivityOrderDetailVO insert(ActivityOrderDetailVO actOrderDetailVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setInt(1,actOrderDetailVO.getActOrderId());
			ps.setInt(2,actOrderDetailVO.getActSessionId());
			ps.setInt(3,actOrderDetailVO.getActRealJoinNumber());
			ps.setInt(4,actOrderDetailVO.getActOrderPrice());
			ps.setInt(5,actOrderDetailVO.getActCouponPrice());
			ps.setInt(6,actOrderDetailVO.getActPriceTotal());
			ps.setInt(7,actOrderDetailVO.getActOrderState());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actOrderDetailVO.setActOrderId(rs.getInt(1));
				actOrderDetailVO.setActSessionId(rs.getInt(2));
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
		return actOrderDetailVO;
	}

	@Override
	public void update(ActivityOrderDetailVO actOrderDetailVO,Integer actOrderId,Integer actSessionId) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1,actOrderDetailVO.getActOrderId()); 
			ps.setInt(2,actOrderDetailVO.getActSessionId());
			ps.setInt(3,actOrderDetailVO.getActRealJoinNumber());
			ps.setInt(4,actOrderDetailVO.getActOrderPrice());
			ps.setInt(5,actOrderDetailVO.getActCouponPrice());
			ps.setInt(6,actOrderDetailVO.getActPriceTotal());
			ps.setInt(7,actOrderDetailVO.getActOrderState());
			ps.setInt(8,actOrderId); 
			ps.setInt(9,actSessionId);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public List<ActivityOrderDetailVO> findByActOrderId(Integer actOrderId) {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ORDER_ID_SQL)) {
			ps.setInt(1, actOrderId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setActOrderId(rs.getInt(1));
				actOrderDetailVO.setActSessionId(rs.getInt(2));
				actOrderDetailVO.setActRealJoinNumber(rs.getInt(3));
				actOrderDetailVO.setActOrderPrice(rs.getInt(4));
				actOrderDetailVO.setActCouponPrice(rs.getInt(5));
				actOrderDetailVO.setActPriceTotal(rs.getInt(6));
				actOrderDetailVO.setActOrderState(rs.getInt(7));
				list.add(actOrderDetailVO);
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
	public List<ActivityOrderDetailVO> findByActSessionId(Integer actSessionId) {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_SESSION_ID_SQL)) {
			ps.setInt(1, actSessionId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setActOrderId(rs.getInt(1));
				actOrderDetailVO.setActSessionId(rs.getInt(2));
				actOrderDetailVO.setActRealJoinNumber(rs.getInt(3));
				actOrderDetailVO.setActOrderPrice(rs.getInt(4));
				actOrderDetailVO.setActCouponPrice(rs.getInt(5));
				actOrderDetailVO.setActPriceTotal(rs.getInt(6));
				actOrderDetailVO.setActOrderState(rs.getInt(7));
				list.add(actOrderDetailVO);
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
	public List<ActivityOrderDetailVO> getAll() {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();	
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setActOrderId(rs.getInt(1));
				actOrderDetailVO.setActSessionId(rs.getInt(2));
				actOrderDetailVO.setActRealJoinNumber(rs.getInt(3));
				actOrderDetailVO.setActOrderPrice(rs.getInt(4));
				actOrderDetailVO.setActCouponPrice(rs.getInt(5));
				actOrderDetailVO.setActPriceTotal(rs.getInt(6));
				actOrderDetailVO.setActOrderState(rs.getInt(7));
				list.add(actOrderDetailVO);
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
		ActivityOrderDetailJDBCDAO dao = new ActivityOrderDetailJDBCDAO();
//		ActivityOrderDetailVO vo = new ActivityOrderDetailVO();
//		
//		vo.setActOrderId(1);
//		vo.setActSessionId(1);
//		vo.setActRealJoinNumber(3);
//		vo.setActOrderPrice(2850);
//		vo.setActCouponPrice(600);
//		vo.setActPriceTotal(2250);
//		vo.setActOrderState(1);
		
//		dao.insert(vo);
//		dao.update(vo,1,1);
//		List<ActivityOrderDetailVO> list =dao.findByActOrderId(1);
//		List<ActivityOrderDetailVO> list =dao.findByActSessionId(1);
		List<ActivityOrderDetailVO> list = dao.getAll();
		for(ActivityOrderDetailVO vo : list)
		System.out.println(vo);
	}
}
