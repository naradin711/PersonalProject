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
			<input type="hidden" name="cid" value="${dto.cid }">
			<table>
				<caption>내 구매 목록 </caption>
				<c:if test="${list.size() eq 0 }">
					<tr><th colspan="3"> 주문 대기 중인 상품이 없습니다.</th></tr>
				</c:if>
			<c:if test="${list.size() != 0 }">
		 	<tr> <th>장바구니 번호</th> <th>물품명</th> <th>가격</th></tr>
				<c:forEach var="dto" items="${list }">	
					<tr>	
						<td> 
							${dto.oddid }
						</td>
						<td>
							${dto.pname }
						</td>
						<td> 
							${dto.pprice }원
						</td>
		   			</tr>		
			</c:forEach>
			<tr>
				<td colspan="3">
					<c:set var = "total" value = "0" />
						<c:forEach var="dto" items="${list }">	
							<c:set var= "total" value="${total + dto.pprice}"/>	
						</c:forEach>
					구매 합계 :  <c:out value="${total}"/>원
				</td>
			</tr> 
			<tr>
				<td colspan="3">
				<div class="paging">
				<a href="${conPath }/MyOrder.do?pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/MyOrder.do?pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/MyOrder.do?pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/MyOrder.do?pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/MyOrder.do?pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 
				</td>
			</tr>
		</c:if> 
		
	</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>