// CSRF 토큰 설정 함수
function setCSRFHeaders() {
    const csrfToken = document.querySelector("meta[name='_csrf']").content;
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
    return { [csrfHeader]: csrfToken };
}

// JSON 형태로 서버에 데이터를 전송하는 함수
function sendJSON(url, data, handler) {
    const headers = {
        "Content-Type": "application/json; charset=utf-8",
        ...setCSRFHeaders()
    };
    fetch(url, {
        method: "POST",
        headers: headers,
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(json => handler(json))
        .catch(err => console.error('Error with the fetch operation:', err));
}

// FormData 형태로 서버에 데이터를 전송하는 함수
function sendFormData(url, formData, handler) {
    fetch(url, {
        method: "POST",
        headers: setCSRFHeaders(),
        body: formData
    }).then(res => res.json())
        .then(json => handler(json))
        .catch(err => console.error('Error with the fetch operation:', err));
}

// 게시물 등록을 처리하는 함수
function handlePostSubmission(e) {
    e.preventDefault();
    const formData = new FormData(e.target);

    if (confirm("게시물을 등록하시겠습니까?")) {
        sendFormData('/boards/insert', formData, (json) => {
            if (json.status === 0) {
                alert("게시물이 성공적으로 등록되었습니다.");
                window.location.href = "/boards/list";
            } else {
                alert(json.statusMessage);
            }
        });
    } else {
        console.log("게시물 등록이 취소되었습니다.");
    }
}

// DOM이 로드된 후 실행되는 함수
document.addEventListener("DOMContentLoaded", function() {
    const rForm = document.getElementById("rForm");
    if (rForm) {
        rForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const data = { // 예시 데이터, 실제 구현에 맞게 조정 필요
                name: rForm.elements['name'].value,
                age: rForm.elements['age'].value
            };
            sendJSON('/path/to/api', data, (json) => {
                console.log("Response:", json);
            });
        });
    }

    const updateForm = document.getElementById("updateForm");
    if (updateForm) {
        updateForm.addEventListener("submit", handlePostSubmission);
    }
});
