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
<style type="text/css">
	#content_form1 table {border: 2px solid white; width:500px; margin: 10px auto;}
	#content_form1 table tr { background-color: #FFCDCD; height:40px; }
	#content_form1 table tr:hover { background-color: #FF9F9F;
		cursor: pointer;
	}
	#content_form1 td, #content_form th {text-align: center; padding:5px; font-size: 15px; font-weight: bold;}
	#content_form1 caption {font-size: 20px; padding:10px; }
	#content_form1 h2, #content_form b{text-align: center; color:red;}
	#content_form1 a { text-decoration: none; color: #999999;}
	#content_form1 .left{text-align: left;}
	#content_form1 input:not(.btn), #content_form textarea {
		width: 100px;
	}
</style>
<link href="${conPath }/css/mypage.css " rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<script type="text/javascript">
		$(document).ready(function () {
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
	<div id="content_form1">
		<form action="${conPath}/CustomerModify.do" method="post">
			<table>
					<caption>개인 정보 수정하기</caption>
				<tr> 
					<th>아이디</th>
					<td><input type="text" name="cid" value="${customer.cid }" readonly="readonly"></td> 
				</tr>
				<tr> 
					<th>비밀번호</th><td><input type="password" name="cpw" size="5"  value="${customer.cpw }" required="required"></td>
				</tr>
				<tr> 
					<th>이름</th> 
					<td><input type="text" name="cname" value="${customer.cname }" readonly="readonly"></td> 
				</tr>
				<tr> 
					<th>이메일</th> 
					<td>
						<input type="text" name="cemail" id="cemail" value="${customer.cemail }">
						<div id="emailConfirmResult"> &nbsp; </div>
					</td> 			
				</tr>
				<tr> 
					<th>전화번호</th> 
					<td>
						<input type="text" name="ctel" id="ctel" value="${customer.ctel }">
						<div id="telConfirmResult"> &nbsp; </div>
					</td> 
				</tr>
				<tr> 
					<th>주소</th> 
					<td><input type="text" name="caddress" value="${customer.caddress }"></td> 
				</tr>
			 	<tr>
					<th>생년월일</th>
					<td>
						<input type="text" name="cbirth" value="${customer.cbirth }" id="datepicker"><br>
					</td>
				</tr>
				<tr> 
					<td colspan="2">
						<input type="submit" value="수정" class="myPageBtn_style">
						<input type="reset" value="초기화" class="myPageBtn_style"
						   	onclick="history.back()">
						<c:if test="${ admin != null }">	   
						<input type="button" value="목록" class="myPageBtn_style" 
					       	onclick="location='${conPath}/CustomerList.do?pageNum=${param.pageNum}'">
						</c:if>
					</td>
				</tr>
			</table>
	
		</form>
	</div>
</body>
</html>