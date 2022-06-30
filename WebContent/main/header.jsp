<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <c:set var="conPath" value="${pageContext.request.contextPath }"  /> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Header</title>
	<link href="${conPath}/css/header1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function () {
			  
		});
	</script>
</head>
<body> <!-- gnb는 로그인/전후 다르다 -->
	<header>
		<div class="gnb">
		<c:if test="${empty customer && empty admin}">
			<!-- 로그인 전 메뉴 우측부터 고객 센터 회원가입 로그인 -->
			<ul>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>	
		</c:if>	
		<c:if test="${ customer != null }">
			<!-- 로그인 후 메뉴 우측부터 고객 센터 로그아웃 정보 수정, 고객이름 -->
			<ul>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/mypage.do">My Page</a></li>
				<li><a href="#">${customer.cname }님 반갑습니다.</a></li>
			</ul>	
		</c:if>
		<c:if test="${ admin != null }">
			<ul>
				<li><a href="${conPath }/CustomerList.do">고객 목록</a></li>
				<li><a href="${conPath }/ProductList.do">상품 목록</a></li>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/logout.do">관리자 모드 나가기</a></li>
				<li><a href="#">${admin.aname }님 </a></li>
			</ul>		
		</c:if>
		</div>
		<div class="logo" onclick="location.href='${conPath }/main/main.jsp'">
			<img alt="로고이미지" src="${conPath}/img/logo.png">
		</div>
		<div class="lnb">
			<!-- 우측부터 메뉴 : 도서보기, 도서등록(로그인 이후) -->
			<ul>
					
					<li><a href="${conPath }/BestSellerView.do">BestSeller</a></li>
			 		<li><a href="${conPath }/WhatsNewView.do">What's New</a></li>		
					<li>
						<a href="${conPath }/DressView.do">Product</a>
						<ul class="hidden">
							<li><a href='${conPath }/DressView.do'>DRESS</a></li>
							<li><a href='${conPath }/AccView.do'>Accessories</a></li>	
							<li><a href='${conPath }/TopView.do'>TOPS</a></li>
							<li><a href='${conPath }/BotView.do'>BOTTOMS</a></li>
						</ul>
					</li>	
					<li>
						<a href='${conPath }/main/aboutUs.jsp'>About Us</a>
						<ul class="hidden">
							<li><a href='${conPath }/main/aboutUs.jsp'>Brand Story</a></li>
							<li><a href='${conPath }/main/contact.jsp'>Contact Us</a></li>
						</ul>
					</li>		
			</ul>
		</div>
	</header>
</body>
</html>









