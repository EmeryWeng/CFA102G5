package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class TestServlet extends HttpServlet {
	private Connection con = null;
	@Override
	public void destroy() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void init() {
		try {
			DataSource ds = (DataSource) new InitialContext()
					.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		}catch(NamingException ex) {
			ex.printStackTrace();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	private static final long serialVersionUID = 1L;
	private final String select = "SELECT * FROM ACTIVITY_CLASS";
    private final String sql = "INSERT INTO ACTIVITY_CLASS VALUES(?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain;charset=UTF-8");
		String act_name = req.getParameter("name");
		String act_state = req.getParameter("state");
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, null);
			ps.setString(2,act_name);
			ps.setBoolean(3,Boolean.parseBoolean(act_state));
			ps.executeUpdate();
			System.out.println("成功了");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
