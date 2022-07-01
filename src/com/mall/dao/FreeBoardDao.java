package com.mall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource; 

import com.mall.dto.FreeBoardDto;

public class FreeBoardDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static FreeBoardDao instance;
	public static FreeBoardDao getInstance () {
		if (instance == null) {
			instance = new FreeBoardDao();
		}
		return instance;
	}
	private FreeBoardDao () {
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
//	    (select F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C 
//	                 WHERE F.CID = C.CID
//	                ORDER BY FBGROUP DESC, FBSTEP )A )
//	     WHERE RN BETWEEN 5 AND 11; 
	public ArrayList<FreeBoardDto> listFreeBoard (int startRow, int endRow){
		ArrayList<FreeBoardDto> freeboards = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C  " + 
				" WHERE F.CID = C.CID " + 
				" ORDER BY FBGROUP DESC, FBSTEP )A ) " + 
				" WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fbid 		= rs.getInt("fbid");
				String cid 		= rs.getString("cid");
				String aname	= rs.getString("aname");
				String fbtitle 	= rs.getString("fbtitle");      
			    String fbcontent = rs.getString("fbcontent");    
			    String fbphoto 	 = rs.getString("fbphoto");
			    String fbip 	 = rs.getString("fbip");
			    Date fbrdate 	 = rs.getDate("fbrdate");
			    int fbgroup 	 = rs.getInt("fbgroup");
			    int fbstep 		= rs.getInt("fbstep");
			    int fbindent 	= rs.getInt("fbindent");
			    String fbpw 	= rs.getString("fbpw");
			     
			    freeboards.add(new FreeBoardDto(fbid, cid, aname, fbtitle, fbcontent, fbphoto, fbrdate, fbip, fbgroup, fbstep, fbindent, fbpw) );   
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
		
		return freeboards;
	}
//	            
//	-- 2. 글 갯수 세기
//	SELECT COUNT(*)CNT FROM FREEBOARD_SHOP;
	public int getFreeBoardCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM FREEBOARD_SHOP";
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
//	INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
//	        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
//	VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', NULL,'title220702','content23', 
//	        'NOIMG.JPG', '192.168.10.151', FBSHOP_SEQ.CURRVAL, 0, 0, '111');
	public int insertFreeBoard(FreeBoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent,  " + 
				" fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw) " + 
				" VALUES (FBSHOP_SEQ.NEXTVAL, ? , NULL, ? , ? ,  " + 
				" ? , ? , FBSHOP_SEQ.CURRVAL, 0, 0, ? ) "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCid());
			System.out.println(1);
			pstmt.setString(2, dto.getFbtitle());
			System.out.println(2);
			pstmt.setString(3, dto.getFbcontent());
			System.out.println(3);
			pstmt.setString(4, dto.getFbphoto());
			System.out.println(4);
			pstmt.setString(5, dto.getFbip());
			System.out.println(5);
			pstmt.setString(6, dto.getFbpw());
			System.out.println(6);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글 작성 성공":"글 작성 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert freeboard error");
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
	
	
//	-- 4. FBId로 글 dto보기 (글쓴이 이름 추가)
//	SELECT F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C 
//	           WHERE F.CID = C.CID AND FBID=1;
	public FreeBoardDto FreeBoardView(int fbid){
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C  " + 
				"   WHERE F.CID = C.CID AND FBID= ?     ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt (1, fbid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String cid 		= rs.getString("cid");
				String aname	= rs.getString("aname");
				String fbtitle 	= rs.getString("fbtitle");      
			    String fbcontent = rs.getString("fbcontent");    
			    String fbphoto 	 = rs.getString("fbphoto");
			    Date fbrdate 	 = rs.getDate("fbrdate");
			    String fbip 		= rs.getString("fbip");
			    int fbgroup 		= rs.getInt("fbgroup");
			    int fbstep 		= rs.getInt("fbstep");
			    int fbindent 	= rs.getInt("fbindent");
			    String fbpw 	= rs.getString("fbpw");
				dto = new FreeBoardDto(fbid, cid, aname, fbtitle, fbcontent, fbphoto, fbrdate, fbip, fbgroup, fbstep, fbindent, fbpw);
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
//	-- 6. 글 수정 (FBID, fBtitle, FBContent, FBPHOTO,  FIp)
//	UPDATE FREEBOARD_SHOP SET FBTITLE = '바뀐제목3',
//	                            FBCONTENT = '바뀐 본문',
//	                            fBPHOTO = 'NOIMG.JPG',
//	                            FBIP = '111.168.151.11'
//	                      WHERE FBID = 3; 
	public int ModifyFreeBoard (FreeBoardDto freeboard) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null; 
		String sql = "UPDATE FREEBOARD_SHOP SET FBTITLE = ? , " + 
				" 								FBCONTENT =  ? , " + 
				"								FBPHOTO =  ? , " + 
				"								FBIP =  ? " + 
				"								WHERE FBID = ? AND FBPW = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, freeboard.getFbtitle());
			System.out.println(11);
			pstmt.setString (2, freeboard.getFbcontent());
			System.out.println(22);
			pstmt.setString (3, freeboard.getFbphoto());
			System.out.println(33);
			pstmt.setString (4, freeboard.getFbip());
			System.out.println(44);
			pstmt.setInt 	(5, freeboard.getFbid()); 
			System.out.println(55);
			pstmt.setString (6, freeboard.getFbpw());
			System.out.println(66);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글 수정 성공" : "글 수정 실패");
		
		} catch (Exception e) {
			System.out.println(e.getMessage()+"freeBoard Modify Error");
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
//	DELETE FROM FREEBOARD_SHOP WHERE FBID=3 AND FBPW = '111';
//	ROLLBACK;                      
 	public int DeleteFreeBoard (int fbid, String fbpw) {
	int result = FAIL;
	Connection 		   conn = null;
	PreparedStatement pstmt = null; 
	String sql = " DELETE FROM FREEBOARD_SHOP WHERE FBID = ? " + 
			"								    AND FBPW = ? ";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt (1, fbid);
		pstmt.setString (2, fbpw);
		result = pstmt.executeUpdate();
		System.out.println(result==SUCCESS? "글 삭제 성공" : "글 삭제 실패");
	
	} catch (Exception e) {
		System.out.println(e.getMessage()+"FreeBoard Delete Error");
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
//	-- (9) 답변글 쓰기 (관리자 ONLY)
//
//	INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
//	        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
//	VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', '관리자','답) title220702','답) content23', 
//	        'NOIMG.JPG', '192.168.10.151', 3, 1, 1, '111');
 	
 	public int ReplyFreeBoard(FreeBoardDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent, " + 
				" fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw) " + 
				" VALUES (FBSHOP_SEQ.NEXTVAL, ? , ? , ? , ? ,  " + 
				" ? , ? , ? , 1, 1, ? ) "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCid());
			pstmt.setString(2, dto.getAname());
			pstmt.setString(3, dto.getFbtitle());
			pstmt.setString(4, dto.getFbcontent());
			pstmt.setString(5, dto.getFbphoto());
			pstmt.setString(6, dto.getFbip());
			pstmt.setInt	(7, dto.getFbgroup());
			pstmt.setString	(8, dto.getFbpw());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "답글 작성 성공":"답글 작성 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert freeboard reply error");
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage() + "insert freeboard reply error 22"  );
			}
		}
		return result;
	}
}
