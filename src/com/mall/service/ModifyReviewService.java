package com.mall.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.ProductDao;
import com.mall.dao.ReviewDao;
import com.mall.dto.ProductDto;
import com.mall.dto.ReviewDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ModifyReviewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getRealPath("reviewFileUp");
		int MaxSize = 1024*1024*50; //사진 업로드 제한
		String rbphoto = ""; // 첨부된 파일이 저장되는 파일 이름
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성 후 mPhoto 파일이름 얻어옴
			mRequest = new MultipartRequest(request, path, MaxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				rbphoto = mRequest.getFilesystemName(param);
			}
			System.out.println(rbphoto+"들어옴");
			// mRequest 객체를 이용하여 파라미터 받아와서 DB 저장
			System.out.println(33);
			String rbtitle = mRequest.getParameter("rbtitle");
			System.out.println(44);
			String rbcontent = mRequest.getParameter("rbcontent"); 
			System.out.println(55);
			HttpSession session = request.getSession();
			ReviewDto pp =(ReviewDto)session.getAttribute("ReviewView");
			String sessionPhoto = null;
			if (pp!=null) {
				sessionPhoto = pp.getRbphoto() ; // 세션의 pw를 sessionPw에 할당
			}
			rbphoto = rbphoto==null? sessionPhoto : rbphoto;
			System.out.println(66);
			String rbip = request.getRemoteAddr();
			System.out.println(76);
			int rbid = Integer.parseInt(mRequest.getParameter("rbid")) ;
			System.out.println(77);
			String rbpw = mRequest.getParameter("rbpw");
			
			ReviewDao rbDao = ReviewDao.getInstance();
			// 상품 추가
			ReviewDto dto = new ReviewDto(rbtitle, rbcontent, rbphoto, rbip, rbid, rbpw);
			int result = rbDao.ModifyReview(dto);
			
				if(result == ProductDao.SUCCESS) {
					session.setAttribute("review", dto);
					request.setAttribute("reviewModifyResult", result);
				} else {
					request.setAttribute("reviewModifyError", "리뷰 수정 실패");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Modify review service");
		}
		
		// 파일 복사
		File serverFile = new File (path +"/"+ rbphoto);
		if (serverFile.exists() && !rbphoto.equals("NOIMG.JPG")) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/webPro_ryu/source/07_JQuery/PersonalProject/WebContent/reviewFileUp/"+rbphoto);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int readByCnt = is.read(bs);
					if(readByCnt==-1)break;
					os.write(bs, 0, readByCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} 
			}
		}

	}

}
