package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.ProductDao;
import com.mall.dao.CartDao;
import com.mall.dto.CartDto;

public class AddCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		    System.out.println("장바구니 서비스 에러 " + 11);
			String cid = request.getParameter("cid");
			System.out.println("장바구니 서비스 에러 " + 22);
			int pid = Integer.parseInt(request.getParameter("pid")) ;
			System.out.println("장바구니 서비스 에러 " + 33);
			String pname = request.getParameter("pname");
			System.out.println("장바구니 서비스 에러 " + 44);
			int pprice = Integer.parseInt(request.getParameter("pprice")) ;
			System.out.println("장바구니 서비스 에러 " + 55);
			String pphoto = request.getParameter("pphoto"); // 첨부된 파일이 저장되는 파일 이름
			System.out.println("장바구니 서비스 에러 " + 66);
			CartDao cartDao = CartDao.getInstance();
			// 상품 추가
			CartDto dto = new CartDto(cid, pid, pname, pphoto, pprice);
			int result = cartDao.insertCart(dto);
			
				if(result == ProductDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("Cart", dto);
					request.setAttribute("CartAddResult", result);
				} else {
					request.setAttribute("CartAddError", "장바구니 추가 실패");
				}
		}

}
