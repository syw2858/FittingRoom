window.onload = function() {
    if (document.querySelector(".isSample").value == "true" || document.querySelector(".isSample").value == "") {
        document.getElementById("closet").style.display="block";
        document.getElementById("clothesUpload").style.display="none";
        document.querySelector(".sampleClothes").style.backgroundColor = "black";
        document.querySelector(".sampleClothes").style.color = "white";
        document.querySelector(".uploadClothes").style.backgroundColor = "white";
        document.querySelector(".uploadClothes").style.color = "black";
    }
    else {
        document.getElementById("closet").style.display="none";
        document.getElementById("clothesUpload").style.display="block";
        document.querySelector(".sampleClothes").style.backgroundColor = "white";
        document.querySelector(".sampleClothes").style.color = "black";
        document.querySelector(".uploadClothes").style.backgroundColor = "black";
        document.querySelector(".uploadClothes").style.color = "white";
    }
}

// function personImageUpload() {
//     document.getElementById("personImage").onsubmit;
// }

function showClothes() {
    document.getElementById("closet").style.display = "block";
    document.getElementById("clothesUpload").style.display = "none";
    document.querySelector(".sampleClothes").style.backgroundColor = "black";
    document.querySelector(".sampleClothes").style.color = "white";
    document.querySelector(".uploadClothes").style.backgroundColor = "white";
    document.querySelector(".uploadClothes").style.color = "black";
    var inputIsSampleList = document.getElementsByClassName("isSample");
    for(var i = 0; i < inputIsSampleList.length; i++) {
        inputIsSampleList[i].value=true;
    }
    document.getElementById("composeIsSample").value=true;
}

function uploadClothes() {
    document.getElementById("closet").style.display = "none";
    document.getElementById("clothesUpload").style.display = "block";
    document.querySelector(".sampleClothes").style.backgroundColor = "white";
    document.querySelector(".sampleClothes").style.color = "black";
    document.querySelector(".uploadClothes").style.backgroundColor = "black";
    document.querySelector(".uploadClothes").style.color = "white";
    var inputIsSampleList = document.getElementsByClassName("isSample");
    for(var i = 0; i < inputIsSampleList.length; i++) {
        inputIsSampleList[i].value=false;
    }
    document.getElementById("composeIsSample").value=false;
}

function topClothesSelect(i) {
    var table = document.querySelector("#sampleTopClothes");
    var topClothes = table.querySelectorAll('img');

    sampleTopImageUrl = topClothes[i].src;
    document.getElementById("composeSampleTopImageUrl").value = topClothes[i].src;
}

function bottomClothesSelect(i) {
    var table = document.querySelector("#sampleBottomClothes");
    var bottomClothes = table.querySelectorAll('img');

    sampleBottomImageUrl = bottomClothes[i].src;
    document.getElementById("composeSampleBottomImageUrl").value = bottomClothes[i].src;
}