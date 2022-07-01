package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.FreeBoardDao;
import com.mall.dao.ProductDao;

public class FreeBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int fbid = Integer.parseInt(request.getParameter("fbid"));
		String fbpw = request.getParameter("fbpw");
		FreeBoardDao fDao = FreeBoardDao.getInstance();
		int result = fDao.DeleteFreeBoard(fbid, fbpw);
		if (result == ProductDao.SUCCESS) { // 삭제 성공
			request.setAttribute("FreeBoardDeleteResult", "글 삭제 성공"); 
		} else {
			request.setAttribute("FreeBoardDeleteError", "글 삭제 실패");
		}
	}

}
