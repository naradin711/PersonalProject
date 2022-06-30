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
<title>고객 목록</title>
<link href="${conPath }/css/stylep.css " rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<c:if test="${not empty CustomerModifyResult }">
		<script type="text/javascript">
			alert('회원 정보 수정 성공');
		</script>
	</c:if>
	<c:if test="${not empty CustomerModifyErrorMsg }">
		<script type="text/javascript">
			alert('회원 정보 수정 실패');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty DeleteCustomerResult }">
		<script type="text/javascript">
			alert('회원 탈퇴에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty DeleteCustomerError }">
		<script type="text/javascript">
			alert('회원 탈퇴에 실패하셨습니다.');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<c:set var="SUCCESS" value="1"/>
		<c:set var="FAIL" value="0"/>
	<table>
	<caption>회원 정보 보기</caption>
		<tr>
			<th>   ID </th> <th> 이름 </th>
		</tr>
		<c:if test="${list.size() eq 0 }">
			<tr><th>등록된 회원이 없습니다</th></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
		 
			<c:forEach var="dto" items="${list }">	
			<tr>	
				<td>
					<a href="${conPath }/CustomerView.do?cid=${dto.cid}&pageNum=${pageNum}">
					${dto.cid }
					</a>
				</td>
				<td> 
					${dto.cname } 
				</td>
		    </tr>		
			</c:forEach>
			
		</c:if>
		<tr>
			<td colspan="2">
				<input type="button" value="메인화면" onclick="location ='${conPath}/main.do'">
				<input type="button" value="로그아웃" onclick="location ='${conPath}/logout.do'">
			</td>
		</tr>
	</table>
			<div class="paging">
				<a href="${conPath }/CustomerList.do?pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/CustomerList.do?pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/CustomerList.do?pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/CustomerList.do?pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/CustomerList.do?pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>