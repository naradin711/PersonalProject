package com.mall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mall.dto.CustomerDto;

public class CustomerDao {
	public static final int SUCCESS = 1; // 회원가입 성공
	public static final int FAIL = 0;	// 회원가입 실패
	public static final int ID_EXISTENT = 1; // 중복아이디 없음
	public static final int ID_NONEXISTENT = 0;	// 중복아이디 있음
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL = 0;
	private DataSource ds;
	
	private static CustomerDao instance;
	public static CustomerDao getInstance () {
		if (instance == null) {
			instance = new CustomerDao();
		}
		return instance;
	}
	private CustomerDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//  0. 로그인 SELECT * FROM mvcmember WHERE MID='aaa' AND MPW='111';
	public int loginCheck(String cid, String cpw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT * FROM customer_shop WHERE CID = ? and CPW = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setString(2, cpw);
			rs = pstmt.executeQuery();
			if(rs.next()) {   	// 로그인 성공
				result = LOGIN_SUCCESS;
			} else {			// 로그인 실패
				result = LOGIN_FAIL;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
//	--1. ID중복체크
//	SELECT * FROM customer_shop WHERE CID = 'aaa';
 	public int confirmCid(String cid) {
	int result = ID_EXISTENT;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet		  rs	= null;
		String sql = "SELECT * FROM customer_shop WHERE CID =  ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cid);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = ID_EXISTENT;
		} else {
			result = ID_NONEXISTENT;
		}
	} catch (Exception e) {
		System.out.println(e.getMessage() + "cidConfirm error");
	} finally {
		try {
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if (conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
 }
// 1 - 1 . 회원가입 이메일 중복체크
public int confirmCemail(String cemail) {
	int result = ID_EXISTENT;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet		  rs	= null;
		String sql = "SELECT * FROM customer_shop WHERE CEMAIL = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cemail);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = ID_EXISTENT;
		} else {
			result = ID_NONEXISTENT;
		}
	} catch (Exception e) {
		System.out.println(e.getMessage() + "emailConfirm");
	} finally {
		try {
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if (conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
}
//1 - 2 . 회원가입 핸드폰 중복체크
public int confirmCtel(String ctel) {
	int result = ID_EXISTENT;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet		  rs	= null;
		String sql = "SELECT * FROM customer_shop WHERE CTEL = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ctel);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = ID_EXISTENT;
		} else {
			result = ID_NONEXISTENT;
		}
	} catch (Exception e) {
		System.out.println(e.getMessage() + "telConfirm");
	} finally {
		try {
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if (conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
}

//
//	--2. 회원가입        
//	INSERT INTO customer_shop (CID, CPW, CNAME, CEMAIL, CTEL, CADDRESS, CBIRTH)
//	        VALUES ('aaa', '111', '홍길동', 'honghong11@hong.com', '010-9999-9999', 
//	                '서울시 종로구 무악동', '1992-02-19'); 
//
public int JoinCustomer(CustomerDto dto) {
	int result = FAIL;
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = "INSERT INTO customer_shop (CID, CPW, CNAME, CEMAIL, CTEL, CADDRESS, CBIRTH) " + 
			"     VALUES ( ? , ? , ? , ? , ? , ? , ? )";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, dto.getCid());
		pstmt.setString	(2, dto.getCpw());
		pstmt.setString	(3, dto.getCname());
		pstmt.setString	(4, dto.getCemail());
		pstmt.setString	(5, dto.getCtel());
		pstmt.setString	(6, dto.getCaddress());
		pstmt.setDate	(7, dto.getCbirth());
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "회원가입성공":"회원가입실패");
		
	} catch (Exception e) {
		System.out.println(e.getMessage() + "join error");
	} finally {
		try { 
			if (pstmt!=null) pstmt.close();
			if (conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
}
//  -- 2. 1. 회원 수 세보기
public int getCustomerCnt() {
	int totCnt = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet		  rs	= null;
	String sql = "SELECT COUNT(*) CNT FROM CUSTOMER_SHOP";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next();
		totCnt = rs.getInt("CNT");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		try {
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if (conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return totCnt;
}
//	-- 2. 2. 회원 목록 보기 페이징하기
public ArrayList<CustomerDto> listCustomer(int startRow, int endRow){
	ArrayList<CustomerDto> Customers = new ArrayList<CustomerDto>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet			rs  = null;
	String sql = "SELECT * FROM " + 
			"        (SELECT ROWNUM RN, A.* FROM  " + 
			"        (SELECT * FROM CUSTOMER_SHOP ORDER BY CRDATE DESC)A ) " + 
			"         WHERE RN BETWEEN ? AND ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			 String cid 	 = rs.getString("cid");       
			 String cpw 	 = rs.getString("cpw");      
			 String cname 	 = rs.getString("cname");   
			 String cemail	 = rs.getString("cemail");    
			 String ctel   	 = rs.getString("ctel");
			 String caddress = rs.getString("caddress");
			 Date cbirth     = rs.getDate("cbirth");
			 Date crdate     = rs.getDate("crdate");
			 Customers.add(new CustomerDto(cid, cpw, cname, cemail, ctel, caddress, cbirth, crdate));   
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
	return Customers;
}

//	-- 2. 3. 회원정보 상세보기 (cid로 dto 출력)
public CustomerDto CustomerView(String cid){
	CustomerDto dto = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet			rs  = null;
	String sql = "SELECT * FROM CUSTOMER_SHOP WHERE CID = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cid);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			 String cpw 	 = rs.getString("cpw");      
			 String cname 	 = rs.getString("cname");   
			 String cemail	 = rs.getString("cemail");    
			 String ctel   	 = rs.getString("ctel"); 
			 String caddress = rs.getString("caddress"); 
			 Date cbirth     = rs.getDate("cbirth"); 
			 Date crdate     = rs.getDate("crdate");
			 dto = new CustomerDto(cid, cpw, cname, cemail, ctel, caddress, cbirth, crdate);   
			}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
	return dto;
}

//	-- 3. 회원 정보 수정
//
//	update customer_shop set cpw = '111',
//	                        CNAME = '씨길동',
//	                        CEMAIL = 'honghong13@hong.com',
//	                        CTEL = '010-2333-2333',
//	                        CADDRESS = '서울시 종로구 사전동' ,
//	                        CBIRTH = '1993-03-03'
//	                        WHERE CID = 'ccc';
public int ModifyCustomer (CustomerDto customer) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = "update customer_shop SET cpw 	= ? , " + 
			"                         CNAME 	= ? , " + 
			"                         CEMAIL 	= ? , " + 
			"                         CTEL 		= ? , " + 
			"                         CADDRESS 	 = ? , " + 
			" 	                      CBIRTH 	 = ? " + 
			"                         WHERE CID  = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString (1, customer.getCpw());
		pstmt.setString (2, customer.getCname());
		pstmt.setString (3, customer.getCemail());
		pstmt.setString (4, customer.getCtel());
		pstmt.setString (5, customer.getCaddress());
		pstmt.setDate   (6, customer.getCbirth());
		pstmt.setString (7, customer.getCid());
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "고객 정보 수정 성공" : "고객 정보 수정 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"Dao Modify Error");
	} finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
}
//	-- 4. 회원 탈퇴
//
//	DELETE CUSTOMER_SHOP WHERE CID = 'bbb';
public int DeleteCustomer (String cid) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = "DELETE CUSTOMER_SHOP WHERE CID = ?  ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		System.out.println(11);
		pstmt.setString (1, cid);
		System.out.println(22);
		result = pstmt.executeUpdate();
		System.out.println(33);
		System.out.println(result==SUCCESS? "고객 정보 삭제 성공" : "고객 정보 삭제 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"CUSTOMERDao Delete Error");
	} finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	return result;
}
	
}






















