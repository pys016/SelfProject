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
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<style>
        .main-content {
            text-align: center;
            margin-top: 50px;
        }

        .main-content h1 {
            font-size: 36px;
            color: #333;
        }

        .main-content p {
            font-size: 18px;
            color: #666;
        }

        .main-content .btn {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .main-content .btn:hover {
            background-color: #45a049;
        }

        @media screen and (max-width: 600px) {
            .navbar a {
                float: none;
                display: block;
                text-align: left;
            }
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

    <div class="main-content">
        <h1>주차 관리 시스템</h1>
        <p>주차 관리 시스템에 오신 것을 환영합니다. 아래에서 원하는 기능을 선택하세요.</p>
        <div>
            <a href="${contextPath}/parkingLot/listParking.do" class="btn">주차 리스트 보기</a>
            <a href="${contextPath}/parkingLot/entryForm.do" class="btn">입차 등록</a>
            <a href="${contextPath}/parkingLot/findExitForm.do" class="btn">출차 등록</a>
        </div>
    </div>
</body>
</html>