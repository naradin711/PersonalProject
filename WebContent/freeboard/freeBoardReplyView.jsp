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
<title>고 객 센 터 답 글 쓰 기</title>
<link href="${conPath }/css/freeboard1.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	   <form action="${conPath }/FreeBoardReply.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name = "fbid" value="${param.fbid }">
		<input type="hidden" name = "pageNum" value="${param.pageNum }"> 	 
	 	<input type="hidden" name = "fbgroup" value="${FreeBoardView.fbgroup }">	 
	 	<table>
				<caption>고 객 센 터 답 글 쓰 기</caption>
				<tr> <th>id</th> <td><input type="text" name="cid" value="${admin.aid }" readonly="readonly"></td> </tr>
 				<tr> <th>관리자 이름</th> <td><input type="text" name="aname" value="${admin.aname }" readonly="readonly"></td> </tr>
				<tr> <th>글 제목</th> <td><input type="text" name="fbtitle" required="required" value="${param.fbid }번 답글)"></td> </tr>
				<tr> 
					<td colspan="2"><textarea rows="5" cols="20" name="fbcontent">${FreeBoardView.fbtitle }</textarea></td> 
				</tr>
				<tr> 
					<th>사진 첨부</th> 
					<td><input type="file" name="fbphoto" ></td>  
				</tr>
					<tr> <th>글 비밀번호</th> <td><input type="text" name="fbpw" required="required"></td> </tr>
				<tr> 
						<td colspan="2">
							<input type="submit" value="답글 등록" class="btn">
							<input type="reset" value="초기화" class="btn">
							<input type="button" value="글 목록" class="btn" onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">
						</td>
				</tr>		
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>