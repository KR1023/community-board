<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseDate var="createdDt" value="${account.createdDt}" pattern="yyy-MM-dd'T'HH:mm" type="both"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>상세 페이지</title>
</head>
<body>
	<table>
		<tr>
			<td>회원 Id : </td>
			<td>${account.userId}</td>
		</tr>
		<tr>
			<td>회원 이름 : </td>
			<td>${account.username }</td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td>${account.email}</td>
		</tr>
		<tr>
			<td>가입일 : </td>
			<td><fmt:formatDate pattern="yyyy.MM.dd" value="${createdDt}" /></td>
		</tr>
	</table>
</body>
</html>