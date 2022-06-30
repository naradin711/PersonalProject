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
<title>물품 정보 보기</title>
<link href="${conPath }/css/stylep3.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<div id="content_form">
	<table>
		<caption>물품 정보 상세보기</caption>
		<tr> <th>상품명</th> <td>${AdminProductView.pname }</td> </tr>
		<tr> <th>상품타입</th> <td>${AdminProductView.ptype }</td> </tr>
		<tr> <th>상품설명</th> <td><pre>${AdminProductView.pcontent }</pre></td> </tr>
		<tr> 
			<th>상품사진</th>
			<td>
				<img alt="productImg" src="${conPath }/productFileUp/${AdminProductView.pphoto}">
			</td>
		</tr>
		<tr> <th>상품가격</th><td> ${AdminProductView.pprice }</td></tr>
		<tr> <th>조회수</th><td> ${AdminProductView.phit } </td></tr>
		<tr> 
			<th>작성일</th> 
			<td><fmt:formatDate value="${AdminProductView.prdate }" pattern="yy년 MM월dd일"/></td>
		</tr>
		 
		<tr>
		<td colspan="2">
			<button onclick="location='${conPath }/ProductModifyView.do?pid=${param.pid}&pageNum=${param.pageNum}'">상품 정보 수정</button>
			<button onclick="location='${conPath }/ProductDelete.do?pid=${param.pid}&pageNum=${param.pageNum}'">상품 삭제</button>
			<button onclick="location='${conPath }/ProductList.do?pageNum=${param.pageNum}'">목록</button>
			 
			</td>
		</tr>
	</table>
	</div>
</body>
</html>