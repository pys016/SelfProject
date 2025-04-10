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
	<c:choose>
		<c:when test='${msg =="addEntry" }'>
			<script>
				window.onload = function(){
					alert("입차등록을 완료했습니다.");
				}
			</script>
		</c:when>
	</c:choose>
<meta charset="UTF-8">
<title>주차리스트</title>
</head>
<body>
		<h1 style="text-align:center">주차리스트</h1>
		<table border="1" align="center">
			<tr>
				<td>NO.</td>
				<td>차량넘버</td>
				<td>주차장소</td>
				<td>입차시간</td>
				<td>출차시간</td>
			</tr>
			<c:choose>
				<c:when test="${empty parkingList }">
					<tr>
						<td colspan="5">
							<p align="center">
								<b><span style="font-size:12pt">주차된 차량이 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="park" items="${parkingList }" varStatus="parking_id">
						<tr>
							<td>${parking_id.count }</td>
							<td>${park.car_number}</td>
							<td>${park.park_area}</td>
							<td>${park.entry_log}</td>
							<td>${park.exit_log}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<a href="${contextPath }/parkingLot/entryForm.do">입차하기</a>
</body>
</html>