package com.activityImage.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityImageJDBCDAO implements I_ActivityImageDAO {

	private final String URL = "jdbc:mysql://localhost:3306/cfa102_g5?serverTimezone=Asia/Taipei";
	private final String USERNAME = "David";
	private final String PASSWORD = "123456";
	private final String[] GET_KEY = {"act_img_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_IMAGE";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_IMAGE VALUES(?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_IMAGE SET act_no = ?,act_img = ? WHERE act_img_no = ?";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_IMAGE WHERE act_img_no = ?";
	private final String SELECT_BY_ACTIVITY_ID_SQL = "SELECT * FROM ACTIVITY_IMAGE WHERE act_no = ?";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	
	@Override
	public ActivityImageVO insert(ActivityImageVO actImageVO) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, GET_KEY)) {
			ps.setString(1, null);  //AI
			ps.setInt(2, actImageVO.getActId());
			ps.setBytes(3, actImageVO.getActImg());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys(); //綁定主鍵值，這樣撈回來才有正確的Id
			if (rs.next()) {
				actImageVO.setActImgId(rs.getInt(1));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return actImageVO;
	}

	@Override
	public void update(ActivityImageVO actImageVO) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1,actImageVO.getActId());
			ps.setBytes(2,actImageVO.getActImg());
			ps.setInt(3,actImageVO.getActImgId());
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ActivityImageVO findById(Integer actImageId) {
		ActivityImageVO actImageVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {
			ps.setInt(1, actImageId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actImageVO = new ActivityImageVO();
				actImageVO.setActImgId(rs.getInt(1));
				actImageVO.setActId(rs.getInt(2));
				actImageVO.setActImg(rs.getBytes(3));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return actImageVO;
	}

	@Override
	public List<ActivityImageVO> findByActId(Integer actId) {
		List<ActivityImageVO> list = new ArrayList<>();
		ActivityImageVO actImageVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ACTIVITY_ID_SQL)) {
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				actImageVO = new ActivityImageVO();
				actImageVO.setActImgId(rs.getInt(1));
				actImageVO.setActId(rs.getInt(2));
				actImageVO.setActImg(rs.getBytes(3));
				list.add(actImageVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<ActivityImageVO> getAll() {
		List<ActivityImageVO> list = new ArrayList<>();
		ActivityImageVO actImageVO = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)) {
			
			rs = ps.executeQuery();
			while (rs.next()) {
				actImageVO = new ActivityImageVO();
				actImageVO.setActImgId(rs.getInt(1));
				actImageVO.setActId(rs.getInt(2));
				actImageVO.setActImg(rs.getBytes(3));
				list.add(actImageVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return list;
	}
	public static void main(String[] args) throws IOException {
		ActivityImageJDBCDAO dao = new ActivityImageJDBCDAO();
//		ActivityImageVO vo = new ActivityImageVO();
		
//		FileInputStream fis = new FileInputStream("WebContent/images/activity/whale3.jpg");
//		byte[] array = new byte[fis.available()];
//		fis.read(array);		
//		vo.setActImgId(4);
//		vo.setActId(2);
//		vo.setActImg(array);
//		dao.insert(vo);
//		fis.close();
//		dao.update(vo);//注意FK的問題
//		List<ActivityImageVO> list = dao.findByActId(1);
//		ActivityImageVO vo =dao.findById(2);
		List<ActivityImageVO> list = dao.getAll();
		for(ActivityImageVO vo : list)
		System.out.println(vo);
	}
}
