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
<title>고객 센터 글 상세보기</title>
<link href="${conPath }/css/freeboard.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>  
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	 	<div id="product_image">
			<img alt="${FreeBoardView.fbphoto }" 
				 src="${conPath }/freeboardFileUp/${FreeBoardView.fbphoto}" 
				 width="400"> 
		</div>
		<div id="product_info">
			<table>
				<tr>
					<td colspan="2">${FreeBoardView.fbtitle }</td>	
				</tr>
				<tr>
					<td colspan="2"><pre>${FreeBoardView.fbcontent }</pre></td>	
				</tr>
				<tr>
					<th>작성일자</th>
					<td>
						<fmt:formatDate value="${FreeBoardView.fbrdate }" pattern="yy년 MM월 dd일"/>
					</td>	
				</tr>
				<tr>
					<th>작성 IP</th>
					<td>${FreeBoardView.fbip }</td>	
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="글 수정하기" onclick="location='${conPath }/FreeBoardModifyView.do?fbid=${FreeBoardView.fbid}&pageNum=${param.pageNum}'">
						<input type="button" value="글 삭제하기" onclick="location='${conPath }/FreeBoardDeleteView.do?fbid=${FreeBoardView.fbid}'">
						
						<c:if test="${not empty admin }">
						<input type="button" value="답글 달기" onclick="location='${conPath }/FreeBoardReplyView.do?fbid=${FreeBoardView.fbid}&pageNum=${param.pageNum}'">
						</c:if>
					</td>	
				</tr>
			</table> 
		</div> 
	</div>
	<jsp:include page="../main/footer.jsp"/>
 	
</body>
</html>