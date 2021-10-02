package com.roomType.model;

import java.sql.Connection;
import java.sql.Date;
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
	private static final String GET_ALL_FRONT = "SELECT * FROM room_type WHERE type_state=true ORDER BY type_no";
	private static final String CHANGE_STATE = "UPDATE room_type SET type_state = ? WHERE type_no = ?";
	private static final String GET_ENOUGH_TYPE = "SELECT * FROM room_type JOIN (SELECT MIN(rm_total-rsv_total) as minqty, type_no as typeno FROM room_rsv WHERE rsv_date BETWEEN ? AND ? GROUP BY type_no) as rsv ON room_type.type_no = rsv.typeno WHERE minqty >= ? AND type_qty >= ? AND type_state = 1";
	private static final String GET_NOT_ENOUGH_TYPE = "SELECT * FROM room_type LEFT JOIN (SELECT MIN(rm_total-rsv_total) as minqty, type_no as typeno FROM room_rsv WHERE rsv_date BETWEEN ? AND ? GROUP BY type_no) as rsv ON room_type.type_no = rsv.typeno WHERE minqty < ? OR type_qty < ? AND type_state = 1";

	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public RoomTypeVO insert(RoomTypeVO roomTypeVO) {

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
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

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
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

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
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

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
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

	@Override
	public List<RoomTypeVO> getAllFront() {
		List<RoomTypeVO> list = new ArrayList<>();
		RoomTypeVO roomTypeVO = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_FRONT);
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

	@Override
	public void changeState(Integer type_no, Boolean type_state) {

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(CHANGE_STATE);

			pstmt.setBoolean(1, type_state);
			pstmt.setInt(2, type_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public List<RoomTypeVO> getEnoughType(Date start_date, Date end_date, Integer qty, Integer guest) {
		List<RoomTypeVO> list = new ArrayList<>();
		RoomTypeVO roomTypeVO = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_ENOUGH_TYPE);
			pstmt.setDate(1, start_date);
			pstmt.setDate(2, end_date);
			pstmt.setInt(3, qty);
			pstmt.setInt(4, guest);
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

	@Override
	public List<RoomTypeVO> getNotEnoughType(Date start_date, Date end_date, Integer qty, Integer guest) {
		List<RoomTypeVO> list = new ArrayList<>();
		RoomTypeVO roomTypeVO = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USERNAME, JDBCUtil.PASSWORD)) {
			PreparedStatement pstmt = con.prepareStatement(GET_NOT_ENOUGH_TYPE);
			pstmt.setDate(1, start_date);
			pstmt.setDate(2, end_date);
			pstmt.setInt(3, qty);
			pstmt.setInt(4, guest);
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
