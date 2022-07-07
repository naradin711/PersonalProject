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
<link href="${conPath }/css/mypage1.css " rel="stylesheet" type="text/css">
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
	<c:if test="${not empty DeleteCartOneResult }">
		<script type="text/javascript">
			alert('장바구니 물품 삭제 성공');
		</script>
	</c:if>
	<c:if test="${not empty DeleteCartOneError }">
		<script type="text/javascript">
			alert('장바구니 물품 삭제 실패');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty DeleteCartWholeResult }">
		<script type="text/javascript">
			alert('장바구니 전체 삭제 성공');
		</script>
	</c:if>
	<c:if test="${not empty DeleteCartWholeError }">
		<script type="text/javascript">
			alert('장바구니 전체 삭제 실패');
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
			<input type="hidden" name="cid" value="${dto.cid }">
			<table>
				<caption>내 장바구니 목록 </caption>
				<c:if test="${list.size() eq 0 }">
					<tr><th>등록된 상품이 없습니다.</th></tr>
				</c:if>
			<c:if test="${list.size() != 0 }">
		 	<tr> <th>장바구니 번호</th> <th>물품명</th> <th>가격</th> <th colspan="2">사진</th></tr>
				<c:forEach var="dto" items="${list }">	
					<tr>	
						<td> 
							${dto.cartid } 
						</td>
						<td>
							${dto.pname }
							
						</td>
						<td> 
							${dto.pprice }원
						</td>
						<td>
							<img alt="productImg" src="${conPath }/productFileUp/${dto.pphoto}" width="120">
					 	</td>
					 	<td>
							<input type="button" value="삭제" 
							onclick="location='${conPath }/DeleteItemCart.do?cartid=${dto.cartid }&cid=${customer.cid }'"
							class="myPageBtn_style">
					 	</td>
		   			</tr>		
			</c:forEach>
			<tr>
				<td colspan="5">
					<c:set var = "total" value = "0" />
						<c:forEach var="dto" items="${list }">	
							<c:set var= "total" value="${total + dto.pprice}"/>	
						</c:forEach>
					구매 합계 :  <c:out value="${total}"/>원
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<button onclick="location='${conPath }/OrderDetailView.do?cid=${customer.cid }'" 
					class="myPageBtn_style">주문하기</button>
					<button onclick="location='${conPath }/DeleteWholeCart.do?cid=${customer.cid }'"
					class="myPageBtn_style">전체 삭제</button> 
				</td>
			</tr>
			
		</c:if>
		
		
	</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>