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
	<br>
	<h1>Main</h1>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>















