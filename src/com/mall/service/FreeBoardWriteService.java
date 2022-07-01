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

import com.mall.dao.FreeBoardDao; 
import com.mall.dto.FreeBoardDto;
import com.mall.dto.ProductDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getRealPath("freeboardFileUp");
		int MaxSize = 1024*1024*50; //사진 업로드 제한
		String fbphoto = ""; // 첨부된 파일이 저장되는 파일 이름
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성 후 mPhoto 파일이름 얻어옴
			mRequest = new MultipartRequest(request, path, MaxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				fbphoto = mRequest.getFilesystemName(param);
			}
			System.out.println(fbphoto+"들어옴");
			// mRequest 객체를 이용하여 파라미터 받아와서 DB 저장 
			String fbtitle = mRequest.getParameter("fbtitle");
			String fbcontent = mRequest.getParameter("fbcontent");
			fbphoto = fbphoto==null? "NOIMG.JPG" : fbphoto;
			int fbid = Integer.parseInt(mRequest.getParameter("fbid"))  ;
			String fbpw = mRequest.getParameter("fbpw");
			FreeBoardDao fbDao = FreeBoardDao.getInstance();
			// 상품 추가
			FreeBoardDto freeboard = new FreeBoardDto(fbtitle, fbcontent, fbphoto, fbid, fbpw) ;
			int result = fbDao.insertFreeBoard(freeboard);
			
				if(result == FreeBoardDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("freeboard", freeboard);
					request.setAttribute("FreeBoardWriteResult", result);
				} else {
					request.setAttribute("FreeBoardWriteError", "글 쓰기 실패");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "write freeboard service error");
		}
		
		// 파일 복사
		File serverFile = new File (path +"/"+ fbphoto);
		if (serverFile.exists() && !fbphoto.equals("NOIMG.JPG")) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/webPro_ryu/source/07_JQuery/PersonalProject/WebContent/freeboardFileUp/"+fbphoto);
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
