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
<title>리 뷰 삭 제 하 기</title>
<link href="${conPath }/css/productview1.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<div id="content_form">
	<form action="${conPath }/ReviewDelete.do?rbid=${ReviewView.rbid}&pid=${param.pid }&pageNum=${param.pageNum}" method="post">
			 <input type="hidden" name = "rbid" value="${param.rbid }">
			 <input type="hidden" name = "pid" value="${param.pid }">
			 <input type="hidden" name = "pageNum" value="${param.pageNum }">
			 
			 <table>
				<caption>리뷰 삭제</caption>
				<c:if test="${ empty admin }">
				 <tr> 
				 	<th>리뷰 삭제를 위해 비밀 번호를 입력하세요.</th> 
				 	<td><input type="password" name="rbpw"  required="required"></td> 
				 </tr>
				 </c:if>
				 <c:if test="${not empty admin }">
				 	<tr> 
				 		<th>리뷰 삭제를 위해 비밀 번호를 입력하세요.</th> 
				 		<td><input type="text" name="rbpw"  value="${ReviewView.rbpw }" required="required"></td> 
				 	</tr>
				 </c:if>
				 
				<tr> 
						<td colspan="2">
						<input type="submit" value="글 삭제" class="btn"> 
						<input type="button" value="글 목록" class="btn" onclick="location='${conPath}/CustomerProductView.do?pid=${param.pid }&pageNum=${param.pageNum}'">
					</td>
				</tr>		
			</table>
		</form>
	</div>
</body>
</html>