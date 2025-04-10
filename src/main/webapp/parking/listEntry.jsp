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
		<c:when test='${param.msg =="addEntry" }'>
			<script>
				window.onload = function(){
					alert("입차등록을 완료했습니다.");
				}
			</script>
		</c:when>
		<c:when test='${param.msg =="addExit" }'>
			<script>
				window.onload = function(){
					alert("출차처리가 완료했습니다.");
				}
			</script>
		</c:when>
	</c:choose>
<meta charset="UTF-8">
<title>주차리스트</title>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<style>
	.cla1{
		text-align:center;
	}
</style>
</head>
<body>

    <div class="navbar">
    	<a href="${contextPath}/parking/index.jsp">홈</a>
        <a href="${contextPath}/parkingLot/listParking.do">주차 리스트</a>
        <a href="${contextPath}/parkingLot/entryForm.do">입차 등록</a>
        <a href="${contextPath}/parkingLot/findExitForm.do">출차 등록</a>
    </div>
    
		<h1 style="text-align:center">주차리스트</h1>
		<table class="cla1" border="1" align=center>
			<tr>
				<td>NO.</td>
				<td>차량넘버</td>
				<td>주차장소</td>
				<td>입차시간</td>
				<td>출차시간</td>
				<td>주차요금</td>
			</tr>
			<c:choose>
				<c:when test="${empty parkingList }">
					<tr>
						<td colspan="6">
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
							<td>
								<fmt:formatDate value="${park.entry_log}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<fmt:formatDate value="${park.exit_log}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
									<!-- 출차시간이 있을 때만 요금 계산 -->
									<!-- 출차되지 않으면 주차요금은 표시하지 않음 -->
								<c:choose>
									<c:when test="${not empty park.exit_log}">
										<fmt:formatNumber value="${park.parking_fee}" pattern="###,###"/>원
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<p align="center">
			<a href="${contextPath }/parkingLot/entryForm.do">입차하기</a> &nbsp;&nbsp;&nbsp;
			<a href="${contextPath }/parkingLot/findExitForm.do">출차하기</a>		
		</p>
</body>
</html>