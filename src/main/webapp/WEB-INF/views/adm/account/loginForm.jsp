<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input title</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<c:choose>
    <c:when test="${result=='failed'}">
        <script>
            window.onload=function(){
                alert("아이디 혹은 비밀번호를 확인해 주세요.");
            }
        </script>
   </c:when>
   <c:when test="${account != null}">
        <script>
            window.onload=function(){
                location.replace("/adm/account/list");
            }
        </script>
   </c:when>
</c:choose>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/login.css" />
    <script defer src="/resources/js/main.js"></script>
</head>
<body>
  <div id="wrap">
    <!-- <div class="logo"><h1>로그인</h1></div> -->
    <h1 class="h1_logo">관리자 로그인</h1>
    <form method="post" action="/adm/login">
      <div class="input_box">
        <input  class="input_el" type="text" name="userId"><span id="email" class="input_span1">아이디</span><br>
        <input  class="input_el" type="password" name="userPwd"><br><span id="pw" class="input_span2">비밀번호</span>
        <input class="input_el" type="submit" value="로그인">
      </div>
    </form>
  </div>
</body>
</html>