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
