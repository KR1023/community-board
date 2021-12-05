<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="article" value="${article}" />
<fmt:parseDate var="createdDt" value="${article.createdDt}" pattern="yyyy-MM-dd'T'HH:mm" type="both" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>글 상세</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/board/boardDetail.css" />

    <script>
        <c:choose>
            <c:when test="${account != 'admin'}">
                window.onload = function(){
                    alert('로그인이 필요합니다.');
                    location.href = "/adm/login-form";
                }
            </c:when>
        </c:choose>
    </script>
</head>
<body>
    <jsp:include page="../side/admSide.jsp" flush="true" />
    <div class="wrap">
            <div class="article">
                <h1>${article.title}</h1>
                <hr>
                <div class="cr">작성자<span>${article.userId}</span></div>
                <div class="cr">조회수<span>${article.viewCnt}</span></div>
                <div class="cr">작성일
                    <span><fmt:formatDate pattern="yyyy.MM.dd" value="${createdDt}" /></span>
                </div>
                <div class="management">
                    <a href="javascript:history.back();">목록</a>
                </div>
                <div class="content">
                    <textarea wrap="hard" cols="152" rows="26" maxlength="60000" disabled>${article.contents}
                    </textarea>
                </div>
            </div>
        </div>
</body>
</html>