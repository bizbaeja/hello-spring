<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href='<c:url value="/css/signup.css"/>'>
	<%@ include file="/WEB-INF/views/include/meta.jsp" %>
	<%@ include file="/WEB-INF/views/include/css.jsp" %>
	<title>회원가입</title>
</head>
<body>

<div class="wrap">

	<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<div class="user-detail">
		<h2>회원 정보 수정</h2>
		<form id="signupForm" action="<c:url value='/users/signup'/>" method="post">

			<sec:csrfInput/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div>
				<div>
					<label for="member_id">아이디:</label>
					<input type="text" id="member_id" name="member_id" required>
				</div>
				<div>
					<label for="member_name">이름:</label>
					<input type="text" id="member_name" name="member_name" required>
				</div>
				<div>
					<label for="member_pwd">비밀번호:</label>
					<input type="password" id="member_pwd" name="member_pwd" required>
				</div>
				<div>
					<label for="passwordConfirm">비밀번호 확인:</label>
					<input type="password" id="passwordConfirm" required>
				</div>
				<div>
					<label for="member_hand_phone">전화번호:</label>
					<input type="text" id="member_hand_phone" name="member_hand_phone" required>
				</div>
				<div>
					<label for="member_address">주소:</label>
					<input type="text" id="member_address" name="member_address" required>
				</div>
				<div>
					<label for="member_detail_address">상세주소:</label>
					<input type="text" id="member_detail_address" name="member_detail_address" required>
				</div>
				<div>
					<label for="member_birthday">생년월일:</label>
					<input type="date" id="member_birthday" name="member_birthday" required>
				</div>
				<div>
					<label>성별:</label>
					<input type="radio" id="gender_male" name="member_gender" value="M"><label for="gender_male">남성</label>
					<input type="radio" id="gender_female" name="member_gender" value="F"><label for="gender_female">여성</label>
				</div>
				<div>
					<input type="submit" value="가입하기">
				</div>
		</form>
	</div>
</div>

<script type="text/javascript">document.addEventListener("DOMContentLoaded", function() {
	const signupForm = document.getElementById("signupForm");
	const password = document.getElementById("member_pwd");
	const passwordConfirm = document.getElementById("passwordConfirm");

	signupForm.addEventListener("submit", function(e) {
		e.preventDefault();

		if (password.value !== passwordConfirm.value) {
			alert("비밀번호가 일치하지 않습니다.");
			passwordConfirm.focus();
			return;
		}

		// Use the myFetch function provided in common.js to submit the form data
		myFetch('/users/signup', 'signupForm', function(json) {
			// Assuming the server sends back a JSON response indicating success or failure
			if (json.success) {
				alert("회원 가입이 완료되었습니다.");
				window.location.href = '<c:url value="/"/>';
			} else {
				alert("회원가입에 실패했습니다: " + json.message);
			}
		});
	});
});
const myFetch = (url, formId, handler) => {
	const param = typeof formId == "string" ? formToSerialize(formId) : JSON.stringify(formId);
	const csrfToken = document.querySelector("meta[name='_csrf']").content;
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
	const headers = {"Content-type" : "application/json; charset='utf-8'"};
	if (csrfToken) {
		headers[csrfHeader] = csrfToken
	}
	fetch(url, {
		method:"POST",
		body : param,
		headers : {
			"Content-Type":  "application/json; charset='utf-8'"
		},
	}).then(res => res.json()).then(json => {
		//서버로 부터 받은 결과를 사용해서 처리 루틴 구현
		console.log("json ", json );
		if (handler) handler(json);
	});
}
const formToSerialize = (formId) => JSON.stringify([].reduce.call(document.querySelector('#' + formId), (data, element) => {
					//이름이 있는 것을 대상으로함
					console.log(element);
					if (element.name == '') return data;
					//radio와 checkbox인 경우는 반드시 선택된 것만 대상으로함
					if (element.type == 'radio' || element.type == 'checkbox') {
						if (element.checked) {
							if (typeof data[element.name] == 'undefined') {
								//문자열 1건 추가
								if (document.querySelector("#" + formId).querySelectorAll("[name='" +element.name+ "']").length == 1 || element.type == 'radio') {
									//문자열 값을 배열로 변경
									data[element.name] = element.value;
								} else if (element.type == 'checkbox') {
									//배열로 변경
									data[element.name] = [element.value];
								}
							} else if(typeof data[element.name] == 'string') {
								//문자열 값을 배열로 변경
								data[element.name] = [data[element.name], element.value];
							} else if(typeof data[element.name] == 'object') {
								//배열에 문자열 값을 추가
								data[element.name].push(element.value);
							}
						}
					} else {
						//그외는 모두 대상으로 함
						data[element.name] = element.value;
					}
					return data;
				},
				{} //초기값
		)
);
const menuActive = link_id => {
	document.querySelector("#" + link_id).classList.add("active");
}

console.log(location.pathname)

</script>

</body>
</html>
