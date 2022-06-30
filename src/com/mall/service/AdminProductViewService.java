package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.CustomerDao;
import com.mall.dao.ProductDao;

public class AdminProductViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pageNum = request.getParameter("pageNum");
		ProductDao pDao = ProductDao.getInstance();
		request.setAttribute("AdminProductView", pDao.AdminProductView(pid));


	}

}
