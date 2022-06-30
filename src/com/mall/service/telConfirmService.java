package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.CustomerDao;
import com.mall.dto.CustomerDto;

public class telConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ctel = request.getParameter("ctel");
		CustomerDao cDao = CustomerDao.getInstance();
		int result = cDao.confirmCtel(ctel);
		if (result==CustomerDao.ID_EXISTENT) {
			request.setAttribute("telConfirmResult", "중복된 번호입니다." );
		}else {
			request.setAttribute("telConfirmResult", "사용 가능한  번호입니다." );
		}

	}

}
