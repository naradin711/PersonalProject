package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.FreeBoardDao;
import com.mall.dto.FreeBoardDto; 

public class FreeBoardViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int fbid = Integer.parseInt(request.getParameter("fbid"));
		String pageNum = request.getParameter("pageNum");
		FreeBoardDao fbDao = FreeBoardDao.getInstance();
		FreeBoardDto freeboard = fbDao.FreeBoardView(fbid);
		request.setAttribute("FreeBoardView", freeboard);
 
	}

}
