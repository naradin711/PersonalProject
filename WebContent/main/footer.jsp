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
<link href="${conPath}/css/footer1.css" rel="stylesheet">
</head>
<body>
	<footer>
		<div class="office_logo">
			<img alt="로고이미지" src="${conPath}/img/logo.png">
		</div>
		<div class="office_address">
			<ul>
				<li>러블시 리즈구 지수동 211 초코로켓빌딩 8F | TEL : 02-094-0211 | FAX : 02-994-0211</li>
				<li>사업자등록번호 : 000-12-00000 | 통신판매업신로 : 강남 제0000호 | 대표이사 : 서지수</li>
				<li>조은시스템 문의 메일 : webmaster@mula.com <a href='${conPath }/adminLoginView.do'>관리자모드</a> </li>
			</ul>
		</div>
		<div class="copyright">
			ⓒ2022 YOUVELY.Co.,Ltd. All Rights Reserved.
		</div>
	</footer>
</body>
</html>
