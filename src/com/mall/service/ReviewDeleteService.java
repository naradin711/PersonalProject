package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.ProductDao;
import com.mall.dao.ReviewDao;

public class ReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rbid = Integer.parseInt(request.getParameter("rbid")) ;
		String rbpw = request.getParameter("rbpw");
		ReviewDao pDao = ReviewDao.getInstance();
		int result = pDao.DeleteReview(rbid, rbpw);
		if (result == ProductDao.SUCCESS) { // 삭제 성공
			request.setAttribute("DeleteReviewResult", "리뷰 삭제 성공"); 
		} else {
			request.setAttribute("DeleteReviewError", "리뷰 삭제 실패");
		}

	}

}
