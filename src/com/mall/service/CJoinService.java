package com.mall.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.CustomerDao;
import com.mall.dto.CustomerDto;

public class CJoinService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 
			String cid = request.getParameter("cid");
			String cpw = request.getParameter("cpw");
			System.out.println(1);
			String cname = request.getParameter("cname");
			String cemail = request.getParameter("cemail");
			String ctel = request.getParameter("ctel");
			String cBirthStr = request.getParameter("cbirth");
			Date cbirth = null;
			if(!cBirthStr.equals("")) {
				cbirth = Date.valueOf(cBirthStr);
			}
			String caddress = request.getParameter("caddress");
			CustomerDao cDao = CustomerDao.getInstance();
			// cid 중복체크
			int result = cDao.confirmCid(cid);
			if (result == CustomerDao.ID_NONEXISTENT) { // 중복멤 없음 회원가입 가능
				CustomerDto customer = new CustomerDto(cid, cpw, cname, cemail, ctel, caddress, cbirth) ;
				result = cDao.JoinCustomer(customer);
				// 회원가입
				if(result == CustomerDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("cid", cid);
					request.setAttribute("joinResult", result);
				} else {
					request.setAttribute("joinErrorMsg", "회원가입 실패");
				}
			} else {
				request.setAttribute("joinErrorMsg", "중복된 아이디는 회원가입이 불가능합니다.");
			} 
	
		}  
		  
}













