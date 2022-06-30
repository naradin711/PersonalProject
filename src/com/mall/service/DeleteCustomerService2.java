package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
 

public class DeleteCustomerService2 implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		com.mall.dao.CustomerDao cDao = com.mall.dao.CustomerDao.getInstance();
		int result = cDao.DeleteCustomer(cid);
		if (result == com.mall.dao.CustomerDao.SUCCESS) { // 삭제 성공
			request.setAttribute("DeleteCustomerResult", "회원 탈퇴 성공"); 
		} else {
			request.setAttribute("DeleteCustomerError", "회원 탈퇴 실패");
		}

	}

}
