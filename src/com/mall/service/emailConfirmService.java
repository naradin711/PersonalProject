package com.mall.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.dao.CustomerDao;

public class emailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cemail = request.getParameter("cemail");
		CustomerDao cDao = CustomerDao.getInstance();
		int result = cDao.confirmCemail(cemail);
		if (result==CustomerDao.ID_EXISTENT) {
			request.setAttribute("emailConfirmResult", "중복된 e-mail 입니다.");
		}else {
			request.setAttribute("emailConfirmResult", "사용 가능한  e-mail입니다.");
		}

	}

}
