package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.CustomerDao;
import com.mall.dto.CustomerDto;


public class CLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");
		CustomerDao cDao = CustomerDao.getInstance();
		int result = cDao.loginCheck(cid, cpw);
		if(result == CustomerDao.LOGIN_SUCCESS) {
			CustomerDto customer = cDao.CustomerView(cid);
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			request.setAttribute("LoginResult", result);
		} else {
			request.setAttribute("LoginError", "로그인에 실패하셨습니다.");
		}
		
	}

}
