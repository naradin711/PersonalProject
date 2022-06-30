package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.CustomerDao;
public class IdConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		CustomerDao cDao = CustomerDao.getInstance();
		int result = cDao.confirmCid(cid);
		if (result==CustomerDao.ID_EXISTENT) {
			request.setAttribute("idConfirmResult", "중복된 아이디 입니다." );
		}else {
			request.setAttribute("idConfirmResult", "사용 가능한  ID입니다." );
		}

	}

}
