package com.roomType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class RoomTypeJDBCDAO implements I_RoomTypeDAO {
	private static final String INSERT = "INSERT INTO room_type (type_name, type_qty, type_price, type_size, bed_size, type_info, type_facility) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE room_type SET type_name = ?, type_qty = ?, type_price = ?, type_size = ?, bed_size = ?, type_info = ? ,type_facility = ? ,type_state = ? WHERE type_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_type WHERE type_no = ?";
	private static final String GET_ALL = "SELECT * FROM room_type ORDER BY type_no";
	
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(RoomTypeVO roomtypevo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, roomtypevo.getType_name());
			pstmt.setInt(2, roomtypevo.getType_qty());
			pstmt.setInt(3, roomtypevo.getType_price());
			pstmt.setInt(4, roomtypevo.getType_size());
			pstmt.setString(5, roomtypevo.getBed_size());
			pstmt.setString(6, roomtypevo.getType_info());
			pstmt.setString(7, roomtypevo.getType_facility());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(RoomTypeVO roomtypevo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, roomtypevo.getType_name());
			pstmt.setInt(2, roomtypevo.getType_qty());
			pstmt.setInt(3, roomtypevo.getType_price());
			pstmt.setInt(4, roomtypevo.getType_size());
			pstmt.setString(5, roomtypevo.getBed_size());
			pstmt.setString(6, roomtypevo.getType_info());
			pstmt.setString(7, roomtypevo.getType_facility());
			pstmt.setBoolean(8, roomtypevo.getType_state());
			pstmt.setInt(9, roomtypevo.getType_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public RoomTypeVO getOne(Integer type_no) {
		RoomTypeVO roomtype = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomtype = new RoomTypeVO();
				roomtype.setType_no(rs.getInt("type_no"));
				roomtype.setType_name(rs.getString("type_name"));
				roomtype.setType_qty(rs.getInt("type_qty"));
				roomtype.setType_price(rs.getInt("type_price"));
				roomtype.setType_size(rs.getInt("type_size"));
				roomtype.setBed_size(rs.getString("bed_size"));
				roomtype.setType_info(rs.getString("type_info"));
				roomtype.setType_facility(rs.getString("type_facility"));
				roomtype.setType_state(rs.getBoolean("type_state"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomtype;
	}
	
	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> list = new ArrayList<>();
		RoomTypeVO roomtype = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomtype = new RoomTypeVO();
				roomtype.setType_no(rs.getInt("type_no"));
				roomtype.setType_name(rs.getString("type_name"));
				roomtype.setType_qty(rs.getInt("type_qty"));
				roomtype.setType_price(rs.getInt("type_price"));
				roomtype.setType_size(rs.getInt("type_size"));
				roomtype.setBed_size(rs.getString("bed_size"));
				roomtype.setType_info(rs.getString("type_info"));
				roomtype.setType_facility(rs.getString("type_facility"));
				roomtype.setType_state(rs.getBoolean("type_state"));
				list.add(roomtype);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
