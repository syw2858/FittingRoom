function showClothes() {
    // "샘플" 버튼의 스타일 변경
    document.querySelector(".sampleClothes").style.backgroundColor = "black";
    document.querySelector(".sampleClothes").style.color = "white";
    // "내 PC" 버튼의 스타일 변경
    document.querySelector(".uploadClothes").style.backgroundColor = "white";
    document.querySelector(".uploadClothes").style.color = "black";

    // 상의, 하의 선택 영역 표시
    document.getElementById("closet").classList.remove("display-none")
    document.getElementById("clothesUpload").classList.add("display-none")
}

function uploadClothes() {
    // "샘플" 버튼의 스타일 변경
    document.querySelector(".sampleClothes").style.backgroundColor = "white";
    document.querySelector(".sampleClothes").style.color = "black";
    // "내 PC" 버튼의 스타일 변경
    document.querySelector(".uploadClothes").style.backgroundColor = "black";
    document.querySelector(".uploadClothes").style.color = "white";

    // 상의, 하의 업로드 영역 표시
    document.getElementById("closet").classList.add("display-none")
    document.getElementById("clothesUpload").classList.remove("display-none")
}
