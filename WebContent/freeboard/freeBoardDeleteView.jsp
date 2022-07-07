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
<title>상품 정보 수정</title>
<link href="${conPath }/css/freeboard.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<script type="text/javascript">
		$(document).ready(function () {
			 
		});
</script>
</head>
<body>
	<div id="content_form">
	<form action="${conPath }/FreeBoardDelete.do" method="post">
			 <input type="hidden" name = "fbid" value="${param.fbid }">
			 <input type="hidden" name = "pageNum" value="${param.pageNum }">
			 
			 <table>
				<caption>고객 센터 글 삭제</caption>
				<c:if test="${ empty admin }">
				 <tr> 
				 	<th>글 삭제를 위해 비밀 번호를 입력하세요.</th> 
				 	<td><input type="text" name="fbpw"  required="required"></td> 
				 </tr>
				 </c:if>
				 <c:if test="${not empty admin }">
				 	<tr> 
				 		<th>글 삭제를 위해 비밀 번호를 입력하세요.</th> 
				 		<td><input type="text" name="fbpw"  value="${FreeBoardView.fbpw }" required="required"></td> 
				 	</tr>
				 </c:if>
				 
				<tr> 
						<td colspan="2">
						<input type="submit" value="글 삭제" class="btn"> 
						<input type="button" value="글 목록" class="btn" onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum}'">
					</td>
				</tr>		
			</table>
		</form>
	</div>
</body>
</html>