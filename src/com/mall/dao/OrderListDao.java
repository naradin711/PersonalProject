package com.mall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
 
import com.mall.dto.OrderListDto;

public class OrderListDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static OrderListDao instance;
	public static OrderListDao getInstance () {
		if (instance == null) {
			instance = new OrderListDao();
		}
		return instance;
	}
	private OrderListDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//	-- 1. 글 출력 (int startRow, int endRow)
//	SELECT * FROM
//	    (SELECT ROWNUM RN, A.* FROM
//	    (select F.* from OrderList_SHOP F, CUSTOMER_SHOP C 
//	                 WHERE F.CID = C.CID
//	                ORDER BY FBGROUP DESC, FBSTEP )A )
//	     WHERE RN BETWEEN 5 AND 11; 
	public ArrayList<OrderListDto> listOrderList (int startRow, int endRow){
		ArrayList<OrderListDto> list = new ArrayList<OrderListDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select OD.* from ORDERLIST OD, CUSTOMER_SHOP C " + 
				"  WHERE OD.CID = C.CID ORDER BY ODDATE DESC )A ) " + 
				" WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int odid 		= rs.getInt("odid");
				String cid 		= rs.getString("cid");
				String odtitle = rs.getString("odtitle");
				String odaddress = rs.getString("odaddress");
				int odprice 		= rs.getInt("odprice");
				String odcall 		= rs.getString("odcall");
				Date oddate			= rs.getDate("oddate");
				
			    list.add(new OrderListDto(odid, cid, odtitle, odaddress, odprice, odcall, oddate) );   
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
		
		return list;
	}
//	            
//	-- 2. 글 갯수 세기
//	SELECT COUNT(*)CNT FROM OrderList_SHOP;
	public int getOrderListCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM ORDERLIST ";
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
//	-- 3. 글 작성하기. (고객 원글)
//	INSERT INTO OrderList_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
//	        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
//	VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', NULL,'title220702','content23', 
//	        'NOIMG.JPG', '192.168.10.151', FBSHOP_SEQ.CURRVAL, 0, 0, '111');
	public int insertOrderList (OrderListDto OrderList) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO ORDERLIST (ODID, cID, ODTITle, ODaddress, odprice) " + 
				"VALUES (ORDERLIST_SEQ.NEXTVAL, ? , ? , ? , ?) "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, OrderList.getCid());
			pstmt.setString(2, OrderList.getOdtitle());
			pstmt.setString(3, OrderList.getOdaddress());
			pstmt.setInt(4, OrderList.getOdprice());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "주문 작성 성공":"주문 작성 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert OrderList error");
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
	
	// 4. 1. 주문 상세 삭제하기
	public int DeleteOrderDetail(String cid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Delete ORDERDETAIL where cid = ? " ; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			result = pstmt.executeUpdate();
			System.out.println(result == FAIL ? " 주문 상세 삭제 실패 " : " 주문 상세 삭제 성공 ");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "DELETE ORDERDETAIL error DAO");
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
    
		 
	
	// 4. 주문 처리 하기
	public int OrderCall (int odid) { 
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null; 
		String sql = "UPDATE ORDERLIST SET ODtitle = CONCAT( ODtitle, ' - 처리 완료.') , " + 
				"                     	   ODCALL  = 'Y' " + 
				"                 WHERE ODID = ? " ;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, odid);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? " 주문 처리 성공 " : " 주문 처리 실패 "); 
		} catch (Exception e) {
			System.out.println(e.getMessage()+" Order Call Error");
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
