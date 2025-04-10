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
<title>출차등록</title>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <div class="navbar">
        <a href="${contextPath}/parking/index.jsp">홈</a>
        <a href="${contextPath}/parkingLot/listParking.do">주차 리스트</a>
        <a href="${contextPath}/parkingLot/entryForm.do">입차 등록</a>
        <a href="${contextPath}/parkingLot/findExitForm.do">출차 등록</a>
    </div>
    
	<h1 style="text-align:center">출차 확인</h1>
	
	<c:choose>
		<c:when test="${empty findCar }">
			<p align="center"><b>입력한 차량번호는 주차되어 있지 않습니다.</b></p>
		</c:when>
		<c:otherwise>
			<form method="post" action="${contextPath }/parkingLot/addExit.do">
				<table border="1" align="center">
					<tr>
						<td>차량번호</td>
						<td>${findCar.car_number }</td>
					</tr>
					<tr>
						<td>주차장소</td>
						<td>${findCar.park_area }</td>
					</tr>
					<tr>
						<td>입차시간</td>
						<td>${findCar.entry_log }</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" name="car_number" value="${findCar.car_number }">
							<input type="submit" value="출차하기">
						</td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>