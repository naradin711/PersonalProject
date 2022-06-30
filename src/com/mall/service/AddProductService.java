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
import com.mall.dto.ProductDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AddProductService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getRealPath("productFileUp");
		int MaxSize = 1024*1024*50; //사진 업로드 제한
		String pphoto = ""; // 첨부된 파일이 저장되는 파일 이름
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성 후 mPhoto 파일이름 얻어옴
			mRequest = new MultipartRequest(request, path, MaxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				pphoto = mRequest.getFilesystemName(param);
			}
			System.out.println(pphoto+"들어옴");
			// mRequest 객체를 이용하여 파라미터 받아와서 DB 저장
			String pname = mRequest.getParameter("pname");
			String ptype = mRequest.getParameter("ptype");
			String pcontent = mRequest.getParameter("pcontent");
			pphoto = pphoto==null? "NOIMG.JPG" : pphoto;
			int pprice = Integer.parseInt(mRequest.getParameter("pprice"));
			ProductDao pDao = ProductDao.getInstance();
			// 상품 추가
			ProductDto product = new ProductDto(pname, ptype, pcontent, pphoto, pprice);
			int result = pDao.insertProduct(product);
			
				if(result == ProductDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("product", product);
					request.setAttribute("AddProductResult", result);
				} else {
					request.setAttribute("AddProductErrorMsg", "상품 추가 실패");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "add product service");
		}
		
		// 파일 복사
		File serverFile = new File (path +"/"+ pphoto);
		if (serverFile.exists() && !pphoto.equals("NOIMG.JPG")) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/webPro_ryu/source/07_JQuery/PersonalProject/WebContent/productFileUp/"+pphoto);
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
