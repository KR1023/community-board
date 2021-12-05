<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>side</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/board/side.css" />
    <script>

        function fn_add_article(){
            <c:choose>
                <c:when test="${isLogOn != true}">
                    if(confirm("로그인이 필요합니다. 로그인하시겠습니까?")){
                        location.href="/account/login-form";
                    }
                </c:when>
            </c:choose>
        }
    </script>
</head>
<body>
    <div class="side">
        <div class="none">
            <div class="account">
                <a href="/account/login-form">로그인</a>&nbsp;&nbsp;&nbsp;<a href="/account/register-form">회원가입</a>
            </div>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/board/notice'">공지사항</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/board/list?pageNum=0'">자유게시판</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="fn_add_article()">글 작성</span>
        </div>
    </div>
</body>
</html>