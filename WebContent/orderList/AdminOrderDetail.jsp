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
<title>구매 상세 내역</title>
<link href="${conPath }/css/stylep1.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">  
			<input type="hidden" name="cid" value="${param.cid }">
			<input type="hidden" name="odid" value="${param.odid }">
			<table>
				<caption>상세 구매 목록 </caption>
				<c:if test="${list.size() eq 0 }">
					<tr><th>구매하신 상품이 없습니다.</th></tr>
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
					<input type="button" value="주문처리" onclick="location='${conPath }/OrderSuccess.do?odid=${param.odid }'">
					<input type="button" value="뒤로가기" onclick= history.back(); >
				</td>
			</tr>
		</c:if>
		
		
	</table>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>