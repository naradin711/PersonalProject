package com.mall.Fcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.service.AccViewService;
import com.mall.service.AddCartService;
import com.mall.service.AddOrderListService;
import com.mall.service.AddProductService;
import com.mall.service.AdminOrderDetailService;
import com.mall.service.AdminProductViewService;
import com.mall.service.BestSellerViewService;
import com.mall.service.BoardListService;
import com.mall.service.BotViewService;
import com.mall.service.CJoinService;
import com.mall.service.CLoginService;
import com.mall.service.CLogoutService;
import com.mall.service.CustomerListService;
import com.mall.service.CustomerModifyService;
import com.mall.service.CustomerModifyService2;
import com.mall.service.CustomerProductService;
import com.mall.service.CustomerViewService;
import com.mall.service.DeleteCustomerService;
import com.mall.service.DeleteCustomerService2;
import com.mall.service.DeleteItemCartService;
import com.mall.service.DeleteWholeCartService;
import com.mall.service.DressViewService;
import com.mall.service.FreeBoardDeleteService;
import com.mall.service.FreeBoardModifyService;
import com.mall.service.FreeBoardReplyService;
import com.mall.service.FreeBoardViewService;
import com.mall.service.FreeBoardWriteService;
import com.mall.service.IdConfirmService;
import com.mall.service.ModifyReviewService;
import com.mall.service.MyCartService; 
import com.mall.service.MyOrderService;
import com.mall.service.OrderDetailService;
import com.mall.service.OrderListService;
import com.mall.service.OrderSuccessService;
import com.mall.service.ProductDeleteService;
import com.mall.service.ProductListService;
import com.mall.service.ProductModifyService;
import com.mall.service.ReviewContentService;
import com.mall.service.ReviewDeleteService;
import com.mall.service.ReviewListService;
import com.mall.service.Service;
import com.mall.service.TopViewService;
import com.mall.service.WhatsNewService;
import com.mall.service.WriteReviewService;
import com.mall.service.emailConfirmService;
import com.mall.service.telConfirmService;
import com.sun.java.swing.plaf.windows.resources.windows;

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
		String command = uri.substring(conPath.length()); //????????? ?????? : ~do
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) {// ?????? ?????? ?????? ??????
			service = new BestSellerViewService();//  
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} //
		  // INDEX ADMIN
		  //
		  else if (command.equals("/adminLoginView.do")) { // ????????? ????????? ??????
			 doAction=true;
			 viewPage = "admin/adminLoginView.jsp";
		} else if (command.equals("/adminLogin.do")) { // ID ?????? ????????????
			if(doAction) {
			service = new com.mall.service.AdminLoginService(); 
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			doAction = false;
			}
			viewPage = "ProductList.do";
		} else if(command.equals("/CustomerList.do")) {// ???????????? ????????? ??????
			service = new CustomerListService();//  
			service.execute(request, response);
			viewPage = "admin/CustomerList.jsp";
		} else if(command.equals("/CustomerView.do")) {// ???????????? ????????????
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "admin/customerView.jsp";
		} else if (command.equals("/CustomerModify2View.do")) {// ???????????? ????????????
			doAction = true;
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "admin/customer_modify_view.jsp";
		} else if (command.equals("/CustomerModify2.do")) {// ???????????? ????????????
			if(doAction) {
			service = new CustomerModifyService2();
			service.execute(request, response);
			doAction = false;
			}
			viewPage = "CustomerList.do";
		} else if (command.equals("/DeleteCustomer2.do")) { // ID ?????? ????????????
			service = new DeleteCustomerService2();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "CustomerList.do";
		} else if (command.equals("/ProductList.do")) { // ID ?????? ????????????
			service = new ProductListService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "admin/ProductList.jsp";
		} else if (command.equals("/ProductList1.do")) { // ID ?????? ????????????
			service = new ProductListService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
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
		} else if(command.equals("/join.do")) {// ?????? ?????? db ??????
			if(doAction) {
			service = new CJoinService();// ????????? ???????????? ??? ?????? ??????
			service.execute(request, response);
			doAction = false;
			}
			viewPage = "loginView.do";
		} else if(command.equals("/loginView.do")) {// ????????? ????????? ??????
			doAction = true;
			viewPage = "customer/login.jsp";
		} else if(command.equals("/login.do")) {// ????????? db ??????
			if(doAction) {
				service = new CLoginService();//  
				service.execute(request, response);
				viewPage = "main.do";
				doAction = false;
			}
			
		} else if(command.equals("/logout.do")) {// ???????????? db ??????
			service = new CLogoutService();//  
			service.execute(request, response);
			viewPage = "customer/login.jsp";
		} else if(command.equals("/mypage.do")) {// ??? ?????????
			viewPage = "customer/mypage.jsp";
		} else if (command.equals("/CustomerModifyView.do")) {// ???????????? ????????????
			doAction = true;
			service = new CustomerViewService();
			service.execute(request, response);
			viewPage = "customer/mypage_modify.jsp";
		} else if (command.equals("/CustomerModify.do")) {// ???????????? ????????????
			if(doAction) {
			service = new CustomerModifyService();
			service.execute(request, response);
			doAction = false;
			} 
			viewPage = "mypage.do";
		} else if(command.equals("/main1.do")) {// ?????? ?????? ?????? ??????
			service = new CLogoutService();//  
			service.execute(request, response);
			viewPage = "main.do";
		} else if (command.equals("/DeleteCustomer.do")) { // ID ?????? ????????????
			service = new DeleteCustomerService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "main1.do";
		} else if (command.equals("/CustomerProductView.do")) {  
			service = new CustomerProductService();//
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "ReviewList.do";
		} else if (command.equals("/ReviewList.do")) {  
			 service = new ReviewListService();//
			 service.execute(request, response);
			viewPage = "customer/customer_Product_View.jsp";
		} 
		 //
		 // INDEX PRODUCT
		 //	
		else if (command.equals("/AddProductView.do")) { // ID ?????? ????????????
			viewPage = "product/addProductView.jsp";
		} else if (command.equals("/AddProduct.do")) { // ID ?????? ????????????
			service = new AddProductService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "ProductList.do";
		} else if (command.equals("/AdminProductView.do")) { // ID ?????? ????????????
			service = new AdminProductViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "admin/adminProductView.jsp";
		} else if (command.equals("/ProductModifyView.do")) { // ID ?????? ????????????
			doAction = true;
			service = new AdminProductViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "admin/product_modify_view.jsp";
		} else if (command.equals("/ProductModify.do")) { // ID ?????? ????????????
			if(doAction) {
			service = new ProductModifyService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			doAction = false;
			}
			viewPage = "ProductList.do";
		} else if (command.equals("/ProductDelete.do")) { // ID ?????? ????????????
			service = new ProductDeleteService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "ProductList.do";
		} else if (command.equals("/BestSellerView.do")) { // ID ?????? ????????????
			service = new BestSellerViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "product/bestSeller.jsp";
		} else if (command.equals("/WhatsNewView.do")) { // ID ?????? ????????????
			service = new WhatsNewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "product/whatsNew.jsp";
		} else if (command.equals("/DressView.do")) {  
			service = new DressViewService();//  
			service.execute(request, response);   
			viewPage = "product/dress.jsp";
		} else if (command.equals("/AccView.do")) { // ID ?????? ????????????
			service = new AccViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "product/acc.jsp";
		} else if (command.equals("/TopView.do")) { // ID ?????? ????????????
			service = new TopViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "product/tops.jsp";
		} else if (command.equals("/BotView.do")) { // ID ?????? ????????????
			service = new BotViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "product/bottoms.jsp";
		}
		 //
		 // INDEX FREEBOARD
		 //
	      else if (command.equals("/boardList.do")) { // ID ?????? ????????????
			service = new BoardListService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "freeboard/freeboard.jsp";
		} else if (command.equals("/FreeBoardWrite.do")) { // ID ?????? ????????????
			service = new FreeBoardWriteService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "boardList.do";
		} else if (command.equals("/FreeBoardView.do")) { // ID ?????? ????????????
			service = new FreeBoardViewService();//  
			service.execute(request, response); // aid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "freeboard/freeBoardView.jsp";
		} else if (command.equals("/FreeBoardModifyView.do")) { // ID ?????? ????????????
			doAction = true;
			service = new FreeBoardViewService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "freeboard/freeBoardModifyView.jsp";
		} else if (command.equals("/FreeBoardModify.do")) { // ID ?????? ????????????
			if(doAction) {
			service = new FreeBoardModifyService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			doAction = false;
			}
			viewPage = "boardList.do";
		} else if (command.equals("/FreeBoardDeleteView.do")) { // ID ?????? ????????????
			doAction = true;
			service = new FreeBoardViewService();//
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "freeboard/freeBoardDeleteView.jsp";
		} else if (command.equals("/FreeBoardDelete.do")) { // ID ?????? ????????????
			if(doAction) {
			service = new FreeBoardDeleteService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			doAction = false;
			}
			viewPage = "boardList.do";
		} else if (command.equals("/FreeBoardReplyView.do")) { // ID ?????? ????????????
			doAction = true;
			service = new FreeBoardViewService();//
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			viewPage = "freeboard/freeBoardReplyView.jsp";
		} else if (command.equals("/FreeBoardReply.do")) { // ID ?????? ????????????
			if(doAction) {
			service = new FreeBoardReplyService();//  
			service.execute(request, response); // pid parameter??? idConfirm??? ????????? request??? ?????? set
			doAction = false;
			}
			viewPage = "boardList.do";
		}
		 //
		 // INDEX REVIEWBOARD
		 //
		else if (command.equals("/WriteReviewView.do")) {  
			doAction = true; 
			service = new CustomerProductService();//
			service.execute(request, response);
			viewPage = "review/writeReview_view.jsp";
		} else if (command.equals("/WriteReview.do")) {  
			if(doAction) {
			service = new WriteReviewService(); 
			service.execute(request, response);     
			viewPage = "CustomerProductView.do";
			doAction = false;
			}
		} else if (command.equals("/ReviewContent.do")) { 
			service = new ReviewContentService(); 
			service.execute(request, response);     
			viewPage = "review/reviewContent.jsp";  
		} else if (command.equals("/ReviewModifyView.do")) {  
			doAction = true; 
			service = new ReviewContentService();
			service.execute(request, response);
			viewPage = "review/modifyReview_view.jsp";
		} else if (command.equals("/ReviewModify.do")) {  
			if(doAction) {
			service = new ModifyReviewService(); 
			service.execute(request, response);     
			viewPage = "CustomerProductView.do";
			doAction = false;
			}
		} else if (command.equals("/ReviewDeleteView.do")) {  
			doAction = true; 
			service = new ReviewContentService();
			service.execute(request, response);
			viewPage = "review/deleteReview_view.jsp";
		} else if (command.equals("/ReviewDelete.do")) {  
			if(doAction) {
			service = new ReviewDeleteService(); 
			service.execute(request, response);     
			viewPage = "CustomerProductView.do";
			doAction = false;
			}
		}
		 //
		 // INDEX CART
		 //	
		
		  else if (command.equals("/addCart.do")) {	  
			service = new AddCartService();
			service.execute(request, response);
			viewPage = "CustomerProductView.do";
		} else if (command.equals("/MyCart.do")) {	  
			service = new MyCartService();
			service.execute(request, response);
			viewPage = "cart/cartMypage.jsp";
		} else if (command.equals("/DeleteItemCart.do")) {	  
			service = new DeleteItemCartService();
			service.execute(request, response);
			viewPage = "MyCart.do";
		} else if (command.equals("/DeleteWholeCart.do")) {	  
			service = new DeleteWholeCartService();
			service.execute(request, response);
			viewPage = "MyCart.do";
		}
		
		
		 //
		 // INDEX ORDER
		 //
		  else if (command.equals("/OrderDetailView.do")) {	  // ?????? ???????????? 
			service = new MyCartService();
			service.execute(request, response);
			viewPage = "orderList/orderDetailView.jsp";
		} else if (command.equals("/AddOrderList.do")) {	  // ?????? ?????? ???????????? 
			service = new AddOrderListService();
			service.execute(request, response);
			viewPage = "OrderDetail.do";
		} else if (command.equals("/OrderDetail.do")) {	  // ?????? ?????? ?????? ????????????
			service = new OrderDetailService();
			service.execute(request, response);
			viewPage = "DeleteWholeCart.do";
		} else if (command.equals("/MyOrder.do")) {	  
			service = new MyOrderService();
			service.execute(request, response);
			viewPage = "orderList/orderMypage.jsp";
		} else if (command.equals("/OrderList.do")) {	  
			service = new OrderListService();
			service.execute(request, response);
			viewPage = "orderList/AdminOrderList.jsp";
		} else if (command.equals("/OrderList1.do")) {	  
			service = new OrderListService();
			service.execute(request, response);
			viewPage = "OrderList.do";
		} else if (command.equals("/AdminOrderDetail.do")) {	  
			service = new AdminOrderDetailService();
			service.execute(request, response);
			viewPage = "orderList/AdminOrderDetail.jsp";
		} else if (command.equals("/OrderSuccess.do")) {	  
			service = new OrderSuccessService(); 
			service.execute(request, response);  
			viewPage = "OrderList1.do";
		} 
		
	
		;
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}





















