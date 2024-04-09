<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 목록</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<h1>게시물목록</h1>
<h3>로그인 : ${loginVO.name} </h3>
<form id="searchForm" action="list" method="post" >
    <input type="hidden" id="size" name="size" value="${pageRequestVO.size}">
    <label>제목</label>
    <input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    <input type="submit" value="검색">
</form>

<form id="listForm" action="view" method="post">
    <input type="hidden" id="boardid" name="boardid" >
</form>
<%--    select 로 10,20,30 추가한 코드 --%>
<form id="sizeForm" action="list" method="get">
    <select name="size" onchange="document.getElementById('sizeForm').submit();">
        <option value="10" ${pageRequestVO.size == 10 ? 'selected' : ''}>10개씩 보기</option>
        <option value="20" ${pageRequestVO.size == 20 ? 'selected' : ''}>20개씩 보기</option>
        <option value="30" ${pageRequestVO.size == 30 ? 'selected' : ''}>30개씩 보기</option>
    </select>
</form>
<%--    select 로 10,20,30 추가한 코드--%>
<table border="1">
    <tr>
        <th>게시물번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    <c:forEach var="boards" items="${pageResponseVO.list}">
        <tr>
            <td onclick="jsView('${boards.boardid}')"  style="cursor:pointer;">${boards.boardid}</td>
            <td><a href="/boards/view?boardid=${boards.boardid}">${boards.title}</a></td>
            <td>${boards.bwriter}</td>
            <td>${boards.boardid}</td>
        </tr>
    </c:forEach>
</table>

<!--  페이지 네비게이션 바 출력  -->
<div class="float-end">
    <ul class="pagination flex-wrap">
        <c:if test="${pageResponseVO.prev}">
            <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.start -1}">이전</a>
            </li>
        </c:if>

        <c:forEach begin="${pageResponseVO.start}" end="${pageResponseVO.end}" var="num">
            <li class="page-item ${pageResponseVO.pageNo == num ? 'active':''} ">
                <a class="page-link"  data-num="${num}">${num}</a></li>
        </c:forEach>

        <c:if test="${pageResponseVO.next}">
            <li class="page-item">
                <a class="page-link"  data-num="${pageResponseVO.end + 1}">다음</a>
            </li>
        </c:if>
    </ul>

</div>

<script>

    document.querySelector(".pagination").addEventListener("click", function (e) {
        e.preventDefault();

        const target = e.target;

        if(target.tagName !== 'A') {
            return;
        }

        const num = target.dataset["num"];

        // 현재 페이지의 URL에서 기본 경로를 추출합니다.
        const baseUrl = window.location.origin + window.location.pathname;

        // 쿼리 문자열을 추가하여 전체 URL을 구성합니다.
        const url = baseUrl + '?pageNo=' + num + '&size=' + document.getElementById('size').value;

        // 페이지를 새 URL로 리디렉션합니다.
        window.location.href = url;
    });

    document.getElementById('sizeForm').addEventListener('change', function (e) {
        // Select box에서 선택된 페이지 크기 값을 가져옵니다.
        const selectedSize = e.target.value;

        // 현재 페이지의 URL에서 기본 경로를 추출합니다.
        const baseUrl = window.location.origin + window.location.pathname;

        // 쿼리 문자열을 구성하여 전체 URL을 만듭니다.
        let url = baseUrl + '?size=' + selectedSize;

        // 페이지를 새 URL로 리디렉션합니다.
        window.location.href = url;
    });

    function jsView(boardid) {
        //인자의 값을 설정한다
        boardid.value = boardid;

        //양식을 통해서 서버의 URL로 값을 전달한다
        listForm.submit();

    }
    document.getElementById('searchForm').addEventListener('submit', function (e) {
        e.preventDefault();

        // 입력된 검색 키와 페이지 크기 값을 가져옵니다.
        const searchKey = document.getElementById('searchKey').value;
        const size = document.getElementById('size').value;

        // 현재 페이지의 URL에서 기본 경로를 추출합니다.
        const baseUrl = window.location.origin + window.location.pathname;

        // 쿼리 문자열을 구성하여 전체 URL을 만듭니다.
        let url = baseUrl + '?size=' + size;
        if (searchKey) {
            url += '&searchKey=' + encodeURIComponent(searchKey);
        }

        // 페이지를 새 URL로 리디렉션합니다.
        window.location.href = url;
    });
</script>
<div class="button-container">
    <a href="board.do?action=insertForm">등록</a>
</div>

<script type="text/javascript" src="<c:url value='/resources/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/update.js'/>"></script>
</body>
</html>

