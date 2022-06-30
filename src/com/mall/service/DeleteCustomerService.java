package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dto.CustomerDto; 
 

public class DeleteCustomerService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		CustomerDto customer = (CustomerDto)session.getAttribute("customer");
		if(customer!=null) {
			String cid = customer.getCid();
			com.mall.dao.CustomerDao cDao = com.mall.dao.CustomerDao.getInstance();
			int result = cDao.DeleteCustomer(cid);
			if (result == com.mall.dao.CustomerDao.SUCCESS) { // 삭제 성공 
				session.setAttribute("cid", cid);
				request.setAttribute("DeleteCustomerResult", "회원 탈퇴 성공"); 
			} else {
				request.setAttribute("DeleteCustomerError", "회원 탈퇴 실패");
			}
		}else {
			request.setAttribute("DeleteCustomerError", "로그인을 하셔야 탈퇴가 됨");
		}

	}

}
