package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.mall.dao.CustomerDao;

public class CustomerViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		String pageNum = request.getParameter("pageNum");
		CustomerDao cDao = CustomerDao.getInstance();
		request.setAttribute("Customer_view", cDao.CustomerView(cid));

	}

}
