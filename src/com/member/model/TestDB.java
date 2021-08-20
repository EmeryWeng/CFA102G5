package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.JDBCUtil;

public class TestDB {
	private static final String sql = "INSERT INTO ACTIVITY_CLASS VALUES(?,?,?)";
	
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(JDBCUtil.url,JDBCUtil.username,JDBCUtil.password);
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, null);
			ps.setString(2,"水上活動");
			ps.setBoolean(3,true);
			ps.executeUpdate();
			System.out.println("成功!");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
