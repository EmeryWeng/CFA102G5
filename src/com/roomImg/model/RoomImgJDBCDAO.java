package com.roomImg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class RoomImgJDBCDAO implements I_RoomImgDAO {
	private static final String INSERT = "INSERT INTO room_img (type_no, type_img) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM room_img WHERE img_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_img WHERE img_no = ?";
	private static final String GET_ALL_BY_TYPE = "SELECT * FROM room_img WHERE type_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(RoomImgVO roomimgvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, roomimgvo.getType_no());
			pstmt.setBytes(2, roomimgvo.getType_img());
			
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
	public void delete(Integer img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, img_no);
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
	public RoomImgVO getOne(Integer img_no) {
		RoomImgVO roomimg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, img_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomimg = new RoomImgVO();
				roomimg.setImg_no(rs.getInt("img_no"));
				roomimg.setType_no(rs.getInt("type_no"));
				roomimg.setType_img(rs.getBytes("type_img"));
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
		return roomimg;
	}

	@Override
	public List<RoomImgVO> getAllByType(Integer type_no) {
		List<RoomImgVO> list = new ArrayList<>();
		RoomImgVO roomimg = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(GET_ALL_BY_TYPE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomimg = new RoomImgVO();
				roomimg.setImg_no(rs.getInt("img_no"));
				roomimg.setType_no(rs.getInt("type_no"));
				roomimg.setType_img(rs.getBytes("type_img"));
				list.add(roomimg);
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
