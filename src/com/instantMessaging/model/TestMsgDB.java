package com.instantMessaging.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.JDBCUtil;

public class TestMsgDB {
private static final String sql = "INSERT INTO INSTANT_MESSAGING VALUES(?,?,?,?,?,?)";
	
	public static void main(String[] args) {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(JDBCUtil.url,JDBCUtil.username,JDBCUtil.password);
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, null);
			ps.setString(2,"1");
			ps.setBoolean(3,true);
			ps.setString(4, "helloworld");
			ps.setString(5, null);
			ps.setDate(6, java.sql.Date.valueOf("2002-01-01"));;
			ps.executeUpdate();
			System.out.println("成功!");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}

