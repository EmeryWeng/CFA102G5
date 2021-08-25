package com.roomOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCUtil;

public class RoomOrderJDBCDAO implements I_RoomOrderDAO{
	private static final String INSERT = "INSERT INTO room_order (mem_no, type_no, start_date, end_date, rm_num, price, total_price, note, title, name, phone, email, payment, ord_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? , NOW())";
	private static final String UPDATE = "UPDATE room_order SET ord_state = 4 WHERE end_date < CURDATE()";
	private static final String CANCEL = "UPDATE room_order SET total_price = ?, ord_state = 3 WHERE ord_no = ?";
	private static final String CHANGE = "UPDATE room_order SET start_date = ?, end_date = ?,ord_state = 2 WHERE ord_no = ?";
	private static final String GET_ONE = "SELECT * FROM room_order WHERE _no = ?"; 
	private static final String GET_ALL = "SELECT * FROM room_order"; 
	private static final String GET_ALL_BY_MEM = "SELECT * FROM room_order WHERE mem_no = ?";
	
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(RoomOrderVO roomordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setInt(1, roomordervo.getMem_no());
			pstmt.setInt(2, roomordervo.getType_no());
			pstmt.setObject(3, roomordervo.getStart_date());
			pstmt.setObject(4, roomordervo.getEnd_date());
			pstmt.setInt(5, roomordervo.getRm_num());
			pstmt.setInt(6, roomordervo.getPrice());
			pstmt.setInt(7, roomordervo.getTotal_price());
			pstmt.setString(8, roomordervo.getNote());
			pstmt.setString(9, roomordervo.getTitle());
			pstmt.setString(10, roomordervo.getName());
			pstmt.setString(11, roomordervo.getPhone());
			pstmt.setString(12, roomordervo.getEmail());
			pstmt.setString(13, roomordervo.getPayment());
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
	public void update(RoomOrderVO roomordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
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
	public void cancel(RoomOrderVO roomordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, roomordervo.getTotal_price());
			pstmt.setInt(2, roomordervo.getOrd_no());
			
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
	public void change(RoomOrderVO roomordervo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(CHANGE);
			
			pstmt.setObject(1, roomordervo.getStart_date());
			pstmt.setObject(2, roomordervo.getEnd_date());
			pstmt.setInt(3, roomordervo.getOrd_no());
			
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
	public RoomOrderVO getOne(Integer ord_no) {
		RoomOrderVO roomordervo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, ord_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomordervo = new RoomOrderVO();
				roomordervo.setOrd_no(rs.getInt("ord_no"));
				roomordervo.setMem_no(rs.getInt("mem_no"));
				roomordervo.setType_no(rs.getInt("type_no"));
//				LocalDate startdate;
//				startdate = rs.getDate("start_date").toLocalDate();
//				(startdate != null) ? startdate : (0000,01,01);
				
				roomordervo.setStart_date(rs.getDate("start_date").toLocalDate());
				roomordervo.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomordervo.setRm_num(rs.getInt("rm_num"));
				roomordervo.setPrice(rs.getInt("price"));
				roomordervo.setTotal_price(rs.getInt("total_price"));				
				roomordervo.setNote(rs.getString("note"));	
				roomordervo.setTitle(rs.getString("title"));	
				roomordervo.setName(rs.getString("name"));	
				roomordervo.setPhone(rs.getString("phone"));	
				roomordervo.setEmail(rs.getString("email"));	
				roomordervo.setPayment(rs.getString("payment"));
				roomordervo.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomordervo.setOrd_state(rs.getInt("ord_state"));
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
		return roomordervo;
	}
	@Override
	public List<RoomOrderVO> getAll() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomordervo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomordervo = new RoomOrderVO();
				roomordervo.setOrd_no(rs.getInt("ord_no"));
				roomordervo.setMem_no(rs.getInt("mem_no"));
				roomordervo.setType_no(rs.getInt("type_no"));
				roomordervo.setStart_date(rs.getDate("start_date").toLocalDate());
				roomordervo.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomordervo.setRm_num(rs.getInt("rm_num"));
				roomordervo.setPrice(rs.getInt("price"));
				roomordervo.setTotal_price(rs.getInt("total_price"));				
				roomordervo.setNote(rs.getString("note"));	
				roomordervo.setTitle(rs.getString("title"));	
				roomordervo.setName(rs.getString("name"));	
				roomordervo.setPhone(rs.getString("phone"));	
				roomordervo.setEmail(rs.getString("email"));	
				roomordervo.setPayment(rs.getString("payment"));
				roomordervo.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomordervo.setOrd_state(rs.getInt("ord_state"));
				list.add(roomordervo);
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
	public List<RoomOrderVO> getAllByMem(Integer mem_no) {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomordervo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_MEM);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomordervo = new RoomOrderVO();
				roomordervo.setOrd_no(rs.getInt("ord_no"));
				roomordervo.setMem_no(rs.getInt("mem_no"));
				roomordervo.setType_no(rs.getInt("type_no"));
				roomordervo.setStart_date(rs.getDate("start_date").toLocalDate());
				roomordervo.setEnd_date(rs.getDate("end_date").toLocalDate());
				roomordervo.setRm_num(rs.getInt("rm_num"));
				roomordervo.setPrice(rs.getInt("price"));
				roomordervo.setTotal_price(rs.getInt("total_price"));				
				roomordervo.setNote(rs.getString("note"));	
				roomordervo.setTitle(rs.getString("title"));	
				roomordervo.setName(rs.getString("name"));	
				roomordervo.setPhone(rs.getString("phone"));	
				roomordervo.setEmail(rs.getString("email"));	
				roomordervo.setPayment(rs.getString("payment"));
				roomordervo.setOrd_date(rs.getDate("ord_date").toLocalDate());
				roomordervo.setOrd_state(rs.getInt("ord_state"));
				list.add(roomordervo);
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
