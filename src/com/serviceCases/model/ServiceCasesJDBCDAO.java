package com.serviceCases.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.JDBCUtil;

public class ServiceCasesJDBCDAO implements I_ServiceCasesDAO {
	// 新增案件
	private final String INSERT = "INSERT INTO SERVICE_CASES VALUES (?, ?, ?, ?, ?, ?)";
	// 案件回覆
	private final String UPDATE = "UPDATE SERVICE_CASES set case_reply=?, case_state=? where case_no = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ServiceCasesVO serviceCasesVO) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			// 1, AutoIncrement
			pstmt.setString(1, null);
			pstmt.setInt(2, serviceCasesVO.getMem_no());
			pstmt.setString(3, serviceCasesVO.getCase_title());
			pstmt.setString(4, serviceCasesVO.getCase_des());
			pstmt.setString(5, serviceCasesVO.getCase_reply());
			pstmt.setInt(6, serviceCasesVO.getCase_state());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				serviceCasesVO.setCase_no(rs.getInt(1));
			}

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
	public void update(ServiceCasesVO serviceCasesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.username, JDBCUtil.password);
			pstmt = con.prepareStatement(UPDATE);		
			pstmt.setString(1, serviceCasesVO.getCase_reply());
			pstmt.setInt(2, serviceCasesVO.getCase_state());
			pstmt.setInt(3,serviceCasesVO.getCase_no());
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

	public static void main(String[] args) throws IOException {

		// 新增訊息
//		ServiceCasesJDBCDAO dao = new ServiceCasesJDBCDAO();
//		ServiceCasesVO vo = new ServiceCasesVO();
//		vo.setMem_no(2);
//		vo.setCase_title("我有一些房間問題");
//		vo.setCase_des("我的床有點大");
//		vo.setCase_state(1);
//		dao.insert(vo);
//		System.out.println(vo);
//		System.out.println("新增案件成功");


		// 案件回覆
//		ServiceCasesJDBCDAO dao = new ServiceCasesJDBCDAO();
//		ServiceCasesVO vo = new ServiceCasesVO();
//		vo.setCase_no(1);
//		vo.setCase_reply("找麻煩嗎?");
//		vo.setCase_state(2);
//		dao.update(vo);
//		System.out.println("案件回覆成功");
	}

}
