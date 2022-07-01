<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <c:set var="conPath" value="${pageContext.request.contextPath }"  />    
<!DOCTYPE html>
	<c:if test="${ empty customer && empty admin }">
		<script type="text/javascript">
			
			location.href='${conPath}/loginView.do'
			alert('로그인 후 이용해 주세요!');	
		</script>  
	</c:if>
	
	<c:if test="${ not empty customer || not empty admin }"> 
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<link href="${conPath }/css/productview.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
		
</script> 
</head>
<body>
	
		 
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<input type="hidden" name="pid" value="${CustomerProductView.pid }">
		<div id="product_image">
			<img alt="${CustomerProductView.pphoto }" 
				 src="${conPath }/productFileUp/${CustomerProductView.pphoto}" 
				 width="520"> 
		</div>
		<div id="product_info">
			<table>
				<tr>
					<th>상품명</th>
					<td>${CustomerProductView.pname }</td>	
				</tr>
				<tr>
					<td colspan="2">${CustomerProductView.pcontent }</td>	
				</tr>
				<tr>
					<th>가격</th>
					<td>${CustomerProductView.pprice }원</td>	
				</tr>
			</table>
			<a href="${conPath }/addCart.do">
				<img alt="addCart" src="${conPath }/productFileUp/cart_in.png" width="300">
			</a>
			 
		</div>
		<div id="review">
			 
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
 	
</body>
</html>
</c:if> 