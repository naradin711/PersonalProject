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
<title>주 문 상 세 보 기</title>
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
	<form action="${conPath }/AddOrderList.do" method="post">
			 <input type="hidden" name="cid" value="${customer.cid }">
			  <input type="hidden" name="odtitle" value="${customer.cid }님의 주문 ">  
			 <table>
				<caption>주 문 상 세</caption> 
				<c:if test="${list.size() eq 0 }">
					<tr><th>등록된 상품이 없습니다.</th></tr>
				</c:if>
			<c:if test="${list.size() != 0 }"> 
		 	<tr> <th>장바구니 번호</th> <th>물품명</th> <th>가격</th> <th>사진</th></tr>
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
		   			</tr>		
			</c:forEach>
			<tr>
				<td colspan="4">
					<c:set var = "total" value = "0" />
						<c:forEach var="dto" items="${list }">	
							<c:set var= "total" value="${total + dto.pprice}"/>	
						</c:forEach>
					구매 합계 :  <c:out value="${total}"/>원
					<input type="hidden" name="odprice" value="${total}"> 
				</td>
			</tr>
			<tr>
				<td colspan="4">
					배송지 : <input type="text" name="odaddress" value="${customer.caddress }"> 
				</td>
			</tr>
			</c:if>
				<tr> 
						<td colspan="4">
							<input type="submit" value="주문하기" class="btn">
							<input type="reset" value="초기화" class="btn">
							<input type="button" value="돌아가기" class="btn" onclick="location='${conPath}/MyCart.do?cid=${customer.cid }'">
						</td>
				</tr>		
			</table>
		</form>
		</div>
		<jsp:include page="../main/footer.jsp"/>
</body>
</html>