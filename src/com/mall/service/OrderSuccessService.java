package com.mall.service;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.OrderListDao;

public class OrderSuccessService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		String cid = request.getParameter("cid");
		int odid = Integer.parseInt(request.getParameter("odid")) ;
		System.out.println("주문 처리 과정 " + 11);
		OrderListDao odDao = OrderListDao.getInstance(); 
		int result = odDao.OrderCall(odid);
		System.out.println("주문 처리 과정 " + 22);
		if (result == OrderListDao.SUCCESS) {  
			session.setAttribute("OrderList", odid);
			request.setAttribute("OrderSuccessResult", result); 
		} else {
			request.setAttribute("OrderSuccessError", "Service - 주문 처리 실패");
		}


	}

}
