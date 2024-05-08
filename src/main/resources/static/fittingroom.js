function personUpload() {
    var formData = new FormData();
    var personImageInput = document.getElementById("personImageUrl");
    formData.append("personImageUrl", personImageInput.files[0]);
    var categoryInput = document.getElementById("category");
    formData.append("category", categoryInput.value);
    var pageNumInput = document.getElementById("pageNum");
    formData.append("pageNum", pageNumInput.value);
    var colorInput = document.getElementById("color");
    formData.append("color", colorInput.value);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/fittingroom/uploadPersonImage');
    xhr.onload = function() {
        if (xhr.status === 200) {
            console.log('이미지 업로드 성공');
        } else {
            console.log('이미지 업로드 실패');
        }
    };
    xhr.send(formData);
}

function showClothes() {
    document.getElementById("closet").style.display="block";
    document.getElementById("clothesUpload").style.display="none";
}

function uploadClothes() {
    document.getElementById("closet").style.display = "none";
    document.getElementById("clothesUpload").style.display = "block";
}
