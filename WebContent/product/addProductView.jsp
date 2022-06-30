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
<title>상 품 등 록</title>
<link href="${conPath }/css/stylep3.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		$(document).ready(function () {
			
		});
</script>
</head>
<body>
	<div id="content_form" >
		<form action="${conPath }/AddProduct.do" method="post" enctype="multipart/form-data">
			 <table>
				<caption>상 품 등 록</caption>
				<tr> <th>상품명</th> <td><input type="text" name="pname" required="required"></td> </tr>
				<tr> 
					<th>상품타입</th> 
					<td> 
						<select name="ptype"> 
							<option value="DRESS">드레스 </option>
                        	<option value="ACC">악세사리 </option>
                        	<option value="TOP">상의 </option>
                        	<option value="BOT">하의 </option>
                    	</select>
                    </td> 
				</tr>
				<tr> 
					<th>상품설명</th> 
					<td><textarea rows="5" cols="20" name="pcontent"></textarea></td> 
				</tr>
				<tr> 
					<th>상품사진</th> 
					<td><input type="file" name="pphoto" ></td>  
				</tr>
				<tr> <th>가격</th> <td><input type="number" name="pprice" min=0 max=99999999 required="required"></td> </tr>
				
					<tr> 
						<td colspan="2">
						<input type="submit" value="상품 등록" class="btn">
						<input type="reset" value="초기화" class="btn">
						<input type="button" value="상품 목록" class="btn" onclick="location='${conPath}/ProductList.do'">
					</td>
				</tr>		
			</table>
		</form>
	</div>
</body>
</html>