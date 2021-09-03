package com.roomOrder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomOrderDAO implements I_RoomOrderDAO{
	private static final String INSERT = "INSERT INTO room_order (mem_no, type_no, start_date, end_date, rm_num, price, total_price, note, title, name, phone, email, payment, ord_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? , NOW())";
	private static final String UPDATE = "UPDATE room_order SET ord_state = 4 WHERE start_date < CURDATE()";
	private static final String CANCEL = "UPDATE room_order SET total_price = ?, ord_state = 3 WHERE ord_no = ?";
	private static final String CHANGE = "UPDATE room_order SET start_date = ?, end_date = ?,ord_state = 2 WHERE ord_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_order WHERE _no = ?"; 
	private static final String GET_ALL = "SELECT * FROM room_order"; 
	private static final String GET_ALL_BY_MEM = "SELECT * FROM room_order WHERE mem_no = ?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public RoomOrderVO insert(RoomOrderVO roomOrderVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, roomOrderVO.getMem_no());
			pstmt.setInt(2, roomOrderVO.getType_no());
			pstmt.setObject(3, roomOrderVO.getStart_date());
			pstmt.setObject(4, roomOrderVO.getEnd_date());
			pstmt.setInt(5, roomOrderVO.getRm_num());
			pstmt.setInt(6, roomOrderVO.getPrice());
			pstmt.setInt(7, roomOrderVO.getTotal_price());
			pstmt.setString(8, roomOrderVO.getNote());
			pstmt.setString(9, roomOrderVO.getTitle());
			pstmt.setString(10, roomOrderVO.getName());
			pstmt.setString(11, roomOrderVO.getPhone());
			pstmt.setString(12, roomOrderVO.getEmail());
			pstmt.setString(13, roomOrderVO.getPayment());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return roomOrderVO;
	}
	
	@Override
	public void update(RoomOrderVO roomOrderVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	@Override
	public void cancel(RoomOrderVO roomOrderVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, roomOrderVO.getTotal_price());
			pstmt.setInt(2, roomOrderVO.getOrd_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	@Override
	public void change(RoomOrderVO roomOrderVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(CHANGE);
			
			pstmt.setObject(1, roomOrderVO.getStart_date());
			pstmt.setObject(2, roomOrderVO.getEnd_date());
			pstmt.setInt(3, roomOrderVO.getOrd_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	@Override
	public RoomOrderVO getOne(Integer ord_no) {
		RoomOrderVO roomOrderVO = null;
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, ord_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrd_no(rs.getInt("ord_no"));
				roomOrderVO.setMem_no(rs.getInt("mem_no"));
				roomOrderVO.setType_no(rs.getInt("type_no"));			
				roomOrderVO.setStart_date(rs.getDate("start_date").toLocalDate());
				roomOrderVO.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomOrderVO.setRm_num(rs.getInt("rm_num"));
				roomOrderVO.setPrice(rs.getInt("price"));
				roomOrderVO.setTotal_price(rs.getInt("total_price"));				
				roomOrderVO.setNote(rs.getString("note"));	
				roomOrderVO.setTitle(rs.getString("title"));	
				roomOrderVO.setName(rs.getString("name"));	
				roomOrderVO.setPhone(rs.getString("phone"));	
				roomOrderVO.setEmail(rs.getString("email"));	
				roomOrderVO.setPayment(rs.getString("payment"));
				roomOrderVO.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomOrderVO.setOrd_state(rs.getInt("ord_state"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return roomOrderVO;
	}
	
	@Override
	public List<RoomOrderVO> getAll() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrd_no(rs.getInt("ord_no"));
				roomOrderVO.setMem_no(rs.getInt("mem_no"));
				roomOrderVO.setType_no(rs.getInt("type_no"));
				roomOrderVO.setStart_date(rs.getDate("start_date").toLocalDate());
				roomOrderVO.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomOrderVO.setRm_num(rs.getInt("rm_num"));
				roomOrderVO.setPrice(rs.getInt("price"));
				roomOrderVO.setTotal_price(rs.getInt("total_price"));				
				roomOrderVO.setNote(rs.getString("note"));	
				roomOrderVO.setTitle(rs.getString("title"));	
				roomOrderVO.setName(rs.getString("name"));	
				roomOrderVO.setPhone(rs.getString("phone"));	
				roomOrderVO.setEmail(rs.getString("email"));	
				roomOrderVO.setPayment(rs.getString("payment"));
				roomOrderVO.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomOrderVO.setOrd_state(rs.getInt("ord_state"));
				list.add(roomOrderVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}
	@Override
	public List<RoomOrderVO> getAllByMem(Integer mem_no) {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_BY_MEM);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrd_no(rs.getInt("ord_no"));
				roomOrderVO.setMem_no(rs.getInt("mem_no"));
				roomOrderVO.setType_no(rs.getInt("type_no"));
				roomOrderVO.setStart_date(rs.getDate("start_date").toLocalDate());
				roomOrderVO.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomOrderVO.setRm_num(rs.getInt("rm_num"));
				roomOrderVO.setPrice(rs.getInt("price"));
				roomOrderVO.setTotal_price(rs.getInt("total_price"));				
				roomOrderVO.setNote(rs.getString("note"));	
				roomOrderVO.setTitle(rs.getString("title"));	
				roomOrderVO.setName(rs.getString("name"));	
				roomOrderVO.setPhone(rs.getString("phone"));	
				roomOrderVO.setEmail(rs.getString("email"));	
				roomOrderVO.setPayment(rs.getString("payment"));
				roomOrderVO.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomOrderVO.setOrd_state(rs.getInt("ord_state"));
				list.add(roomOrderVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	} 
}
