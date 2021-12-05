<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    let id_dup = 0;
    const regId= /^[a-z0-9]{4,20}$/;
    const regPwd= /(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,30}$/;
    const regName= /^[a-zA-Z가-힣]{2,10}$/;
    const regEmail= /^[a-zA-Z0-9]*[@]{1}[a-zA-Z0-9]*[.]{1}[a-zA-Z]{1,3}$/;

    function check_id(){
        const check_id = $('#id').val();

        if(check_id !== null || check_id !== ''){
            $.ajax({
                type: "post",
                async: true,
                url: "/api/account/check-dup",
                dataType: "text",
                data: {id: check_id},
                success: function(data){
                    if(data == 0){
                        $('#usable').text("사용할 수 있는 아이디입니다.");
                        id_dup = 0;
                    }else{
                        $('#usable').text("이미 존재하는 아이디입니다!");
                        id_dup = 1;
                    }
                }
            });
        }
    }

    function register(){
        const form = document.registerForm;

        if(($('#id').val() == null) || ($('#id').val() == '')){
            alert("아이디를 입력해 주세요!");
            return false;
        }else if(($('#id').val() !== '') && (regId.test($('#id').val()) == false)){
            alert("아이디는 4~20 자리의 소문자와 숫자만 사용할 수 있습니다!");
            return false;
        }

        if(($('#pwd').val() == null) || ($('#pwd').val() == '')){
            alert("비밀번호를 입력해 주세요!");
            return false;
        }else if(($('#pwd').val() !== '') && (regPwd.test($('#pwd').val()) == false)){
            alert("비밀번호는 8~30 자리의 대소문자와 숫자, 특수문자를 입력해 주세요!");
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

        if(id_dup == 1){
            alert("아이디를 확인해 주세요!");
            return false;
        }

        if(confirm("회원가입을 하시겠습니까?")){
            alert('회원가입이 완료되었습니다!');
            form.submit();
        }
    }

    function fn_back(){
         window.history.back();
    }
</script>
<link rel="stylesheet" href="/resources/css/registerForm.css" />
</head>
<body>
<div class="wrap">
    <h1>회원 가입</h1>
    <form action="/account/addAccount" method="post" name="registerForm">
        <table align="center">
            <tr>
                <td>아이디</td>
                <td><input type="text" maxlength="20" id="id" name="userId" onfocusout="check_id()"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" maxlength="30" id="pwd" name="userPwd"></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" maxlength="4" id="name" name="username"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="text" maxlength="30" id="email" name="email"></td>
            </tr>
        </table>
        <h2 id="usable"></h2>
        <span class="submit" onClick="register()">회원가입</span>
        <span class="cancel" onClick="fn_back()">취소</span>
    </form>
</div>
</body>
</html>