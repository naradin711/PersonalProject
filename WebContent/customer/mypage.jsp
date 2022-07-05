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
<title>My Page</title>
<link href="${conPath }/css/mypage.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<c:if test="${not empty CustomerModifyResult }">
		<script type="text/javascript">
			alert('회원 정보 수정 성공');
		</script>
	</c:if>
	<c:if test="${not empty CustomerModifyErrorMsg }">
		<script type="text/javascript">
			alert('회원 정보 수정 실패');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<br>
	<h1>My Page</h1>
		<div id="side_banner">
			<table>
				<tr>
					<td><a href="${conPath }/mypage.do?cid=${customer.cid }">상세 정보</a></td>
				</tr>
				<tr>
					<td>
						<a href="${conPath }/MyCart.do?cid=${customer.cid }">장바구니 목록</a>
					</td>
				</tr>
				<tr>
					<td><a href="${conPath }/MyOrder.do?cid=${customer.cid }">구매내역</a></td>
				</tr>
			</table>
		</div>
		<div id="main_banner">
			<jsp:include page="../customer/customerInfo.jsp"/>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>