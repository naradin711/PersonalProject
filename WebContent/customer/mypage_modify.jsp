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
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<br>
	<h1>My Page</h1>
		<div id="side_banner">
			<table>
				<tr>
					<td><a>상세 정보</a></td>
				</tr>
				<tr>
					<td><a>장바구니 목록</a></td>
				</tr>
				<tr>
					<td><a>구매내역</a></td>
				</tr>
			</table>
		</div>
		<div id="main_banner">
			<jsp:include page="../customer/customer_modify_info.jsp"/>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>