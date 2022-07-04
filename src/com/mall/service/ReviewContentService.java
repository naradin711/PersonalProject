package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.FreeBoardDao;
import com.mall.dao.ReviewDao;
import com.mall.dto.FreeBoardDto;
import com.mall.dto.ReviewDto;

public class ReviewContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rbid = Integer.parseInt(request.getParameter("rbid"));
		String pageNum = request.getParameter("pageNum");
		ReviewDao rbDao = ReviewDao.getInstance();
		ReviewDto ReviewView = rbDao.ReviewView(rbid);
		request.setAttribute("ReviewView", ReviewView);
 

	}

}
