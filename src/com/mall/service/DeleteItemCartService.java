package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import com.mall.dao.CartDao;
import com.mall.dao.ProductDao; 
 

public class DeleteItemCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cartid = Integer.parseInt(request.getParameter("cartid")) ;
		CartDao cartDao = CartDao.getInstance();
		int result = cartDao.DeleteCart(cartid) ;
		if (result == CartDao.SUCCESS) { // 삭제 성공
			request.setAttribute("DeleteCartOneResult", "장바구니 상품 삭제 성공"); 
		} else {
			request.setAttribute("DeleteCartOneError", "장바구니 상품 삭제 실패");
		}

	}

}
