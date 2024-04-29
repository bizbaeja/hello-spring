/*
document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");
    if (updateForm) {
        updateForm.addEventListener("submit", e => {
            e.preventDefault();

            // Check if the user confirms the update before making the call
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("board.do", formData, json => {
                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수정되었습니다.");
                        window.location.href = "board.do?action=view&boardid=" + formData.get("boardid");
                    } else {
                        // If there was an error in saving data, alert the error
                        alert(json.statusMessage);
                    }
                });
            } else {
                // If the user cancels, do nothing
                console.log("User cancelled the update.");
            }
        });
    }
});
*/
/*
document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");
    if (updateForm) {
        updateForm.addEventListener("submit", e =>  {
            var boardid = document.getElementById("boardid").value;
            var form = document.getElementById("updateForm");
            form.action.value = "update";
            var formData = new FormData(form);
            e.preventDefault();
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("board.do", "updateForm", json => {

                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수되었습니다.");
                        window.location.href = "board.do?action=view&boardid=" + boardid;
                    } else {
                        // If there was an error in saving data, alert the error
                        alert(json.statusMessage);
                    }
                });
            } else {
                // If the user cancels, do nothing
                console.log("User cancelled the update.");
            }
        });
    } else {
        console.log("viewForm not found");
    }
});
*/
menuActive("board_link");
const csrfParameter = document.querySelector("meta[name='_csrf_parameter']").content;
const csrfToken = document.querySelector("meta[name='_csrf']").content;
//이미지 업로드 URL
const board_image_url = "<c:url value='/board/boardImageUpload?board_token=${board_token}&'/>" + csrfParameter + "=" + csrfToken;

//cfeditor관련 설정
let bcontent; //cfeditor의 객체를 저장하기 위한 변수
ClassicEditor.create(document.querySelector('#bcontent'),{
    //이미지 업로드 URL을 설정한다
    ckfinder: {
        uploadUrl : board_image_url
    }
})
    .then(editor => {
        console.log('Editor was initialized');
        //ckeditor객체를 전역변수 bcontent에 설정함
        window.bcontent = editor;
    })
    .catch(error => {
        console.error(error);
    });

const rForm = document.getElementById("rForm");
rForm.addEventListener("submit", e => {
    //서버에 form data를 전송하지 않는다
    e.preventDefault();

    const param = typeof formId == "string" ? formToSerialize(formId) : JSON.stringify(formId);
    const csrfToken = document.querySelector("meta[name='_csrf']").content;
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
    const headers = {"Content-type" : "application/json; charset=utf-8"};
    if (csrfToken) {
        headers[csrfHeader] = csrfToken
    }
    fetch(url, {
        method:"POST",
        body : param,
        headers : headers
    }).then(res => res.json()).then(json => {
        //서버로 부터 받은 결과를 사용해서 처리 루틴 구현
        console.log("json ", json );
        if (handler) handler(json);
    });
});
const myFileFetch = (url, formId, handler) => {
    const form = document.querySelector("#" + formId);
    const param = new FormData(form);
    const csrfToken = document.querySelector("meta[name='_csrf']").content;
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;

    fetch(url, {
        method: "POST",
        headers: {
            [csrfHeader]: csrfToken  // Set CSRF token in header
        },
        body: param,
    }).then(res => res.json()).then(json => {
        console.log("json", json);
        if (handler) handler(json);
    }).catch(err => console.error('Error with the fetch operation:', err));
};


document.addEventListener("DOMContentLoaded", function() {
    const updateForm = document.getElementById("updateForm");

    if (updateForm) {
        updateForm.addEventListener("submit", function(e) {
            e.preventDefault();

            var form = document.getElementById("updateForm");
            var formData = new FormData(form);

            // 사용자가 수정을 확인할 경우
            if (confirm("게시물을 등록하시겠습니까?")) {
                // myFetch 함수를 호출하여 서버에 데이터 전송
                myFetch("/boards/insert", formData, json => {
                    // 응답 처리
                    if (json.status === 0) {
                        alert("게시물이 성공적으로 등록되었습니다.");
                        // 성공 시 /boards/list로 리디렉션
                        window.location.href = "/boards/list";
                    } else {
                        // 실패 시 오류 메시지 표시
                        alert(json.statusMessage);
                    }
                });
            } else {
                // 사용자가 취소할 경우
                console.log("게시물 등록이 취소되었습니다.");
            }
        });
    }
});
