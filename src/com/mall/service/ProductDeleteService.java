package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.ProductDao;

public class ProductDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pid = Integer.parseInt(request.getParameter("pid")) ;
		ProductDao pDao = ProductDao.getInstance();
		int result = pDao.DeleteProduct(pid);
		if (result == ProductDao.SUCCESS) { // 삭제 성공
			request.setAttribute("DeleteProductResult", "회원 탈퇴 성공"); 
		} else {
			request.setAttribute("DeleteProductError", "회원 탈퇴 실패");
		}

	}

}
