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
<title>회 원 정 보 상 세 보 기</title>
<link href="${conPath }/css/style2.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<table>
		<caption>회원 정보 상세보기</caption>
		<tr> <th>아이디</th> <td>${Customer_view.cid }</td> </tr>
		<tr> <th>이름</th> <td>${Customer_view.cname }</td> </tr>
		<tr> <th>이메일</th> <td>${Customer_view.cemail }</td> </tr>
		<tr> <th>전화번호</th><td><pre>${Customer_view.ctel }</pre></td></tr>
		<tr> <th>주소</th><td><pre>${Customer_view.caddress }</pre></td></tr>
		<tr> 
			<th>생일</th> 
			<td><fmt:formatDate value="${Customer_view.cbirth }" pattern="yy년 MM월dd일"/></td>
		</tr>
		<tr> 
			<th>작성일</th> 
			<td><fmt:formatDate value="${Customer_view.crdate }" pattern="yy년 MM월dd일"/></td>
		</tr>
		 
		<tr>
		<td colspan="2">
			<button onclick="location='${conPath }/CustomerModify2View.do?cid=${param.cid}&pageNum=${param.pageNum}'">정보 수정</button>
			<button onclick="location='${conPath }/DeleteCustomer2.do?cid=${param.cid}&pageNum=${param.pageNum}'">회원 탈퇴</button>
			<c:if test="${ admin != null }">
			<button onclick="location='${conPath }/CustomerList.do?pageNum=${param.pageNum}'">목록</button>
			</c:if>
			</td>
		</tr>
	</table>
	
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>