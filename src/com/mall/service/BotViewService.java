package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.ProductDao;

public class BotViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 6, BLOCKSIZE = 3;
		int startRow = ((currentPage-1)*PAGESIZE)+1;
		int endRow = startRow + PAGESIZE-1;
		ProductDao pDao = ProductDao.getInstance();
		request.setAttribute("list", pDao.BottomsList(startRow, endRow) );//글목록
		int totalCnt = pDao.getProductCnt();
		int pageCnt = (int)Math.ceil((double)totalCnt/PAGESIZE);// 페이지 수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE -1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCnt", pageCnt);


	}

}
