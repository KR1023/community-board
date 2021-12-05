<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageContext" value="${pageContext.request.contextPath}" />
<c:set var="total" value="${accCnt}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>
	<% int cnt = (int)pageContext.getAttribute("total");
	    int pageCnt = (cnt / 10) + 1;
	    if((cnt % 10) == 0) {
	        pageCnt -= 1;
	    }
	%>
	<c:set var="end" value="<%=pageCnt %>" />
	<c:forEach var="i" begin="1" end="${end }" step="1">
		<a href="${contextPath}/account/test?pageNum=${i*10}">${i}</a>
	</c:forEach>
</body>
</html>