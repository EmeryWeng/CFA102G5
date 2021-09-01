package com.roomType.model;

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

public class RoomTypeDAO implements I_RoomTypeDAO {
	private static final String INSERT = "INSERT INTO room_type (type_name, type_qty, type_price, type_size, bed_size, type_info, type_facility) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE room_type SET type_name = ?, type_qty = ?, type_price = ?, type_size = ?, bed_size = ?, type_info = ? ,type_facility = ? ,type_state = ? WHERE type_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_type WHERE type_no = ?";
	private static final String GET_ALL = "SELECT * FROM room_type ORDER BY type_no";
	
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
	public RoomTypeVO insert(RoomTypeVO roomTypeVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, roomTypeVO.getType_name());
			pstmt.setInt(2, roomTypeVO.getType_qty());
			pstmt.setInt(3, roomTypeVO.getType_price());
			pstmt.setInt(4, roomTypeVO.getType_size());
			pstmt.setString(5, roomTypeVO.getBed_size());
			pstmt.setString(6, roomTypeVO.getType_info());
			pstmt.setString(7, roomTypeVO.getType_facility());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return roomTypeVO;
	}

	@Override
	public RoomTypeVO update(RoomTypeVO roomTypeVO) {
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, roomTypeVO.getType_name());
			pstmt.setInt(2, roomTypeVO.getType_qty());
			pstmt.setInt(3, roomTypeVO.getType_price());
			pstmt.setInt(4, roomTypeVO.getType_size());
			pstmt.setString(5, roomTypeVO.getBed_size());
			pstmt.setString(6, roomTypeVO.getType_info());
			pstmt.setString(7, roomTypeVO.getType_facility());
			pstmt.setBoolean(8, roomTypeVO.getType_state());
			pstmt.setInt(9, roomTypeVO.getType_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return roomTypeVO;
	}

	@Override
	public RoomTypeVO getOne(Integer type_no) {
		RoomTypeVO roomTypeVO = null;
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setType_no(rs.getInt("type_no"));
				roomTypeVO.setType_name(rs.getString("type_name"));
				roomTypeVO.setType_qty(rs.getInt("type_qty"));
				roomTypeVO.setType_price(rs.getInt("type_price"));
				roomTypeVO.setType_size(rs.getInt("type_size"));
				roomTypeVO.setBed_size(rs.getString("bed_size"));
				roomTypeVO.setType_info(rs.getString("type_info"));
				roomTypeVO.setType_facility(rs.getString("type_facility"));
				roomTypeVO.setType_state(rs.getBoolean("type_state"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return roomTypeVO;
	}
	
	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> list = new ArrayList<>();
		RoomTypeVO roomTypeVO = null;
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setType_no(rs.getInt("type_no"));
				roomTypeVO.setType_name(rs.getString("type_name"));
				roomTypeVO.setType_qty(rs.getInt("type_qty"));
				roomTypeVO.setType_price(rs.getInt("type_price"));
				roomTypeVO.setType_size(rs.getInt("type_size"));
				roomTypeVO.setBed_size(rs.getString("bed_size"));
				roomTypeVO.setType_info(rs.getString("type_info"));
				roomTypeVO.setType_facility(rs.getString("type_facility"));
				roomTypeVO.setType_state(rs.getBoolean("type_state"));
				list.add(roomTypeVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}
	
}
