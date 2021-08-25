package com.activityOrderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class ActivityOrderDetailJDBCDAO implements I_ActivityOrderDetailDAO {

	private final String[] GET_KEY = {"act_order_no","act_session_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_ORDER_DETAIL VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_ORDER_DETAIL SET act_order_no = ?,act_session_no = ?,act_real_join_number = ?,act_order_price = ?,act_coupon_price = ? "
			+ ",act_price_total = ?,act_order_detail_state = ? WHERE act_order_no = ? and act_session_no = ?";
	private final String SELECT_BY_ACTIVITY_ORDER_NO_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL WHERE act_order_no = ?";
	private final String SELECT_BY_ACTIVITY_SESSION_NO_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL WHERE act_session_no = ?";
	private final String SELECT_BY_ACTIVITY_ORDER_DETAIL_STATE_TRUE_SQL = "SELECT * FROM ACTIVITY_ORDER_DETAIL WHERE act_order_detail_state = 1";
	
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public ActivityOrderDetailVO insert(ActivityOrderDetailVO actOrderDetailVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY);
			ps.setInt(1,actOrderDetailVO.getAct_order_no());
			ps.setInt(2,actOrderDetailVO.getAct_session_no());
			ps.setInt(3,actOrderDetailVO.getAct_real_join_number());
			ps.setInt(4,actOrderDetailVO.getAct_order_price());
			ps.setInt(5,actOrderDetailVO.getAct_coupon_price());
			ps.setInt(6,actOrderDetailVO.getAct_price_total());
			ps.setInt(7,actOrderDetailVO.getAct_order_state());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actOrderDetailVO.setAct_order_no(rs.getInt(1));
				actOrderDetailVO.setAct_session_no(rs.getInt(2));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return actOrderDetailVO;
	}

	@Override
	public void update(ActivityOrderDetailVO actOrderDetailVO,Integer act_order_no,Integer act_session_no) {
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
			ps.setInt(1,actOrderDetailVO.getAct_order_no()); 
			ps.setInt(2,actOrderDetailVO.getAct_session_no());
			ps.setInt(3,actOrderDetailVO.getAct_real_join_number());
			ps.setInt(4,actOrderDetailVO.getAct_order_price());
			ps.setInt(5,actOrderDetailVO.getAct_coupon_price());
			ps.setInt(6,actOrderDetailVO.getAct_price_total());
			ps.setInt(7,actOrderDetailVO.getAct_order_state());
			ps.setInt(8,act_order_no); 
			ps.setInt(9,act_session_no);
			
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public List<ActivityOrderDetailVO> findByActOrderNo(Integer act_order_no) {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ORDER_NO_SQL);
			ps.setInt(1, act_order_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setAct_order_no(rs.getInt(1));
				actOrderDetailVO.setAct_session_no(rs.getInt(2));
				actOrderDetailVO.setAct_real_join_number(rs.getInt(3));
				actOrderDetailVO.setAct_order_price(rs.getInt(4));
				actOrderDetailVO.setAct_coupon_price(rs.getInt(5));
				actOrderDetailVO.setAct_price_total(rs.getInt(6));
				actOrderDetailVO.setAct_order_state(rs.getInt(7));
				list.add(actOrderDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public List<ActivityOrderDetailVO> findByActSessionNo(Integer act_session_no) {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_SESSION_NO_SQL);
			ps.setInt(1, act_session_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setAct_order_no(rs.getInt(1));
				actOrderDetailVO.setAct_session_no(rs.getInt(2));
				actOrderDetailVO.setAct_real_join_number(rs.getInt(3));
				actOrderDetailVO.setAct_order_price(rs.getInt(4));
				actOrderDetailVO.setAct_coupon_price(rs.getInt(5));
				actOrderDetailVO.setAct_price_total(rs.getInt(6));
				actOrderDetailVO.setAct_order_state(rs.getInt(7));
				list.add(actOrderDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public List<ActivityOrderDetailVO> getActOrderDetailToFront() {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ORDER_DETAIL_STATE_TRUE_SQL);
			rs = ps.executeQuery();	
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setAct_order_no(rs.getInt(1));
				actOrderDetailVO.setAct_session_no(rs.getInt(2));
				actOrderDetailVO.setAct_real_join_number(rs.getInt(3));
				actOrderDetailVO.setAct_order_price(rs.getInt(4));
				actOrderDetailVO.setAct_coupon_price(rs.getInt(5));
				actOrderDetailVO.setAct_price_total(rs.getInt(6));
				actOrderDetailVO.setAct_order_state(rs.getInt(7));
				list.add(actOrderDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public List<ActivityOrderDetailVO> getAll() {
		List<ActivityOrderDetailVO> list = new ArrayList<>();
		ActivityOrderDetailVO actOrderDetailVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			
			PreparedStatement ps = con.prepareStatement(SELECT_All_SQL);
			rs = ps.executeQuery();	
			while (rs.next()) {
				actOrderDetailVO = new ActivityOrderDetailVO();
				actOrderDetailVO.setAct_order_no(rs.getInt(1));
				actOrderDetailVO.setAct_session_no(rs.getInt(2));
				actOrderDetailVO.setAct_real_join_number(rs.getInt(3));
				actOrderDetailVO.setAct_order_price(rs.getInt(4));
				actOrderDetailVO.setAct_coupon_price(rs.getInt(5));
				actOrderDetailVO.setAct_price_total(rs.getInt(6));
				actOrderDetailVO.setAct_order_state(rs.getInt(7));
				list.add(actOrderDetailVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return list;
	}
	public static void main(String[] args) {
		ActivityOrderDetailJDBCDAO dao = new ActivityOrderDetailJDBCDAO();
//		ActivityOrderDetailVO vo = new ActivityOrderDetailVO();
//		
//		vo.setAct_order_no(1);
//		vo.setAct_session_no(1);
//		vo.setAct_real_join_number(3);
//		vo.setAct_order_price(2850);
//		vo.setAct_coupon_price(600);
//		vo.setAct_price_total(2250);
//		vo.setAct_order_state(1);
//		
//		dao.insert(vo);
//		dao.update(vo,1,1);
//		List<ActivityOrderDetailVO> list =dao.findByActOrderNo(1);
//		List<ActivityOrderDetailVO> list =dao.findByActSessionNo(1);
		List<ActivityOrderDetailVO> list = dao.getAll();
//		List<ActivityOrderDetailVO> list = dao.getActOrderDetailToFront();
		for(ActivityOrderDetailVO vo : list)
		System.out.println(vo);
	}
}
