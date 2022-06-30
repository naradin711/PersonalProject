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
<title>BestSeller</title>
<link href="${conPath }/css/stylep3.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<c:set var="SUCCESS" value="1"/>
		<c:set var="FAIL" value="0"/>
	<table>
		<c:if test="${list.size() eq 0 }">
			<tr><th>등록된 상품이 없습니다</th></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
			<tr>
			<c:set var="i" value="0"/>
			<c:forEach var="dto" items="${list }" >
				<td colspan="2">
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					<img alt="productImg" src="${conPath}/productFileUp/${dto.pphoto }" width="300" width="450">
					</a>
					<br><br> 
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					${dto.pname }
					</a>
					&nbsp;&nbsp;
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					 ${dto.pprice }원
					</a>
				</td>
				<c:if test="${i%3==2 }">
					</tr> <tr>
				</c:if>
				<c:set var="i" value="${i+1 }"/>	
			</c:forEach>
			 
		</c:if>
	</table>
			<div class="paging">
				<a href="${conPath }/DressView.do?pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/DressView.do?pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/DressView.do?pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/DressView.do?pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/DressView.do?pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>