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
<title>출차 차량 조회</title>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <div class="navbar">
    	<a href="${contextPath}/parking/index.jsp">홈</a>
        <a href="${contextPath}/parkingLot/listParking.do">주차 리스트</a>
        <a href="${contextPath}/parkingLot/entryForm.do">입차 등록</a>
        <a href="${contextPath}/parkingLot/findExitForm.do">출차 등록</a>
    </div>
    
	<h1 style="text-align:center">출차 차량 조회</h1>
	<form method="post" action="${contextPath }/parkingLot/findCar.do">
		<table border="1" align="center">
			<tr>
				<td>차량번호</td>
				<td><input type="text" name="car_number" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="차량 조회"> &nbsp;
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>