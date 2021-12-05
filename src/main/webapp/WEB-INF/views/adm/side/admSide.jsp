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

        function fn_logout(){
            if(confirm("로그아웃 하시겠습니까?")){
                alert("로그아웃되었습니다!");
                location.href="/adm/logout";
            }
        }

    </script>
</head>
<body>
    <div class="side">
        <div class="button">
            <span class="btn_txt" onClick="location.href='/adm/account/list'">회원 관리</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/adm/notice/list'">공지사항 관리</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/adm/board/list'">자유게시판</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="fn_logout()">로그아웃</span>
        </div>
    </div>
</body>
</html>