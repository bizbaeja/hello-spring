function deleteBoard() {
    var boardid = document.getElementById("boardid").value; // 'userid'가 아니라 'boardid'가 맞습니다.

    if (confirm("삭제하시겠습니까?")) {
        fetch('/boards/delete?boardid=' + boardid, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(json => {
                if (json.status === 0) {
                    alert("정상적으로 삭제 되었습니다.");
                    window.location.href = "/boards/list";
                } else {
                    alert(json.statusMessage);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("오류가 발생했습니다.");
            });
    } else {
        console.log("User cancelled the deletion.");
    }
}


document.addEventListener("DOMContentLoaded", function() {
    const viewForm = document.getElementById("viewForm");
    if (viewForm) {
        viewForm.addEventListener("submit", e =>  {
            var userid = document.getElementById("userid").value;
            var form = document.getElementById("viewForm");
            form.action.value = "update";
            var formData = new FormData(form);
            e.preventDefault();
            if (confirm("수정하시겠습니까?")) {
                // myFetch call to save the data
                myFetch("user.do", "viewForm", json => {
                    
                    if (json.status === 0) {
                        // Data save successful, alert and redirect
                        alert("정상적으로 수되었습니다.");
                        window.location.href = "user.do?action=read&userid=" + userid;
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

