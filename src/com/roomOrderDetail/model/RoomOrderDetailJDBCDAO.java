package com.roomOrderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.roomOrder.model.RoomOrderVO;
import com.util.JDBCUtil;

public class RoomOrderDetailJDBCDAO implements I_RoomOrderDetailDAO{
	private static final String INSERT = "INSERT INTO room_order_detail (ord_no) VALUES (?)";
	private static final String CHECKIN = "UPDATE room_order_detail SET checkin_date = CURDATE(), rm_no = ?, detail_state = 2 WHERE detail_no = ?";
	private static final String CHECKOUT = "UPDATE room_order_detail SET checkout_date = CURDATE(), rm_no = null, detail_state = 3 WHERE detail_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_order_detail WHERE detail_no = ?"; 
	private static final String GET_ALL = "SELECT * FROM room_order_detail"; 
	private static final String GET_ALL_BY_ORDNO = "SELECT * FROM room_order_detail WHERE ord_no = ?";
	
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(RoomOrderDetailVO detailvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, detailvo.getOrd_no());
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
	public void checkin(RoomOrderDetailVO derdetailvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(CHECKIN);
			pstmt.setString(1, derdetailvo.getRm_no());
			pstmt.setInt(2, derdetailvo.getDetail_no());
			
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
	public void checkout(RoomOrderDetailVO derdetailvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(CHECKOUT);
			pstmt.setInt(1, derdetailvo.getDetail_no());
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
	public RoomOrderDetailVO getOne(Integer detail_no) {
		RoomOrderDetailVO detailvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, detail_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				detailvo = new RoomOrderDetailVO();
				detailvo.setDetail_no(rs.getInt("detail_no"));				
				detailvo.setOrd_no(rs.getInt("ord_no"));
				detailvo.setCheckin_date(rs.getDate("checkin_date").toLocalDate());
				//null
				detailvo.setCheckout_date(rs.getDate("checkout_date").toLocalDate());
				detailvo.setRm_no(rs.getString("rm_no"));	
				detailvo.setDetail_state(rs.getInt("detail_state"));
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
		return detailvo;
	}

	@Override
	public List<RoomOrderDetailVO> getAll() {
		List<RoomOrderDetailVO> list = new ArrayList<>();
		RoomOrderDetailVO detailvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				detailvo = new RoomOrderDetailVO();
				detailvo.setDetail_no(rs.getInt("detail_no"));				
				detailvo.setOrd_no(rs.getInt("ord_no"));
				detailvo.setCheckin_date(rs.getDate("checkin_date").toLocalDate());
				detailvo.setCheckout_date(rs.getDate("checkout_date").toLocalDate());
				detailvo.setRm_no(rs.getString("rm_no"));	
				detailvo.setDetail_state(rs.getInt("detail_state"));
				list.add(detailvo);
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
	public List<RoomOrderDetailVO> getAllByOrdno(Integer ord_no) {
		List<RoomOrderDetailVO> list = new ArrayList<>();
		RoomOrderDetailVO detailvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_ORDNO);
			pstmt.setInt(1, ord_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				detailvo = new RoomOrderDetailVO();
				detailvo.setDetail_no(rs.getInt("detail_no"));				
				detailvo.setOrd_no(rs.getInt("ord_no"));
				detailvo.setCheckin_date(rs.getDate("checkin_date").toLocalDate());
				detailvo.setCheckout_date(rs.getDate("checkout_date").toLocalDate());
				detailvo.setRm_no(rs.getString("rm_no"));	
				detailvo.setDetail_state(rs.getInt("detail_state"));
				list.add(detailvo);
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
