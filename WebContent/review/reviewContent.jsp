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
<title>리 뷰 상 세 보 기</title>
<link href="${conPath }/css/productview.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<input type="hidden" name="pid" value="${param.pid }">	 
	 	<div id="product_image">
			<img alt="${ReviewView.rbphoto }" 
				 src="${conPath }/reviewFileUp/${ReviewView.rbphoto}" 
				 width="520"> 
		</div>
		<div id="product_info">
			<table>
				<tr>
					<td colspan="2">${ReviewView.rbtitle }</td>	
				</tr>
				<tr>
					<td colspan="2"><pre>${ReviewView.rbcontent }</pre></td>	
				</tr>
				<tr>
					<th>작성일자</th>
					<td>
						<fmt:formatDate value="${ReviewView.rbrdate }" pattern="yy년 MM월 dd일"/>
					</td>	
				</tr>
				<tr>
					<th>작성 IP</th>
					<td>${ReviewView.rbip }</td>	
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="글 수정하기" onclick="location='${conPath }/ReviewModifyView.do?rbid=${ReviewView.rbid}&pid=${param.pid }&pageNum=${param.pageNum}'">
						<input type="button" value="글 삭제하기" onclick="location='${conPath }/ReviewDeleteView.do?rbid=${ReviewView.rbid}&pid=${param.pid }&pageNum=${param.pageNum}'">
					</td>	
				</tr>
			</table> 
		</div> 
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>