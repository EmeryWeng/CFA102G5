package com.roomRsv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class RoomRsvJDBCDAO implements I_RoomRsvDAO {
	
	private static final String INSERT = "INSERT INTO room_rsv (rsv_date, type_no, rm_total ,rsv_total) VALUES (?, ?, ?, ?)";
//	兩間*間數 ?房型 8/26*入住日 *入住日 4*天數
	private static final String RESERVE = "UPDATE room_rsv SET rsv_total = rsv_total+2 WHERE type_no = ? AND (rsv_date BETWEEN '2021-08-26' AND ADDDATE( '2021-08-26', INTERVAL (4-1) DAY))";
	private static final String CANCEL = "UPDATE room_rsv SET rsv_total = rsv_total-2 WHERE type_no = ? AND (rsv_date BETWEEN '2021-08-26' AND ADDDATE( '2021-08-26', INTERVAL (4-1) DAY))";

	private static final String GET_ONEDAY_BY_DATE = "SELECT * FROM room_rsv WHERE rsv_date = ?";
	private static final String GET_ALL = "SELECT * FROM room_rsv ORDER BY rsv_date";
	private static final String GET_ALL_BY_TYPE = "SELECT * FROM room_rsv WHERE type_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(RoomRsvVO roomrsvvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setTimestamp(1, roomrsvvo.getRsv_date());
			pstmt.setInt(2, roomrsvvo.getType_no());
			pstmt.setInt(3, roomrsvvo.getRm_total());
			pstmt.setInt(4, roomrsvvo.getRsv_total());
			
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
	public void reserve(RoomRsvVO roomrsvvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(RESERVE);
			
			pstmt.setInt(1, roomrsvvo.getType_no());
			
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
	public void cancel(RoomRsvVO roomrsvvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, roomrsvvo.getType_no());
			
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
	public List<RoomRsvVO> getOneDayByDate(Timestamp rsv_date) {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomrsv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(GET_ONEDAY_BY_DATE);
			pstmt.setTimestamp(1, rsv_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomrsv = new RoomRsvVO();
				roomrsv.setRsv_date(rs.getTimestamp("rsv_date"));
				roomrsv.setType_no(rs.getInt("type_no"));
				roomrsv.setRm_total(rs.getInt("rm_total"));
				roomrsv.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomrsv);
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
	public List<RoomRsvVO> getAll() {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomrsv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomrsv = new RoomRsvVO();
				roomrsv.setRsv_date(rs.getTimestamp("rsv_date"));
				roomrsv.setType_no(rs.getInt("type_no"));
				roomrsv.setRm_total(rs.getInt("rm_total"));
				roomrsv.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomrsv);
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
	public List<RoomRsvVO> getAllByType(Integer type_no) {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomrsv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(GET_ALL_BY_TYPE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomrsv = new RoomRsvVO();
				roomrsv.setRsv_date(rs.getTimestamp("rsv_date"));
				roomrsv.setType_no(rs.getInt("type_no"));
				roomrsv.setRm_total(rs.getInt("rm_total"));
				roomrsv.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomrsv);
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
