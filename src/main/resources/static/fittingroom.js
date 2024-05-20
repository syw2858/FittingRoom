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

function personImageUpload() {
    document.getElementById("personImageUpload").submit();
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
    var topClothes = table.querySelectorAll('.clothesImage');
    var checks = table.querySelectorAll(".checkClothesImage");

    sampleTopImageUrl = topClothes[i].src;
    document.getElementById("composeSampleTopImageUrl").value = topClothes[i].src;
    checks.forEach(function(check) {
        var isImageChecked = check.getAttribute('data-checked');
        if (isImageChecked === 'true') {
            check.style.display = 'none';
            check.setAttribute('data-checked', 'false');
        } else {
            var topClothesLocation = topClothes[i].getBoundingClientRect();
            check.style.top = window.scrollY + topClothesLocation.top + 'px';
            check.style.left = topClothesLocation.left + 'px';
            check.style.display = 'block';
            check.style.opacity = "0.3";
            check.setAttribute('data-checked', 'true');
        }
    });
}

function bottomClothesSelect(i) {
    var table = document.querySelector("#sampleBottomClothes");
    var bottomClothes = table.querySelectorAll('.clothesImage');
    var checks = table.querySelectorAll(".checkClothesImage");

    sampleBottomImageUrl = bottomClothes[i].src;
    document.getElementById("composeSampleBottomImageUrl").value = bottomClothes[i].src;
    checks.forEach(function(check) {
        var isImageChecked = check.getAttribute('data-checked');
        if (isImageChecked === 'true') {
            check.style.display = 'none';
            check.setAttribute('data-checked', 'false');
        } else {
            var bottomClothesLocation = bottomClothes[i].getBoundingClientRect();
            check.style.top = window.scrollY + bottomClothesLocation.top + 'px';
            check.style.left = bottomClothesLocation.left + 'px';
            check.style.display = 'block';
            check.style.opacity = "0.3";
            check.setAttribute('data-checked', 'true');
        }
    });
}

function uploadTopClothes() {
    document.getElementById("uploadTopClothes").submit();
}

function uploadBottomClothes() {
    document.getElementById("uploadBottomClothes").submit();
}