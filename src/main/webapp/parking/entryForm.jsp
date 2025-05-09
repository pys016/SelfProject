<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입차등록</title>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <div class="navbar">
    	<a href="${contextPath}/parking/index.jsp">홈</a>
        <a href="${contextPath}/parkingLot/listParking.do">주차 리스트</a>
        <a href="${contextPath}/parkingLot/entryForm.do">입차 등록</a>
        <a href="${contextPath}/parkingLot/findExitForm.do">출차 등록</a>
    </div>
    
<c:if test="${not empty msg }">
	<script>
		alert('${msg}');
	</script>
</c:if>
	<form method="post" action="${contextPath }/parkingLot/addEntry.do">
		<h1 style="text-align:center">입차 등록</h1>
		<table border="1" align="center">
			<tr>
				<td>차량넘버</td>
				<td><input type="text" name="car_number" required></td>
			</tr>
			<tr>
				<td>주차장소</td>
				<td><input type="text" name="park_area" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="입차등록"> &nbsp;
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>