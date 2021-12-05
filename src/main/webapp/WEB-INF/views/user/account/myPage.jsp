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
    <title>정보 수정</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <link
        href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
        rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/account/mypage.css" />

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        const regPwd= /(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,30}$/;
        const regName= /^[a-zA-Z가-힣]{2,10}$/;
        const regEmail= /^[a-zA-Z0-9]*[@]{1}[a-zA-Z0-9]*[.]{1}[a-zA-Z]{1,3}$/;

        <c:choose>
            <c:when test="${isLogOn != true}">
                window.onload = function(){
                    alert("로그인 페이지로 이동합니다!");
                    location.href = "/account/login-form";
                }
            </c:when>
        </c:choose>

        function fn_mod(){
            const form = document.accForm;

            if(($('#pwd').val() !== '') && (regPwd.test($('#pwd').val()) == false)){
                alert("비밀번호는 8~30 자리의 대소문자와 숫자, 특수문자를 입력해 주세요!");
                console.log($('#pwd').val());
                return false;
            }

            if(($('#name').val() == null) || ($('#name').val() == '')){
                alert("이름을 입력해 주세요!");
                return false;
            }else if(($('#name').val() !== '') && (regName.test($('#name').val()) == false)){
                alert("이름은 2~4자의 영문 또는 한글만 입력해 주세요!");
                return false;
            }

            if(($('#email').val() == null) || ($('#email').val() == '')){
                alert("이메일을 입력해 주세요!");
                return false;
            }else if(($('#email').val() !== '') && (regEmail.test($('#email').val()) == false)){
                alert("올바른 이메일 형식을 입력해 주세요!");
                return false;
            }

            if(confirm("정보를 수정하시겠습니까?")){
                alert("정보가 수정되었습니다!");
                form.submit();
            }
        }

        function fn_withdraw(){
            const _id = "${pk}";
            console.log(_id);

            if(confirm("정말로 탈퇴하시겠습니까?")){
                $.ajax({
                    type: "post",
                    async: false,
                    url: "/account/delete",
                    dataType: "text",
                    data: { id: _id },
                    success: function(){
                        alert('회원탈퇴가 정상적으로 처리되었습니다!');
                        location.href="/board/list";
                    },
                    error: function(){
                        alert("오류가 발생했습니다. 잠시 후에 다시 시도해 주세요.");
                    }
                });
            }
        }
    </script>
</head>
<body>
    <jsp:include page="../side/sideAcc.jsp" flush="true" />
    <div class="wrap">
        <div class="article">
            <h1>회원 수정</h1>
            <hr>
            <form action="/account/mod-acc" method="post" name = "accForm">
                <div class="cr">
                    아이디
                    <input class="id" type="text" disabled value="${info.userId}"></input>
                </div>
                <div class="cr">
                    비밀번호
                    <input type="password" id="pwd" name="userPwd" />
                </div>
                <div class="cr">
                    이름
                    <input type="text" id="name" name="username" value="${info.username}" />
                </div>

                <div class="cr">
                    이메일
                    <input type="text" id="email" name="email" value="${info.email}"/>
                </div>
                <div class="cr">
                    가입일
                    <span>
                        <fmt:parseDate var="createdDt" value="${info.createdDt}" pattern="yyyy-MM-dd'T'HH:mm" type="both" />
                        <fmt:formatDate pattern="yyyy.MM.dd" value="${createdDt}" />
                    </span>
                </div>
                <input type="hidden" name="pk" value="${pk}" />
                <div class="command">
                    <a href="#" onClick="fn_mod()">수정</a><a href="/board/list">목록</a><a href="#" onClick="fn_withdraw()">회원탈퇴</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>