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

public class FreeBoardReplyService implements Service {

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
			String cid = mRequest.getParameter("cid");
			System.out.println("replyerror 11");
			String aname = mRequest.getParameter("aname");
			System.out.println("replyerror 22");
			String fbtitle = mRequest.getParameter("fbtitle");
			System.out.println("replyerror 33");
			String fbcontent = mRequest.getParameter("fbcontent");
			System.out.println("replyerror 44");
			fbphoto = fbphoto==null? "NOIMG.JPG" : fbphoto;
			System.out.println("replyerror 55");
			String fbip = request.getRemoteAddr();
			System.out.println("replyerror 66");
			int fbid = Integer.parseInt(mRequest.getParameter("fbid"));
			System.out.println("replyerror 77");
			int fbgroup = Integer.parseInt(mRequest.getParameter("fbgroup"));
			System.out.println("replyerror 88");
			String fbpw = mRequest.getParameter("fbpw");
			System.out.println("replyerror 99");
			FreeBoardDao fbDao = FreeBoardDao.getInstance();
			// 상품 추가
			FreeBoardDto freeboard = new FreeBoardDto(fbid, cid, aname, fbtitle, fbcontent, fbphoto, fbip, fbgroup, fbpw) ;
			int result = fbDao.ReplyFreeBoard(freeboard);
			
				if(result == FreeBoardDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("freeboard", freeboard);
					request.setAttribute("FreeBoardReplyResult", result);
				} else {
					request.setAttribute("FreeBoardReplyError", "답글 쓰기 실패");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " reply freeboard service error");
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
