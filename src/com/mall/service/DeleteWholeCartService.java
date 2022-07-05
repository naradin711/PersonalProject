package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.CartDao;  

public class DeleteWholeCartService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid") ;
		CartDao cartDao = CartDao.getInstance();
		int result = cartDao.DeleteCartAll(cid);
		if (result == CartDao.FAIL) { // 삭제 성공
			request.setAttribute("DeleteCartWholeError", "장바구니 전체 삭제 실패"); 
		} else {
			request.setAttribute("DeleteCartWholeResult", "장바구니 전체 삭제 성공");
		}


	}

}
