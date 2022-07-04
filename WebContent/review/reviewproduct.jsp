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
<title></title>
<link href="${conPath }/css/productview.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
<div>
	<input type="hidden" name="pid" value="${CustomerProductView.pid }">
	<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<c:if test="${Reviewlist.size() eq 0 }">
				<tr><th>등록된 리뷰가 없습니다</th></tr>
				</c:if>
				<c:if test="${Reviewlist.size() != 0 }">
				<tr>
					<th>리뷰 번호</th><th>사용자 아이디</th><th>리뷰 제목</th>
				</tr>
					<c:forEach var="dto" items="${Reviewlist }">
						<tr>
						
							<td>${dto.rbid }</td>
							<td>${dto.cid }</td>
							<td>
								<a href="${conPath }/ReviewContent.do?rbid=${dto.rbid}&pid=${CustomerProductView.pid }&pageNum=${pageNum}">
						 				${dto.rbtitle }
							    </a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table> 
			<div class="paging">
				<a href="${conPath }/CustomerProductView.do?pid=${CustomerProductView.pid }&pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/CustomerProductView.do?pid=${CustomerProductView.pid }&pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/CustomerProductView.do?pid=${CustomerProductView.pid }&pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/CustomerProductView.do?pid=${CustomerProductView.pid }&pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/CustomerProductView.do?pid=${CustomerProductView.pid }&pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 	
</div>			
</body>
</html>