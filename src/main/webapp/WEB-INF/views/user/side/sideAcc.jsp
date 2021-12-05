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
                alert('로그아웃 되었습니다.');
                location.href='/account/logout';
            }
        }
    </script>
</head>
<body>
    <div class="side">
        <div class="myPage">
            <div class="account"><span style="font-size:20px;">${account}</span><br><br>
                <a href="/account/mypage">정보 수정</a>&nbsp;&nbsp;&nbsp;<a href="#" onClick="fn_logout()">로그아웃</a>
            </div>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/board/notice'">공지사항</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/board/list'">자유게시판</span>
        </div>
        <div class="button">
            <span class="btn_txt" onClick="location.href='/board/add-form'">글 작성</span>
        </div>
    </div>
</body>
</html>