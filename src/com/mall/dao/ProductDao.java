package com.mall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mall.dto.ProductDto;

public class ProductDao {
	public static final int SUCCESS = 1; //  성공
	public static final int FAIL = 0;	//  실패
	private DataSource ds;
	
	private static ProductDao instance;
	public static ProductDao getInstance () {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	private ProductDao () {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

//	-- 0. 상품 목록 출력 (등록 최신순)
	public ArrayList<ProductDto> listProduct (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P ORDER BY prdate DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 0. 1. 상품 갯수 세기
	public int getProductCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT COUNT(*) CNT FROM PRODUCT ";
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
	
//	-- 1. 상품등록        
//	INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
//    VALUES (PRODUCT_SEQ.nextval , 'WHITE TOP' , 'TOP' , 
//            '이것은 하얀색 상의' , 'NOIMG.JPG' , 45000 )
	public int insertProduct(ProductDto product) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice ) " + 
				"        VALUES (PRODUCT_SEQ.nextval , ?  , ? , ? , ? , ? ) "; 
				 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPtype());
			pstmt.setString(3, product.getPcontent());
			pstmt.setString(4, product.getPphoto());
			pstmt.setInt	(5, product.getPprice());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "물품 추가 성공":"물품 추가 실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "insert product error");
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
//
//	-- 2-1. 상품 조회수 올리기
//	UPDATE PRODUCT SET PHIT = PHIT + 1 WHERE PID = 3;
	public int insertPhit(int pid) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FILEBOARD SET PHIT = PHIT+1 WHERE PID= ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt (1, pid);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "조회수 올리기 성공":"조회수 올리기  실패");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + "hitup error");
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
//	-- 2. 상품 상세 조회 (PID로 DTO 출력)
//
//	SELECT * FROM PRODUCT WHERE PID = 2;
	public ProductDto ProductView(int pid){
		insertPhit(pid);
		ProductDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM PRODUCT WHERE PID = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt (1, pid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	 = rs.getString("pphoto");
			    int pprice = rs.getInt("pprice"); 
			    int phit = rs.getInt("phit");  
			    Date prdate = rs.getDate("prdate");
				dto = new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate);
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
//	-- 2. 1. 상품 상세 조회 관리자용 (PID로 DTO 출력)
//
//		SELECT * FROM PRODUCT WHERE PID = 2;
		public ProductDto AdminProductView(int pid){
			ProductDto dto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet			rs  = null;
			String sql = "SELECT * FROM PRODUCT WHERE PID = ? ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt (1, pid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String pname	= rs.getString("pname");
					String ptype 	= rs.getString("ptype");      
				    String pcontent = rs.getString("pcontent");    
				    String pphoto 	 = rs.getString("pphoto");
				    int pprice = rs.getInt("pprice"); 
				    int phit = rs.getInt("phit");  
				    Date prdate = rs.getDate("prdate");
					dto = new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate);
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
	
//	-- 3-1 . 상품 목록 출력 이름 검색
//	Select * from PRODUCT where PNAME like '%' || upper( 'WH' ) || '%';
	public ArrayList<ProductDto> ProductSearch (String search,int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM\r\n" + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P WHERE PNAME like '%' || upper( ? ) || '%'  " + 
				"                            ORDER BY prdate DESC )A ) " + 
				"                            WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search.trim());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-2 . 상품 목록 출력 가격 높은순
//	SELECT * FROM PRODUCT ORDER BY PPRICE DESC;
	public ArrayList<ProductDto> ProductPriceUp (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (SELECT P.* from PRODUCT P ORDER BY PPRICE DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-3 . 상품 목록 출력 가격 낮은순
//	SELECT * FROM PRODUCT ORDER BY PPRICE ;
	public ArrayList<ProductDto> ProductPriceDown (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (SELECT P.* from PRODUCT P ORDER BY PPRICE )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-5 . 상품 목록 인기순
//	SELECT * FROM PRODUCT ORDER BY PHIT DESC;
	public ArrayList<ProductDto> BestSeller(int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (SELECT P.* from PRODUCT P ORDER BY PHIT DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-6-1 . 상품 목록 종류별 드레스 (인기순)
//	SELECT * FROM PRODUCT WHERE PTYPE='DRESS' ORDER BY PHIT DESC;
	public ArrayList<ProductDto> DressList (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P WHERE PTYPE='DRESS' " + 
				"                            ORDER BY PHIT DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}

//	-- 3-6-2. 상품 목록 종류별 악세서리 (인기순)
//	SELECT * FROM PRODUCT WHERE PTYPE='DRESS' ORDER BY PHIT DESC;
	public ArrayList<ProductDto> AccessoriesList (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P WHERE PTYPE='ACC' " + 
				"                            ORDER BY PHIT DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-6-3. 상품 목록 종류별 상의 (인기순)
//	SELECT * FROM PRODUCT WHERE PTYPE='DRESS' ORDER BY PHIT DESC;
	public ArrayList<ProductDto> TopsList (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P WHERE PTYPE='TOP' " + 
				"                            ORDER BY PHIT DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
//	-- 3-6-4. 상품 목록 종류별 하의 (인기순)
//	SELECT * FROM PRODUCT WHERE PTYPE='DRESS' ORDER BY PHIT DESC;
	public ArrayList<ProductDto> BottomsList (int startRow, int endRow){
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet			rs  = null;
		String sql = "SELECT * FROM " + 
				" (SELECT ROWNUM RN, A.* FROM " + 
				" (select P.* from PRODUCT P WHERE PTYPE='BOTTOM' " + 
				"                            ORDER BY PHIT DESC )A ) " + 
				"  WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pid 		= rs.getInt("pid");
				String pname	= rs.getString("pname");
				String ptype 	= rs.getString("ptype");      
			    String pcontent = rs.getString("pcontent");    
			    String pphoto 	= rs.getString("pphoto");
			    int pprice 		= rs.getInt("pprice"); 
			    int phit 		= rs.getInt("phit");  
			    Date prdate 	= rs.getDate("prdate");
			     
			    products.add(new ProductDto(pid, pname, ptype, pcontent, pphoto, pprice, phit, prdate));   
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
		
		return products;
	}
	
//	-- 4. 상품 정보 수정
//	UPDATE PRODUCT SET  PNAME = 'BLUE TOP',
//	                    PTYPE = 'TOP',
//	                    PCONTENT = '이것은 파란색 상의입니다.',
//	                    PPHOTO = 'NOIMG.JPG',
//	                    PPRICE = 43000
//	                    WHERE PID = 2;
	public int ModifyProduct (ProductDto product) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null; 
		String sql = "UPDATE PRODUCT SET  PNAME = ? , " + 
				"                    PTYPE 		= ? , " + 
				"                    PCONTENT 	= ? , " + 
				"                    PPHOTO 	= ? , " + 
				"                    PPRICE 	= ? "   + 
				"                    WHERE PID = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1, product.getPname());
			pstmt.setString (2, product.getPtype());
			pstmt.setString (3, product.getPcontent());
			pstmt.setString (4, product.getPphoto());
			pstmt.setInt 	(5, product.getPprice());
			pstmt.setInt 	(6, product.getPid());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "상품 수정 성공" : "상품 수정 실패");
		
		} catch (Exception e) {
			System.out.println(e.getMessage()+"product Modify Error");
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
	
//	-- 5. 상품 삭제
//
//	DELETE PRODUCT WHERE PID = 4;
	public int DeleteProduct (int pid) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null; 
		String sql = "DELETE PRODUCT WHERE PID = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt (1, pid);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "물품 삭제 성공" : "물품 삭제 실패");
		
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Product Delete Error");
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
