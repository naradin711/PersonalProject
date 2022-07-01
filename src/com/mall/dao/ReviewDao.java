package com.mall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mall.dto.ReviewDto;

public class ReviewDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static ReviewDao instance;
	public static ReviewDao getInstance () {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	private ReviewDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	-- 1. 글 출력 (int startRow, int endRow)
 
	public ArrayList<ReviewDto> listReview (int pid, int startRow, int endRow){
		ArrayList<ReviewDto> Reviews = new ArrayList<ReviewDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				"            (SELECT ROWNUM RN, A.* FROM " + 
				"            (select R.*  from REVIEW R, CUSTOMER_SHOP C, product P " + 
				"            WHERE R.CID = C.CID AND R.PID = P.PID AND R.PID = ? " + 
				"            ORDER BY rbrdate DESC) A ) " + 
				"            WHERE RN BETWEEN ? AND ?   ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rbid 		 = rs.getInt("rbid");
				String cid 		 = rs.getString("cid");
				String rbtitle 	 = rs.getString("rbtitle");      
			    String rbcontent = rs.getString("rbcontent");    
			    String rbphoto 	 = rs.getString("rbphoto");
			    Date rbrdate	 = rs.getDate("rbrdate");
			    String rbip 	 = rs.getString("rbip"); 
			    String rbpw 	 = rs.getString("rbpw");	
			     
			    Reviews.add(new ReviewDto(rbid, cid, pid, rbtitle, rbcontent, rbphoto, rbrdate, rbip, rbpw) );   
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
		
		return Reviews;
	}
//	            
//	-- 2. 글 갯수 세기
//	SELECT COUNT(*)CNT FROM Review ;
	public int getReviewCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM REVIEW ";
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
//	INSERT INTO Review_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
//	        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
//	VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', NULL,'title220702','content23', 
//	        'NOIMG.JPG', '192.168.10.151', FBSHOP_SEQ.CURRVAL, 0, 0, '111');
	public int insertReview(ReviewDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW (Rbid, cID, pid, Rbtitle, Rbcontent, Rbphoto, Rbip, Rbpw)\r\n" + 
				"VALUES (REVIEW_SEQ.NEXTVAL, ? , ? , ? , ? ,  " + 
				"        ? , ? , ? )  "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCid());
			pstmt.setInt   (2, dto.getPid());
			pstmt.setString(3, dto.getRbtitle());
			pstmt.setString(4, dto.getRbcontent());
			pstmt.setString(5, dto.getRbphoto());
			pstmt.setString(6, dto.getRbip());
			pstmt.setString(7, dto.getRbpw());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "리뷰 작성 성공":"리뷰 작성 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert Review error");
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
//	SELECT F.* from Review_SHOP F, CUSTOMER_SHOP C 
//	           WHERE F.CID = C.CID AND FBID=1;
	public ReviewDto ReviewView(int rbid){
		ReviewDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT R.* from REVIEW R, CUSTOMER_SHOP C, product P " + 
				"           WHERE R.CID = C.CID AND R.PID = P.PID AND RBID= ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt (1, rbid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String cid 		 = rs.getString("cid");
				int pid	 		 = rs.getInt("pid");
				String rbtitle 	 = rs.getString("rbtitle");      
			    String rbcontent = rs.getString("rbcontent");    
			    String rbphoto 	 = rs.getString("rbphoto");
			    Date rbrdate	 = rs.getDate("rbrdate");
			    String rbip 	 = rs.getString("rbip"); 
			    String rbpw 	 = rs.getString("rbpw");
			    
				dto = new ReviewDto(rbid, cid, pid, rbtitle, rbcontent, rbphoto, rbrdate, rbip, rbpw);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "리뷰 보기");
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
//	-- 6. 글 수정 (FBID, fBtitle, FBContent, FBPHOTO,  FIp)
//	UPDATE Review_SHOP SET FBTITLE = '바뀐제목3',
//	                            FBCONTENT = '바뀐 본문',
//	                            fBPHOTO = 'NOIMG.JPG',
//	                            FBIP = '111.168.151.11'
//	                      WHERE FBID = 3; 
	public int ModifyReview (ReviewDto Review) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null; 
		String sql = "UPDATE SET RBTITLE 	= ? , " + 
				"                RBCONTENT 	= ? , " + 
				"                RBPHOTO 	= ? , " + 
				"                RBIP 		= ? " + 
				"                WHERE RBID = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, Review.getRbtitle());
			pstmt.setString (2, Review.getRbcontent());
			pstmt.setString (3, Review.getRbphoto());
			pstmt.setString (4, Review.getRbip());
			pstmt.setInt 	(5, Review.getRbid());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "리뷰 수정 성공" : "리뷰 수정 실패");
		
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Review Modify Error");
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
	
//	                      
//	-- (7) 글 삭제하기(FBID, FBPW로 삭제하기)
//	COMMIT;
//	DELETE FROM Review_SHOP WHERE FBID=3 AND FBPW = '111';
//	ROLLBACK;                      
 	public int DeleteReview (int rbid, String rbpw) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = " DELETE FROM FROM REVIEW WHERE RBID= ? AND RBPW = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt (1, rbid);
		pstmt.setString (2, rbpw);
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "리뷰 삭제 성공" : "리뷰 삭제 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"Review Delete Error");
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
