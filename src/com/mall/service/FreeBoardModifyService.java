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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardModifyService implements Service {

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
			int fbid = Integer.parseInt(mRequest.getParameter("fbid"));
			String cid = mRequest.getParameter("cid");
			String fbtitle = mRequest.getParameter("fbtitle");
			String fbcontent = mRequest.getParameter("fbcontent");
			HttpSession session = request.getSession();
			FreeBoardDto pp =(FreeBoardDto)session.getAttribute("freeboard");
			String sessionPhoto = null;
			if (pp!=null) {
				sessionPhoto = pp.getFbphoto() ; // 세션의 pw를 sessionPw에 할당
			}
			fbphoto = fbphoto==null? sessionPhoto : fbphoto;
			String fbip = request.getRemoteAddr() ;
			String fbpw = mRequest.getParameter("fbpw");
			FreeBoardDao fbDao = FreeBoardDao.getInstance();
			// 상품 추가
			FreeBoardDto freeboard = new FreeBoardDto(cid, fbtitle, fbcontent, fbphoto, fbip, fbid, fbpw) ;
			int result = fbDao.ModifyFreeBoard(freeboard) ;
			
				if(result == FreeBoardDao.SUCCESS) {
					session = request.getSession();
					session.setAttribute("freeboard", freeboard);
					request.setAttribute("FreeBoardModifyResult", result);
				} else {
					request.setAttribute("FreeBoardModifyError", "글 수정 실패");
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
