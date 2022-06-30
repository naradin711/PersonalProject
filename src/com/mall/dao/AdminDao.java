package com.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mall.dto.AdminDto;

public class AdminDao {
	public static final int SUCCESS = 1; // 성공
	public static final int FAIL = 0;	// 실패
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL = 0;
	private DataSource ds;
	
	private static AdminDao instance;
	public static AdminDao getInstance () {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	private AdminDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 1. 관리자 로그인
	public int AdminloginCheck(String aid, String apw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT * FROM admin WHERE AID= ? AND APW= ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			pstmt.setString(2, apw);
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
	// 2. 관리자 상세보기
	public AdminDto AdminView(String aid){
		AdminDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM ADMIN WHERE AID= ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 String apw 	 = rs.getString("apw");      
				 String aname 	 = rs.getString("aname");
				 dto = new com.mall.dto.AdminDto(aid, apw, aname);
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
}
