package com.mall.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
public class CustomerModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw"); 
		String cname = request.getParameter("cname"); 
		String cemail = request.getParameter("cemail");
		String ctel = request.getParameter("ctel"); 
		String caddress = request.getParameter("caddress");
		String cBirthStr = request.getParameter("cbirth");
		Date cbirth = null;
		if(!cBirthStr.equals("")) {
			cbirth = Date.valueOf(cBirthStr);
		}
		HttpSession session = request.getSession();
		com.mall.dao.CustomerDao cDao = com.mall.dao.CustomerDao.getInstance();
		com.mall.dto.CustomerDto customer = new com.mall.dto.CustomerDto(cid, cpw, cname, cemail, ctel, caddress, cbirth);
		int result = cDao.ModifyCustomer(customer);
		if (result == com.mall.dao.CustomerDao.SUCCESS) { // 중복멤 없음 회원가입 가능
			System.out.println(7);
			session.setAttribute("customer", customer);
			request.setAttribute("CustomerModifyResult", result); 
		} else {
			request.setAttribute("CustomerModifyErrorMsg", "회원 정보 수정 실패");
		}

	}

}
