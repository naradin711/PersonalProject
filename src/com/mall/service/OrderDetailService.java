package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.OrderDetailDao;
import com.mall.dao.OrderListDao; 

public class OrderDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("개인 구매 내역 추가하기 " + 11);
		String cid = request.getParameter("cid");
		System.out.println("개인 구매 내역 추가하기 " + 22); 
		OrderDetailDao dao = OrderDetailDao.getInstance();  
		int result = dao.insertOrderDetail(cid);
		
			if(result == OrderListDao.FAIL) {
				request.setAttribute("insertOrderDetailError", "개인 구매 내역 추가 실패 Service");
			} else { 
				session.setAttribute("OrderDetail", cid);
				request.setAttribute("insertOrderDetailResult", result);
			}


	}

}
