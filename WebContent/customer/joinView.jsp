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
<title>회원가입</title>
 
<link href="${conPath }/css/loginpp.css " rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			$('#cid').keyup(function() {
				var cid = $('#cid').val();
				$.ajax({
					url : '${conPath}/idConfirm.do',
					data : 'cid='+cid,
					type : 'get',
					dataType : 'html',
					success : function(data) {
						$('#idConfirmResult').html(data);
					}//success
				});//ajax
			});//keyup
			$('#cpw, #pwChk').keyup(function() {
				var cpw 	= $('#cpw').val();
				var pwChk 	= $('#pwChk').val();
				if (cpw == pwChk){
					$('#pwChkResult').text('비밀번호 일치');
				} else {
					$('#pwChkResult').text('비밀번호 불일치');
				}
			});//keyup checkpw
			$('form').submit(function() {
				var idConfirmResult = $('#idConfirmResult').text().trim();
				var pwChkResult = $('#pwChkResult').text().trim();
				if(idConfirmResult!= '사용 가능한  ID입니다.' ){
					alert('사용가능한 ID를 입력하세요.');
					$('#idConfirmResult').focus();
					return false;
				} else if (pwChkResult!='비밀번호 일치')  {
					alert('올바른 비밀번호를 입력하세요.');
					$('#pwChkResult').focus();
					return false;
				}
			});
			$('#cemail').keyup(function(){
				var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
				var cemail = $('#cemail').val();
				if(patternMail.test(cemail)){
					$.ajax({
						url : '${conPath}/emailConfirm.do',
						type : 'get',
						dataType : 'html',
						data : "cemail="+cemail,
						success : function(data){
							$('#emailConfirmResult').html(data);
						}
					});//ajax
				}else if(!cemail){
					$('#emailConfirmResult').html(' &nbsp; ');
				}else{
					$('#emailConfirmResult').html('메일 형식을 지켜주세요');
				}//if
			});// cEmail keyup 이벤트
			$('#ctel').keyup(function(){
				var patternTel = /^\d{3}-\d{3,4}-\d{4}$/; // 휴대폰 패턴
				var ctel = $('#ctel').val();
				if(patternTel.test(ctel)){
					$.ajax({
						url : '${conPath}/telConfirm.do',
						type : 'get',
						dataType : 'html',
						data : "ctel="+ctel,
						success : function(data){
							$('#telConfirmResult').html(data);
						}
					});//ajax
				}else if(!ctel){
					$('#telConfirmResult').html(' &nbsp; ');
				}else{
					$('#telConfirmResult').html('휴대폰 번호 형식을 지켜주세요');
				}//if
			});
		});
</script>
</head>
<body>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat : 'yy-mm-dd',
    	changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
    	monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	showMonthAfterYear : true,
    	yearSuffix : '년', // "2020년 3월"
    	showOtherMonths : true,
    	dayNamesMin:['일','월','화','수','목','금','토'],
			changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
			minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
			yearRange : 'c-100:c+0', // 년도 선택 셀렉트 
    });
  } );
  </script>
	<jsp:include page="../main/header.jsp"/>
	 
		<form id="loginForm_wrap" action="${conPath }/join.do" method="post">
			<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="cid" id="cid" required="required">
					<div id="idConfirmResult"> &nbsp; </div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="cpw" id="cpw" required="required">
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="pwChk" id="pwChk" required="required">
					<div id="pwChkResult"> &nbsp; </div>		 
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
				 	<input type="text" name="cname" id="cname" required="required">
				</td>
			</tr>
			<tr>
				<th> 이메일 </th>
				<td>
					<input type="email" name="cemail" id="cemail" required="required">
					<div id="emailConfirmResult"> &nbsp; </div>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
				 	<input type="text" name="ctel" id="ctel" required="required">
				 	<div id="telConfirmResult"> &nbsp; </div>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="caddress" id="caddress">
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					<input type="text" name="cbirth" id="datepicker"><br>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입"  class="loginBtn_style">
					<input type="button" value="로그인"  class="loginBtn_style" onclick="location='${conPath}/loginView.do'">
					<input type="reset" value="가입취소"  class="loginBtn_style">
				</td>
			</tr>
		</table>
		</form>
	 
	<jsp:include page="../main/footer.jsp"/>	
</body>
</html>