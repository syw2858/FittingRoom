window.onload = function() {
    if (document.querySelector(".isSample").value == "true") {
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
}

function clothesSelect() {
    document.querySelector(".clothesImage").style.opacity = "0.5";
}