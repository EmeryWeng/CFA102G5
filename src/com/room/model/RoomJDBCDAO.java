package com.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class RoomJDBCDAO implements I_RoomDAO {
	private static final String INSERT = "INSERT INTO room (rm_no, type_no, rm_info) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE room SET type_no = ?, rm_info = ?, rm_state = ?, name_title = ? WHERE rm_no = ?";
	private static final String UPDATE_CHECKIN = "UPDATE room SET rm_state = 2, name_title = ? WHERE rm_no = ?";
	private static final String UPDATE_CHECKOUT = "UPDATE room SET rm_state = 1, name_title = null WHERE rm_no = ?";
	private static final String GET_ONE = "SELECT * FROM room WHERE rm_no = ?";
	private static final String GET_ALL = "SELECT * FROM room ORDER BY rm_no";
	private static final String GET_ALL_BY_TYPE_STATE = "SELECT * FROM room WHERE type_no = ? AND rm_state = 1";
	
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(RoomVO roomvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, roomvo.getRm_no());
			pstmt.setInt(2, roomvo.getType_no());
			pstmt.setString(3, roomvo.getRm_info());
			
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
	public void update(RoomVO roomvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomvo.getType_no());
			pstmt.setString(2, roomvo.getRm_info());
			pstmt.setInt(3, roomvo.getRm_state());
			pstmt.setString(4, roomvo.getName_title());
			pstmt.setString(5, roomvo.getRm_no());
			
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
	public void updateCheckin(RoomVO roomvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_CHECKIN);
			
			pstmt.setString(1, roomvo.getName_title());
			pstmt.setString(2, roomvo.getRm_no());
			
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
	public void updateCheckout(RoomVO roomvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_CHECKOUT);
			pstmt.setString(1, roomvo.getRm_no());
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
	public RoomVO getOne(String rm_no) {
		RoomVO roomvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, rm_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomvo = new RoomVO();
				roomvo.setRm_no(rs.getString("rm_no"));
				roomvo.setType_no(rs.getInt("type_no"));
				roomvo.setRm_info(rs.getString("rm_info"));
				roomvo.setRm_state(rs.getInt("rm_state"));
				roomvo.setName_title(rs.getString("name_title"));
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
		return roomvo;
	}
	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<>();
		RoomVO roomvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomvo = new RoomVO();
				roomvo.setRm_no(rs.getString("rm_no"));
				roomvo.setType_no(rs.getInt("type_no"));
				roomvo.setRm_info(rs.getString("rm_info"));
				roomvo.setRm_state(rs.getInt("rm_state"));
				roomvo.setName_title(rs.getString("name_title"));
				list.add(roomvo);
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
	@Override
	public List<RoomVO> getAllByTypeState(Integer type_no) {
		List<RoomVO> list = new ArrayList<>();
		RoomVO roomvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_TYPE_STATE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomvo = new RoomVO();
				roomvo.setRm_no(rs.getString("rm_no"));
				roomvo.setType_no(rs.getInt("type_no"));
				roomvo.setRm_info(rs.getString("rm_info"));
				roomvo.setRm_state(rs.getInt("rm_state"));
				roomvo.setName_title(rs.getString("name_title"));
				list.add(roomvo);
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
