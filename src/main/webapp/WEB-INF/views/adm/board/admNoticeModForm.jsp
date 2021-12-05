<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <link
        href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
        rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/board/boardAdd.css" />
    <title>글 작성</title>

    <script>
        <c:choose>
            <c:when test="${isLogOn != true }">
                window.onload = function(){
                    alert("로그인이 필요합니다!");
                    location.href="/account/login-form";
                }
            </c:when>
            <c:when test="${account != article.userId}">
                window.onload = function(){
                    alert("사용자가 일치하지 않습니다!");
                    location.replace("/board/list");
                }
            </c:when>
        </c:choose>

        function fn_submit(){
            const form = document.article;
            alert('글을 수정했습니다!');
            article.submit();
        }
    </script>
</head>
<body>
    <jsp:include page="../side/admSide.jsp" flush="true" />
    <div class="wrap">
        <div class="article">
            <h1>공지사항 수정</h1>
            <hr>
            <form name="article" action="/adm/notice/modify" method="post">
                <div class="cr">
                    제목
                    <input type="text" name="title" value="${article.title}"/>
                </div>
                <div class="content">
                    <textarea name="contents" wrap="hard" cols="152" rows="26" maxlength="60000">${article.contents}</textarea>
                </div>
                <div class="command">
                    <a href="#" onclick="fn_submit()">수정</a><a href="/board/list">취소</a>
                </div>
                <input type="hidden" name="boardId" value="${article.boardId}" />
            </form>
        </div>
    </div>
</body>
</html>