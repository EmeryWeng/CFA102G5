package com.activityClass.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityClassDAO implements I_ActivityClassDAO{
	private static DataSource ds;
	
	private final String[] GET_KEY = {"act_class_no"};
	private final String SELECT_All_SQL = "SELECT * FROM ACTIVITY_CLASS";
	private final String INSERT_SQL = "INSERT INTO ACTIVITY_CLASS VALUES(?,?,?)";
	private final String UPDATE_SQL = "UPDATE ACTIVITY_CLASS SET act_class_name = ?,act_class_state = ? WHERE act_class_no = ?";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM ACTIVITY_CLASS WHERE act_class_no = ?";
	
	static {	
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException ex) {
			ex.printStackTrace();
		}	
	}
	
	
	@Override
	public ActivityClassVO insert(ActivityClassVO actClassVO) {
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_SQL,GET_KEY)){
				
			ps.setString(1, null); //AI
			ps.setString(2, actClassVO.getActClassName());
			ps.setBoolean(3, actClassVO.getActClassState());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				actClassVO.setActClassId(rs.getInt(1));
			}
			
		} catch(SQLException ex) {
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
		return actClassVO;
	}

	@Override
	public void update(ActivityClassVO actClassVO) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)){
				ps.setString(1, actClassVO.getActClassName());
				ps.setBoolean(2, actClassVO.getActClassState());
				ps.setInt(3,actClassVO.getActClassId());
				ps.executeUpdate();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
	}

	@Override
	public ActivityClassVO findById(Integer actClassId) {
		ActivityClassVO actClassVO = null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)){
				
				rs = ps.executeQuery();
				ps.setInt(1,actClassId);
				
				while(rs.next()) {
					actClassVO = new ActivityClassVO();
					actClassVO.setActClassId(rs.getInt(1));
					actClassVO.setActClassName(rs.getString(2));
					actClassVO.setActClassState(rs.getBoolean(3));
				}
						
			} catch(SQLException ex) {
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
		return actClassVO;
	}

	@Override
	public List<ActivityClassVO> getAll() {
		List<ActivityClassVO> list = new ArrayList<>();
		ActivityClassVO actClassVO = null;
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_All_SQL)){
				
				rs = ps.executeQuery();
				while(rs.next()) {
					actClassVO = new ActivityClassVO();
					actClassVO.setActClassId(rs.getInt(1));
					actClassVO.setActClassName(rs.getString(2));
					actClassVO.setActClassState(rs.getBoolean(3));
					list.add(actClassVO);
				}						
			} catch(SQLException ex) {
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
	
}
