package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.OrderDetailDao;
import com.mall.dao.OrderListDao;
import com.mall.dto.OrderListDto;

public class OrderDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("개인 구매 내역 추가하기 " + 11);
		String cid = request.getParameter("cid"); 
		System.out.println("개인 구매 내역 추가하기 " + 22);
		OrderDetailDao dao = OrderDetailDao.getInstance();  
		int result = dao.insertOrderDetail(cid);
		
			if(result == OrderListDao.FAIL) {
				request.setAttribute("insertOrderDetailError", "개인 구매 내역 추가 실패");
			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute("OrderDetail", cid);
				request.setAttribute("insertOrderDetailResult", result);
			}


	}

}
