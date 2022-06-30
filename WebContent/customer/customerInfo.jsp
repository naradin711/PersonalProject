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
<title>Insert title here</title>
<link href="${conPath }/css/mypage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<table>
		<caption>회원 정보 상세보기</caption>
		<tr> <th>아이디</th> <td>${customer.cid }</td> </tr>
		<tr> <th>이름</th> <td>${customer.cname }</td> </tr>
		<tr> <th>이메일</th> <td>${customer.cemail }</td> </tr>
		<tr> <th>전화번호</th><td><pre>${customer.ctel }</pre></td></tr>
		<tr> <th>주소</th><td><pre>${customer.caddress }</pre></td></tr>
		<tr> 
			<th>생일</th> 
			<td><fmt:formatDate value="${customer.cbirth }" pattern="yy년 MM월dd일"/></td>
		</tr>
		<tr> 
			<th>작성일</th> 
			<td><fmt:formatDate value="${customer.crdate }" pattern="yy년 MM월dd일"/></td>
		</tr>
		 
		<tr>
			<td colspan="2">
			<button onclick="location='${conPath }/CustomerModifyView.do'">정보 수정</button>
			<button onclick="location='${conPath }/DeleteCustomer.do'">회원 탈퇴</button>
			 
			</td>
		</tr>
	</table>
</body>
</html>