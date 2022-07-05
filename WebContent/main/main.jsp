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
	<title>Main</title>
	<link href="${conPath }/css/stylep3.css " rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<c:if test="${not empty LoginResult }">
		<script type="text/javascript">
			alert('로그인 성공!');
		</script>
	</c:if>
	<c:if test="${not empty LoginError }">
		<script type="text/javascript">
			location.href='${conPath}/loginView.do'
			alert('${LoginError}');
		</script>
	</c:if>
	<c:if test="${not empty ModifyResult }">
		<script type="text/javascript">
			alert('회원 정보 수정 성공');
		</script>
	</c:if>
	<c:if test="${not empty ModifyErrorMsg }">
		<script type="text/javascript">
			location.href='${conPath}/CustomerModifyView.do'
			alert('회원 정보 수정 실패');
		</script>
	</c:if>
	<c:if test="${not empty AdminLoginResult }">
		<script type="text/javascript">
			alert('관리자 로그인에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty AdminLoginError }">
		<script type="text/javascript">
			location.href='${conPath}/adminLoginView.do'
			alert('관리자 로그인에 실패하셨습니다.'); 
		</script>
	</c:if>
	<c:if test="${not empty DeleteCustomerResult }">
		<script type="text/javascript">
			alert('회원 탈퇴에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty DeleteCustomerError }">
		<script type="text/javascript">
			location.href='${conPath}/main.do'
			alert('회원 탈퇴에 실패하셨습니다.');
		</script>
	</c:if>
	
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<img alt="main_image" src="${conPath }/main/mainImage.png">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table>
		<c:if test="${list.size() eq 0 }">
			<tr><th>등록된 상품이 없습니다</th></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
			<tr>
			<c:set var="i" value="0"/>
			<c:forEach var="dto" items="${list }" >
				<td colspan="2">
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					<img alt="productImg" src="${conPath}/productFileUp/${dto.pphoto }" width="300" width="450">
					</a>
					<br><br> 
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					${dto.pname }
					</a>
					&nbsp;&nbsp;
					<a href="${conPath }/CustomerProductView.do?pid=${dto.pid}&pageNum=${pageNum}">
					 ${dto.pprice }원
					</a>
				</td>
				<c:if test="${i%3==2 }">
					</tr> <tr>
				</c:if>
				<c:set var="i" value="${i+1 }"/>	
			</c:forEach>
			 
		</c:if>
	</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>















