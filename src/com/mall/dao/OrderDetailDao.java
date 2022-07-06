package com.mall.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
 
import com.mall.dto.OrderDetailDto;

public class OrderDetailDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static OrderDetailDao instance;
	public static OrderDetailDao getInstance () {
		if (instance == null) {
			instance = new OrderDetailDao();
		}
		return instance;
	}
	private OrderDetailDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//	-- 1. 글 출력 (int startRow, int endRow)
 
	public ArrayList<OrderDetailDto> listOrderDetail (int odid, int startRow, int endRow){
		ArrayList<OrderDetailDto> OrderLists = new ArrayList<OrderDetailDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (select ODD.* from ORDERDETAIL ODD, CUSTOMER_SHOP C " + 
				"                 WHERE ODD.CID = C.CID AND ODD.odid = ? " + 
				"                 ORDER BY ODDID DESC)A ) " + 
				"     WHERE RN BETWEEN ? AND ?  ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int oddid 		= rs.getInt("oddid");
				int pid 		= rs.getInt("pid");
				String pname    = rs.getString("pname");
				int pprice 		= rs.getInt("pprice");
				
			    OrderLists.add(new OrderDetailDto(oddid, odid, pid, pname, pprice) );   
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+ " 구매 번호별 구매 내역 보기 DAO 에러 11 ");
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage() + "구매 내역 보기 DAO 에러 22 ");
			} 
		}
		
		return OrderLists;
	}
	// -- 1. 1. 마이페이지 글 출력 (int startRow, int endRow)
	 
	public ArrayList<OrderDetailDto> listMyOrder (String cid, int startRow, int endRow){
		ArrayList<OrderDetailDto> OrderLists = new ArrayList<OrderDetailDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (select ODD.* from ORDERDETAIL ODD, CUSTOMER_SHOP C " + 
				"                 WHERE ODD.CID = C.CID AND ODD.cid = ? " + 
				"                 ORDER BY ODDID DESC)A ) " + 
				"     WHERE RN BETWEEN ? AND ?  ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int oddid 		= rs.getInt("oddid");
				int pid 		= rs.getInt("pid");
				String pname    = rs.getString("pname");
				int pprice 		= rs.getInt("pprice");
				
			    OrderLists.add(new OrderDetailDto(oddid, cid, pid, pname, pprice) );   
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "내 구매 내역 보기 DAO 에러 11 ");
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage() + "구매 내역 보기 DAO 에러 22 ");
			} 
		}
		
		return OrderLists;
	}
//	            
//	-- 2. 글 갯수 세기
//	SELECT COUNT(*)CNT FROM ORDERDETAIL;
	public int getOrderListCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM ORDERDETAIL ";
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
//	-- 3. 주문 상세 추가하기 
	public int insertOrderDetail(String cid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO ORDERDETAIL (ODDID, odid, cid, pid, pname, pprice) " + 
				"select ORDERDETAIL_SEQ.nextval, ORDERLIST_SEQ.currval , cid, pid, pname, pprice  " + 
				"from CART where cid= ?  " ; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid); 
			result = pstmt.executeUpdate();
			System.out.println(result==FAIL? " 주문 상세 작성 실패 ":" 주문 상세 작성 성공 ");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert ORDERDETAIL error DAO");
		}	finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	
	
// -- 4. 완료된 주문 목록에서 삭제하기 	
		public int DeleteOrderDetail(String cid) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = " delete orderdetail where cid = ? " ; 
					 
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cid);
				result = pstmt.executeUpdate();
				System.out.println(result==FAIL? "주문 상세 삭제 실패":"주문 상세 삭제 성공");
				
			} catch (Exception e) {
				System.out.println(e.getMessage() + "insert ORDERDETAIL error DAO");
			}	finally {
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
