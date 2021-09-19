package com.roomRsv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(RoomRsvVO roomRsvVO) {
		
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(INSERT);
			
			pstmt.setObject(1, roomRsvVO.getRsv_date());
			pstmt.setInt(2, roomRsvVO.getType_no());
			pstmt.setInt(3, roomRsvVO.getRm_total());
			pstmt.setInt(4, roomRsvVO.getRsv_total());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}

	@Override
	public void reserve(RoomRsvVO roomRsvVO) {

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(RESERVE);
			
			pstmt.setInt(1, roomRsvVO.getType_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void cancel(RoomRsvVO roomRsvVO) {
		
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(CANCEL);
			
			pstmt.setInt(1, roomRsvVO.getType_no());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date) {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;
		
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONEDAY_BY_DATE);
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
			se.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomRsvVO> getAll() {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;
		
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
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
			se.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomRsvVO> getAllByType(Integer type_no) {
		List<RoomRsvVO> list = new ArrayList<>();
		RoomRsvVO roomRsvVO = null;
		ResultSet rs = null;
		
		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_BY_TYPE);
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
			se.printStackTrace();
		}
		return list;
	}
}
