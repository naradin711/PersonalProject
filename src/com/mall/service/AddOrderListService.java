package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.mall.dao.OrderListDao;  
import com.mall.dto.OrderListDto;

public class AddOrderListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
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
		OrderListDto OrderList = new OrderListDto(cid, odtitle, odaddress, odprice);
		int result = dao.insertOrderList(OrderList);
		
			if(result == OrderListDao.SUCCESS) {
				session.setAttribute("OrderList", OrderList);
				request.setAttribute("insertOrderListResult", result);
			} else {
				request.setAttribute("insertOrderListError", "주문 목록 추가 실패");
			}

	}

}
