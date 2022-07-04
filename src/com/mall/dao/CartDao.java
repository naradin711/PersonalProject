package com.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mall.dto.CartDto;

public class CartDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static CartDao instance;
	public static CartDao getInstance () {
		if (instance == null) {
			instance = new CartDao();
		}
		return instance;
	}
	private CartDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	-- 1. 글 출력 (int startRow, int endRow)
	public ArrayList<CartDto> listCart (int startRow, int endRow){
		ArrayList<CartDto> dto = new ArrayList<CartDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (select CT.* from CART CT, CUSTOMER_SHOP C " + 
				"                 WHERE CT.CID = C.CID ORDER BY CT.CARTID DESC)A ) " + 
				"     WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cartid 		= rs.getInt("cartid");
				String cid 		= rs.getString("cid");
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice");
			     
			    dto.add(new CartDto(cartid, cid, pid, pname, pphoto, pprice));   
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
//	            
//	-- 2. 글 갯수 세기
//	SELECT COUNT(*)CNT FROM Cart_SHOP;
	public int getCartCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM Cart";
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
//	INSERT INTO Cart_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
//	        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
//	VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', NULL,'title220702','content23', 
//	        'NOIMG.JPG', '192.168.10.151', FBSHOP_SEQ.CURRVAL, 0, 0, '111');
	public int insertCart(CartDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CART (CARTid, cID, PID, pname, pphoto, pprice) " + 
				"VALUES (CART_SEQ.NEXTVAL, ? , ? , ? , ? , ? ) "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCid()); 
			pstmt.setInt   (2, dto.getPid());
			pstmt.setString(3, dto.getPname());
			pstmt.setString(4, dto.getPphoto());
			pstmt.setInt   (5, dto.getPprice());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "장바구니 추가 성공":"장바구니 추가 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " insert Cart Dao error " );
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
	
	
//	-- 4. FBId로 글 dto보기 (글쓴이 이름 추가)
//	SELECT F.* from Cart_SHOP F, CUSTOMER_SHOP C 
//	           WHERE F.CID = C.CID AND FBID=1;
	public CartDto CartView(String cid){
		CartDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "select CT.* from CART CT, CUSTOMER_SHOP C " + 
				"                 WHERE CT.CID = C.CID  " + 
				"                 AND CT.CID = ? " + 
				"                 ORDER BY CT.CARTID DESC  ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cartid 		= rs.getInt("cartid");
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice");
				dto = new CartDto(cartid, cid, pid, pname, pphoto, pprice);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "개인 장바구니 보기");
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
//	-- 5. FBId로 내가 산 물품 불러오기 
//	SELECT F.* from Cart_SHOP F, CUSTOMER_SHOP C 
//	           WHERE F.CID = C.CID AND FBID=1;     
	public ArrayList<CartDto> listMyCart (String cid, int startRow, int endRow){
		ArrayList<CartDto> dto = new ArrayList<CartDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (select CT.* from CART CT, CUSTOMER_SHOP C " + 
				"                 WHERE CT.CID = C.CID AND CT.CID = ? ORDER BY CT.CARTID DESC)A ) " + 
				"     WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cartid 		= rs.getInt("cartid");
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice");
			     
			    dto.add(new CartDto(cartid, cid, pid, pname, pphoto, pprice));   
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
//	-- (7) 글 삭제하기(항목별 삭제)
//	COMMIT;
//	DELETE FROM Cart_SHOP WHERE FBID=3 AND FBPW = '111';
//	ROLLBACK;                      
 	public int DeleteCart (int cartid) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = " DELETE FROM CART WHERE CARTID = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt (1, cartid);
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "장바구니 개별 삭제 성공" : "장바구니 개별 삭제 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"Cart Delete Error");
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
// 	-- (7) 글 삭제하기(항목별 삭제)
//	COMMIT;
//	DELETE FROM Cart_SHOP WHERE FBID=3 AND FBPW = '111';
//	ROLLBACK;                      
 	public int DeleteCartAll (String cid) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = " DELETE FROM CART WHERE CID = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString (1, cid);
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "장바구니 전체 삭제 성공" : "장바구니 전체 삭제 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"CartAll Delete Error");
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
