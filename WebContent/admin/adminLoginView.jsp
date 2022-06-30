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
<title>관리자 로그인</title>
<link href="${conPath }/css/style2.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
  <br> 
  <form action="${conPath }/adminLogin.do" method="post" id="content_form1">
	<table>
		<caption>관리자 로그인 </caption>
		<tr>
			<th>ID</th>
			<td><input type="text" name=aid required="required"></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" name=apw required="required"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인" class="btn">
			</td>
		</tr>
	</table>
	</form>	
  <jsp:include page="../main/footer.jsp"/>
</body>
</html>

