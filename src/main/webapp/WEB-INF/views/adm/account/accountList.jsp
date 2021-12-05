<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="total" value="${accCnt}" />
<c:set var="keyword" value="${keyword}" />
<c:set var="pageNum" value="${pageNum}" />
<c:if test="${pageNum == null}">
    <c:set var="pageNum" value="${0}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/board/boardList.css" />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        <c:choose>
            <c:when test="${id != null}">
                window.onload = function(){
                    alert('${id} 님, 환영합니다.');
                }
            </c:when>
            <c:when test="${account != 'admin'}">
                window.onload = function(){
                    alert('로그인이 필요합니다.');
                    location.href = "/adm/login-form";
                }
            </c:when>
        </c:choose>

        function search_user(){
            const search = $('#searchKey').val();
            console.log(search);
            location.href="/adm/account/list?pageNum=0&keyword="+search;
        }

        function searching(){
            if(window.event.keyCode == 13){
                search_user();
            }
        }
    </script>
</head>

<body>
    <jsp:include page="../side/admSide.jsp" flush="true" />
    <div class="wrap">
        <div class="table">
            <h1>회원 목록</h1>
            <hr>
            <div class="search">
                <input id = "searchKey" onkeyup="searching()" type="text" value="" name="keyword" placeholder="아이디 입력" />
                <span onClick="search_user()"  class="material-icons">search</span>
            </div>
            <table class="contents">
                <tr class="title">
                    <td width="25%">아이디</td>
                    <td width="15%">이름</td>
                    <td width="30%">이메일</td>
                    <td width="20%">가입 일자</td>
                </tr>
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach var="acc" items="${list}">
                        <tr class="content">
                            <td>${acc.userId }</td>
                            <td>${acc.username }</td>
                            <td>${acc.email }</td>
                            <td>
                            <fmt:parseDate var="createdDt" value="${acc.createdDt}" pattern="yyyy-MM-dd'T'HH:mm" type="both" />
                            <fmt:formatDate pattern="yyyy.MM.dd" value="${createdDt}" />
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr class="content">
                        <td colspan="4">등록된 회원이 없습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </table>
            <div class="page">
                <%
                    int cnt = (int)pageContext.getAttribute("total");   // 게시물 수
                    if(cnt == 0) {                                      // 게시물이 없으면 1로 치환
                                            cnt = 1;
                                        }

                    Object stPage = pageContext.getAttribute("pageNum");
                    int pageNum = Integer.parseInt(stPage.toString());
                    int section = (int)((pageNum) / 10);
                    int pageCnt = (cnt / 10);                           // 총 페이지 수
                    if((cnt % 10) == 0) {
                        pageCnt -= 1;
                    }

                    int pageCheck = pageCnt / 10;
                %>
                <c:set var="cnt" value="${total}" />
                <c:set var="pageCnt" value="<%=pageCnt %>" />
                <c:set var="pageCheck" value="<%= pageCheck %>" />
                <c:set var="section" value="<%=section %>" />
                <c:set var="start" value="${section * 10}"/>
                <c:set var="end" value="${(section * 10) + 9}" />

                <c:if test="${ section == pageCheck }" >
                        <c:set var="end" value="${pageCnt}" />
                </c:if>

                <a class="s_f" href="/adm/account/list?pageNum=0&keyword=${keyword}">
                    <span class="material-icons-outlined sf">first_page</span>
                </a>
                <c:if test="${pageNum > 0}">
                    <a class="pageNum" href="/adm/account/list?pageNum=${pageNum-1}&keyword=${keyword}">
                        <span class="material-icons-outlined">arrow_back_ios_new</span>
                    </a>
                </c:if>
                <c:forEach var="i" begin="${start}" end="${end}" step="1">
                    <c:choose>
                        <c:when test="${keyword == null}">
                            <c:choose>
                                <c:when test="${pageNum == i}">
                                    <span class="pageNum"><a class="selected" href="/adm/account/list?pageNum=${i}">${i + 1}</a></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="pageNum"><a href="/adm/account/list?pageNum=${i}">${i + 1}</a></span>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${keyword != null}">
                            <c:choose>
                                <c:when test="${pageNum == i}">
                                    <span class="pageNum"><a class="selected" href="/adm/account/list?pageNum=${i}&keyword=${keyword}">${i + 1}</a></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="pageNum"><a href="/adm/account/list?pageNum=${i}&keyword=${keyword}">${i + 1}</a></span>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageNum != pageCnt}">
                    <a class="pageNum" href="/adm/account/list?pageNum=${pageNum+1}&keyword=${keyword}">
                        <span class="material-icons-outlined">arrow_forward_ios</span>
                    </a>
                </c:if>
                <a class="s_f" href="/adm/account/list?pageNum=${pageCnt}&keyword=${keyword}">
                    <span class="material-icons-outlined sf">last_page</span>
                </a>
            </div>
        </div>
    </div>
</body>
</html>