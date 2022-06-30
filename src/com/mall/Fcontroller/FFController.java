package com.mall.Fcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.service.AddProductService;
import com.mall.service.AdminProductViewService;
import com.mall.service.BestSellerViewService;
import com.mall.service.CJoinService;
import com.mall.service.CLoginService;
import com.mall.service.CLogoutService;
import com.mall.service.CustomerListService;
import com.mall.service.CustomerModifyService;
import com.mall.service.CustomerModifyService2;
import com.mall.service.CustomerViewService;
import com.mall.service.DeleteCustomerService;
import com.mall.service.DeleteCustomerService2;
import com.mall.service.IdConfirmService;
import com.mall.service.ProductDeleteService;
import com.mall.service.ProductListService;
import com.mall.service.ProductModifyService;
import com.mall.service.Service;
import com.mall.service.emailConfirmService;
import com.mall.service.telConfirmService;

@WebServlet("*.do")
public class FFController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean doAction = false;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			actionDo(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			actionDo(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length()); //들어온 요청 : ~do
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) {// 회원 가입 화면 출력
			service = new BestSellerViewService();//  
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} //
		  // INDEX ADMIN
		  //
		  else if (command.equals("/adminLoginView.do")) { // 관리자 로그인 화면
			 doAction=true;
			 viewPage = "admin/adminLoginView.jsp";
		} else if (command.equals("/adminLogin.do")) { // ID 중복 체크하기
			if(doAction) {
			service = new com.mall.service.AdminLoginService(); 
			service.execute(request, response); // aid parameter로 idConfirm한 결과를 request로 객체 set
			doAction = false;
			}
			viewPage = "ProductList.do";
		} else if(command.equals("/CustomerList.do")) {// 회원정보 페이지 가기
			service = new CustomerListService();//  
			service.execute(request, response);
			viewPage = "admin/CustomerList.jsp";
		} else if(command.equals("/CustomerView.do")) {// 개인정보 상세보기
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "admin/customerView.jsp";
		} else if (command.equals("/CustomerModify2View.do")) {// 개인정보 상세보기
			doAction = true;
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "admin/customer_modify_view.jsp";
		} else if (command.equals("/CustomerModify2.do")) {// 개인정보 상세보기
			if(doAction) {
			service = new CustomerModifyService2();
			service.execute(request, response);
			doAction = false;
			}
			viewPage = "CustomerList.do";
		} else if (command.equals("/DeleteCustomer2.do")) { // ID 중복 체크하기
			service = new DeleteCustomerService2();//  
			service.execute(request, response); // aid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "CustomerList.do";
		} else if (command.equals("/ProductList.do")) { // ID 중복 체크하기
			service = new ProductListService();//  
			service.execute(request, response); // aid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "admin/ProductList.jsp";
		}
		
		 //
		 // INDEX CUSTOMER
		 //	
		
		  else if (command.equals("/joinView.do")) {
			doAction = true;
			viewPage = "customer/joinView.jsp";
		} else if (command.equals("/idConfirm.do")) {
			service = new IdConfirmService();//  
			service.execute(request, response);
			viewPage = "customer/idConfirm.jsp";
		} else if (command.equals("/emailConfirm.do")) {
			service = new emailConfirmService();//  
			service.execute(request, response);
			viewPage = "customer/emailConfirm.jsp";
		} else if (command.equals("/telConfirm.do")) {
			service = new telConfirmService();//  
			service.execute(request, response);
			viewPage = "customer/telConfirm.jsp";
		} else if(command.equals("/join.do")) {// 회원 가입 db 처리
			if(doAction) {
			service = new CJoinService();// 아이디 중복체크 후 회원 가입
			service.execute(request, response);
			doAction = false;
			}
			viewPage = "loginView.do";
		} else if(command.equals("/loginView.do")) {// 로그인 페이지 가기
			doAction = true;
			viewPage = "customer/login.jsp";
		} else if(command.equals("/login.do")) {// 로그인 db 처리
			if(doAction) {
				service = new CLoginService();//  
				service.execute(request, response);
				doAction = false;
			}
			viewPage = "main/main.jsp";
		} else if(command.equals("/logout.do")) {// 로그아웃 db 처리
			service = new CLogoutService();//  
			service.execute(request, response);
			viewPage = "customer/login.jsp";
		} else if(command.equals("/mypage.do")) {// 내 페이지
			viewPage = "customer/mypage.jsp";
		} else if (command.equals("/CustomerModifyView.do")) {// 개인정보 상세보기
			doAction = true;
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "customer/mypage_modify.jsp";
		} else if (command.equals("/CustomerModify.do")) {// 개인정보 상세보기
			if(doAction) {
			service = new CustomerModifyService();
			service.execute(request, response);
			doAction = false;
			} 
			viewPage = "mypage.do";
		} else if(command.equals("/main1.do")) {// 회원 가입 화면 출력
			service = new CLogoutService();//  
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (command.equals("/DeleteCustomer.do")) { // ID 중복 체크하기
			service = new DeleteCustomerService();//  
			service.execute(request, response); // aid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "main1.do";
		} 
		 //
		 // INDEX PRODUCT
		 //	
		else if (command.equals("/AddProductView.do")) { // ID 중복 체크하기
			viewPage = "product/addProductView.jsp";
		} else if (command.equals("/AddProduct.do")) { // ID 중복 체크하기
			service = new AddProductService();//  
			service.execute(request, response); // aid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "ProductList.do";
		} else if (command.equals("/AdminProductView.do")) { // ID 중복 체크하기
			service = new AdminProductViewService();//  
			service.execute(request, response); // pid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "admin/adminProductView.jsp";
		} else if (command.equals("/ProductModifyView.do")) { // ID 중복 체크하기
			doAction = true;
			service = new AdminProductViewService();//  
			service.execute(request, response); // pid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "admin/product_modify_view.jsp";
		} else if (command.equals("/ProductModify.do")) { // ID 중복 체크하기
			if(doAction) {
			service = new ProductModifyService();//  
			service.execute(request, response); // pid parameter로 idConfirm한 결과를 request로 객체 set
			doAction = false;
			}
			viewPage = "ProductList.do";
		} else if (command.equals("/ProductDelete.do")) { // ID 중복 체크하기
			service = new ProductDeleteService();//  
			service.execute(request, response); // pid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "ProductList.do";
		} else if (command.equals("/BestSellerView.do")) { // ID 중복 체크하기
			service = new BestSellerViewService();//  
			service.execute(request, response); // pid parameter로 idConfirm한 결과를 request로 객체 set
			viewPage = "product/bestSeller.jsp";
		};
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}





















