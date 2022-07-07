<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <c:set var="conPath" value="${pageContext.request.contextPath }"  />    
<!DOCTYPE html>
<c:if test="${ empty customer && empty admin }">
		<script type="text/javascript">
			
			location.href='${conPath}/loginView.do'
			alert('로그인 후 이용해 주세요!');	
		</script>  
	</c:if>
<html>
<head>
<meta charset="UTF-8">
<title>고객게시판</title>
<link href="${conPath }/css/freeboard.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body> 
	<c:if test="${not empty FreeBoardWriteResult }">
		<script type="text/javascript">
			alert('고객 센터 글 작성에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardWriteError }">
		<script type="text/javascript">
			alert('고객 센터 글 작성에 실패하셨습니다.');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardModifyResult }">
		<script type="text/javascript">
			alert('고객 센터 글 수정에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardModifyError }">
		<script type="text/javascript">
			alert('고객 센터 글 수정에 실패하셨습니다.');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardDeleteResult }">
		<script type="text/javascript">
			alert('글 삭제에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardDeleteError }">
		<script type="text/javascript">
			alert('글 삭제에 실패하셨습니다.');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty  FreeBoardReplyResult }">
		<script type="text/javascript">
			alert('답글 작성에 성공하셨습니다.');
		</script>
	</c:if>
	<c:if test="${not empty FreeBoardReplyError }">
		<script type="text/javascript">
			alert('답글 작성에 실패하셨습니다.');
			history.back();
		</script>
	</c:if> 
	<jsp:include page="../main/header.jsp"/>
	<br><br>
	<div id="content_form">
		<table>
			<caption>고 객 센 터 게 시 판</caption> 
			<tr>
				<th> 글 번호 </th> <th> ID </th> <th> 글 제목 </th> <th>사진 </th> <th> 등록일자 </th>
			</tr>
		<c:if test="${list.size() eq 0 }">
			<tr><th>등록된 회원이 없습니다</th></tr>
		</c:if>
		<c:if test="${list.size() != 0 }">
		 
			<c:forEach var="dto" items="${list }">	
			<tr>	
				<td> 
					${dto.fbid } 
				</td>
				<td> 
					${dto.cid } 
				</td>
				<td class="left">
					<c:forEach var="i" begin="1" end="${dto.fbindent }">
						<c:if test="${i eq dto.fbindent }">
							ㄴ
						</c:if>
						<c:if test="${i != dto.fbindent }">
							&nbsp; &nbsp;
						</c:if>
					</c:forEach>
					<a href="${conPath }/FreeBoardView.do?fbid=${dto.fbid}&pageNum=${pageNum}">
						${dto.fbtitle }
					</a> 
				</td>
				<td>
					<a href="${conPath }/FreeBoardView.do?fbid=${dto.fbid}&pageNum=${pageNum}">
						<img alt="productImg" src="${conPath }/freeboardFileUp/${dto.fbphoto}" width="75">
					</a>			
				</td> 
				<td>
					<fmt:formatDate value="${dto.fbrdate }" pattern="yy-MM-dd"/>
				</td>
		    </tr>		
			</c:forEach>
			
		</c:if>
		<tr>
			<td colspan="5">
				<input type="button" value="글 쓰기" onclick="location ='${conPath}/freeboard/freeBoardWriteView.jsp'">
			</td>
		</tr>
	</table>
			<div class="paging">
				<a href="${conPath }/boardList.do?pageNum=1">◀◀</a>
					&nbsp; &nbsp; &nbsp;
				<c:if test="${startPage > BLOCKSIZE }" >
					<a href="${conPath }/boardList.do?pageNum=${startPage-1 }">◀</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						[<b> ${i } </b>]
					</c:if>
					<c:if test="${i != pageNum }">
						[<a href="${conPath }/boardList.do?pageNum=${i}"> ${i } </a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/boardList.do?pageNum=${endPage + 1}"> ▶ </a>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<a href="${conPath }/boardList.do?pageNum=${pageCnt}">▶▶</a>
				<br> 
			</div> 
	</div>
	<br><br><br>
	<jsp:include page="../main/footer.jsp"/>
	<br><br>
</body>
</html>