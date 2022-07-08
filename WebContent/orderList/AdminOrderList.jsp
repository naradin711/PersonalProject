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
<title>주 문 목 록</title>
<link href="${conPath }/css/stylep1.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<c:if test="${not empty OrderSuccessResult }">
		<script type="text/javascript">
		    location.href ="${conPath}/OrderList.do"
			alert('주문 처리에 성공하셨습니다.');
			
		</script>
	</c:if>
	<c:if test="${not empty OrderSuccessError }">
		<script type="text/javascript">
			alert('주문 처리에 실패하셨습니다.');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<c:set var="SUCCESS" value="1"/>
		<c:set var="FAIL" value="0"/>
	<table>
	<caption>주문 목록</caption>
		<tr>
			<th>주문 ID </th> <th> 주문자 ID </th> <th> 주문명 </th> <th> 배송지 </th><th> 주문 가격 </th> <th> 주문 일자 </th>
		</tr>
		<c:if test="${list.size() eq 0 }">
			<tr><th>주문이 없습니다</th></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
		 	
			<c:forEach var="dto" items="${list }">	
			<tr>	
				<td> 
					${dto.odid } 
				</td>
				<td> 
					${dto.cid } 
				</td>
				<td>
					<a href="${conPath }/AdminOrderDetail.do?cid=${dto.cid }&odid=${dto.odid }&odaddress=${dto.odaddress } ">
						${dto.odtitle }
					</a> 
				</td> 
				<td> 
					${dto.odaddress } 
				</td> 
				<td> 
					${dto.odprice } 
				</td>
				<td>
					<fmt:formatDate value="${dto.oddate }" pattern="yy년 MM월dd일"/>
				</td>
		    </tr>		
			</c:forEach>
			
		</c:if> 
	</table>
			<div class="paging">
				<a href="${conPath }/OrderList.do?pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/OrderList.do?pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/OrderList.do?pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/OrderList.do?pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/OrderList.do?pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>