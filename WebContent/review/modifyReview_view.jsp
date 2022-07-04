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
<title>리뷰 수정하기</title>
<link href="${conPath }/css/style2.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form" >
		<form action="${conPath }/ReviewModify.do?pid=${param.pid }" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="rbid" value="${ReviewView.rbid }">
			 <input type="hidden" name="pid" value="${param.pid }">
			 <input type="hidden" name="pageNum" value="${param.pageNum }">
		
			 <table>
				<caption>리 뷰 수 정 하 기</caption>
				<tr> <th>작성자</th> <td><input type="text" name="cid" required="required" value="${customer.cid }" readonly="readonly"></td> </tr>
				<tr> <th>리뷰제목</th> <td><input type="text" name="rbtitle" required="required" value="${ReviewView.rbtitle }"></td> </tr>
				<tr> 
					<th>상품설명</th> 
					<td><textarea rows="10" cols="30" name="rbcontent">${ReviewView.rbcontent }</textarea></td> 
				</tr>
				<tr> 
					<th>상품사진</th> 
					<td><input type="file" name="rbphoto" ></td>  
				</tr>
				<tr> <th>리뷰 비밀번호</th> <td><input type="text" name="rbpw" required="required"></td> </tr>
				
				<tr> 
					<td colspan="2">
						<input type="submit" value="리뷰 등록" class="btn">
						<input type="reset" value="초기화" class="btn">
						<input type="button" value="상품 목록" class="btn" onclick="location='${conPath}/CustomerProductView.do?pid=$${CustomerProductView.pid }'">
					</td>
				</tr>		
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>