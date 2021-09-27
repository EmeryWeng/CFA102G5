package com.roomRsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.room.model.RoomService;

public class RoomRsvDAO implements I_RoomRsvDAO {

	private static final String INSERT = "INSERT INTO room_rsv (rsv_date, type_no, rm_total ,rsv_total) VALUES (?, ?, ?, ?)";
//	兩間*間數 ?房型 8/26*入住日 *入住日 4*天數   要有訂單內容
	private static final String RESERVE = "UPDATE room_rsv SET rsv_total = rsv_total+間數  WHERE type_no = ? AND (rsv_date BETWEEN '入住日' AND DATEDIFF( '退房日', INTERVAL 1 DAY))";
	private static final String CANCEL = "UPDATE room_rsv SET rsv_total = rsv_total-間數  WHERE type_no = ? AND (rsv_date BETWEEN '2021-08-26' AND DATEDIFF( '退房日', INTERVAL 1 DAY))";
	private static final String GET_ONE_BY_DATE_TYPE = "SELECT * FROM room_rsv WHERE rsv_date = ? AND rm_type = ?";
	private static final String GET_ONEDAY_BY_DATE = "SELECT * FROM room_rsv WHERE rsv_date = ?";
	private static final String GET_NOT_RSV = "SELECT * FROM room_rsv WHERE (rm_total-rsv_total) < ? AND type_no = ?";
	private static final String GET_ALL = "SELECT * FROM room_rsv ORDER BY rsv_date";
	private static final String GET_ALL_BY_TYPE = "SELECT * FROM room_rsv WHERE type_no = ?";

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
	public void insert(RoomRsvVO roomRsvVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setObject(1, roomRsvVO.getRsv_date());
			pstmt.setInt(2, roomRsvVO.getType_no());
			pstmt.setInt(3, roomRsvVO.getRm_total());
			pstmt.setInt(4, roomRsvVO.getRsv_total());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public void reserve(RoomRsvVO roomRsvVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(RESERVE);

			pstmt.setInt(1, roomRsvVO.getType_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public void cancel(RoomRsvVO roomRsvVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CANCEL);

			pstmt.setInt(1, roomRsvVO.getType_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public RoomRsvVO getOneByDateType(LocalDate rsv_date, Integer type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_DATE_TYPE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomRsvVO = new RoomRsvVO();
				roomRsvVO.setRsv_date(rs.getDate("rsv_date").toLocalDate());
				roomRsvVO.setType_no(rs.getInt("type_no"));
				roomRsvVO.setRm_total(rs.getInt("rm_total"));
				roomRsvVO.setRsv_total(rs.getInt("rsv_total"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomRsvVO;
	}

	@Override
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEDAY_BY_DATE);
			pstmt.setObject(1, rsv_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomRsvVO = new RoomRsvVO();
				roomRsvVO.setRsv_date(rs.getDate("rsv_date").toLocalDate());
				roomRsvVO.setType_no(rs.getInt("type_no"));
				roomRsvVO.setRm_total(rs.getInt("rm_total"));
				roomRsvVO.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomRsvVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public List<RoomRsvVO> getNotRsv(Integer qty, Integer type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NOT_RSV);
			pstmt.setObject(1, qty);
			pstmt.setObject(2, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomRsvVO = new RoomRsvVO();
				roomRsvVO.setRsv_date(rs.getDate("rsv_date").toLocalDate());
				roomRsvVO.setType_no(rs.getInt("type_no"));
				roomRsvVO.setRm_total(rs.getInt("rm_total"));
				roomRsvVO.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomRsvVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomRsvVO = new RoomRsvVO();
				roomRsvVO.setRsv_date(rs.getDate("rsv_date").toLocalDate());
				roomRsvVO.setType_no(rs.getInt("type_no"));
				roomRsvVO.setRm_total(rs.getInt("rm_total"));
				roomRsvVO.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomRsvVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_TYPE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomRsvVO = new RoomRsvVO();
				roomRsvVO.setRsv_date(rs.getDate("rsv_date").toLocalDate());
				roomRsvVO.setType_no(rs.getInt("type_no"));
				roomRsvVO.setRm_total(rs.getInt("rm_total"));
				roomRsvVO.setRsv_total(rs.getInt("rsv_total"));
				list.add(roomRsvVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
	public Integer roomCheck(LocalDate rsv_date, Integer stay, Integer type_no) {
		Connection con = null;
		Integer rmLeft = null;

		try {
			con = ds.getConnection();
//			RoomTypeService rmtypeSvc = new RoomTypeService();
//			RoomTypeVO rmtypevo = rmtypeSvc.getOneRoomType(type_no);
			RoomService rmSvc = new RoomService();
			rmLeft = rmSvc.getRmTotal(type_no);
			for (int i = 0; i < stay; i++) {
				RoomRsvVO rsvvo = getOneByDateType(rsv_date.plusDays(i), type_no);
				Integer rm_left = rsvvo.getRm_total() - rsvvo.getRsv_total();
				if (rsvvo == null) {
					continue;
				} else if (rm_left == 0) {
					rmLeft = 0;
					break;
				} else {
					rmLeft = Math.min(rm_left, rmLeft);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmLeft;
	}
}
