package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.dao.CartDao;
import com.mall.dao.OrderListDao;
import com.mall.dao.ProductDao;
import com.mall.dto.CartDto;
import com.mall.dto.OrderListDto;

public class AddOrderListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("구매 목록 추가 과정 " + 11);
		String cid = request.getParameter("cid");
		System.out.println("구매 목록 추가 과정 " + 22);
		String odtitle =  request.getParameter("odtitle")  ;
		System.out.println("구매 목록 추가 과정 " + 33);
		String odaddress = request.getParameter("odaddress");
		System.out.println("구매 목록 추가 과정 " + 44);
		int odprice = Integer.parseInt(request.getParameter("odprice")) ;
		System.out.println("구매 목록 추가 과정 " + 55);
		OrderListDao dao = OrderListDao.getInstance(); 
		OrderListDto dto = new OrderListDto(cid, odtitle, odaddress, odprice);
		int result = dao.insertOrderList(dto);
		
			if(result == OrderListDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("OrderList", dto);
				request.setAttribute("insertOrderListResult", result);
			} else {
				request.setAttribute("insertOrderListError", "주문 목록 추가 실패");
			}

	}

}
