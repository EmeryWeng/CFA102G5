package com.member.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.util.JDBCUtil;

public class test {
	
	public static void main(String[] args) {

		I_MemberClassDAO memberClassJDBCDAO = new MemberClassJDBCDAO();
//		MemberClassVO memberClassVO = new MemberClassVO();		
//email搜尋會員		
		MemberClassVO memberClassVO = memberClassJDBCDAO.getOneBymail("22222@gmail.com");
		System.out.println(memberClassVO);
//電話搜尋會員		
//  	MemberClassVO memberClassVO = memberClassJDBCDAO.getOneByMobile("4");
//		System.out.println(memberClassVO);
//會員單純修改密碼		
//		memberClassVO.setMem_no(3);
//		memberClassVO.setMem_password("66666");
//		memberClassJDBCDAO .updatePassword(memberClassVO);
//		System.out.println(memberClassVO);
//會員狀態查詢會員
//		List<MemberClassVO> All = memberClassJDBCDAO.getAllByState(true);
//會員性別查詢會員		
//		List<MemberClassVO> All = memberClassJDBCDAO.getAllBySex(2);
//		for(MemberClassVO M : All)
//			System.out.println(M);
//搜尋全部會員
//		List<MemberClassVO> All = memberClassJDBCDAO.getAll();
//		for(MemberClassVO M : All)
//		System.out.println(M);
//更新會員		
//		memberClassVO.setMem_name("1");
//		memberClassVO.setMem_sex(2);
//		memberClassVO.setMem_mail("1");
//		memberClassVO.setMem_password("1");
//		memberClassVO.setMem_mobile("1");
//		memberClassVO.setMem_img(null);
//		memberClassVO.setMem_add("3333");
//		memberClassVO.setMem_no(11);
//		
//		memberClassJDBCDAO.updateMember(memberClassVO);
		
//新增會員
		memberClassVO.setMem_name("45234534");
		memberClassVO.setMem_sex(1);
		memberClassVO.setMem_mail("QQQQ22@gmail.com");
		memberClassVO.setMem_password("1222223");
		memberClassVO.setMem_img(null);
		memberClassVO.setMem_mobile("092222233");
		memberClassVO.setMem_state(false);
		memberClassJDBCDAO.addMember(memberClassVO);
		System.out.println(memberClassVO);
//修改會員狀態		
//		memberClassVO.setMem_state(false);
//		memberClassVO.setMem_no(11);
//		memberClassJDBCDAO.updateMemberstate(memberClassVO);
		
		
		
//========================================================================
		
//		I_MemberClassDAO memberClassJDBCDAO = new MemberClassJDBCDAO();
//		List<MemberClassVO> All = memberClassJDBCDAO.getAll();
//		for(MemberClassVO M : All)
//			System.out.println(M);
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("1");
//		int mem_no = sc.nextInt();
//		
//		System.out.println("2");
//		String mem_name = sc.next();
//		
//		System.out.println("3");
//		int mem_sex = sc.nextInt();
//		
//		System.out.println("4");
//		String mem_mail = sc.next();
//		
//		System.out.println("5");
//	    String mem_password = sc.next();
//		
//		System.out.println("6");
//		String mem_mobile = sc.next();
//		
//		System.out.println("7");
//		int mem_img = sc.nextInt();
//		
//		System.out.println("8");
//		String mem_add = sc.next();
//		
//		System.out.println("9");
//		int mem_state = sc.nextInt();
//		
//		sc.close();
	}
	
//	static {
//		try {
//			Class.forName(JDBCUtil.DRIVER);
//		}catch(ClassNotFoundException ce){
//			ce.printStackTrace();
//		}
//	}
//	
//	public static String addMember = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?)";
	

//	public static void main(String[] args) throws IOException {
//		
//		try (Connection con = DriverManager.getConnection(JDBCUtil.url,JDBCUtil.username,JDBCUtil.password);
//				PreparedStatement pstmt = con.prepareStatement(addMember)){
//					pstmt.setInt(1,3);
//					pstmt.setString(2, "劉德華");
//					pstmt.setInt(3,2);
//					pstmt.setString(4,"xxx@yahoo.com.tw");
//					pstmt.setString(5,"qwellldd");
//					pstmt.setString(6,"0912345678");
//					InputStream is = getPictureStream("C:\\Users\\k4670\\Pictures\\Saved Pictures\\andy.jpg");
//					pstmt.setBlob(7,is);
//					pstmt.setString(8,"桃園市中壢區復興路46號8樓");
//					pstmt.setBoolean(9,false);
//					
//					pstmt.executeUpdate();
//				
//				}catch(SQLException se){
//					se.printStackTrace();
//				}				
//			}
	
//	public static InputStream getPictureStream(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		return fis;
//	}
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

	

}
