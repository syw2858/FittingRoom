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
    personImageUrl = document.getElementById("personImageUrl").value;
}

function deletePersonImage() {
    var url = new URL(window.location.href);

    url.searchParams.delete("personImageUrl");
    window.history.replaceState(null, '', url.toString());
    document.getElementsByClassName("personImage")[0].src="";
    window.location.reload();
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

function deleteTopClothesImage() {
    var url = new URL(window.location.href);

    url.searchParams.delete("topImageUrl");
    window.history.replaceState(null, '', url.toString());
    document.getElementsByClassName("topClothesImage")[0].src="";
    window.location.reload();
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

function deleteBottomClothesImage() {
    var url = new URL(window.location.href);

    url.searchParams.delete("bottomImageUrl");
    window.history.replaceState(null, '', url.toString());
    document.getElementsByClassName("bottomClothesImage")[0].src="";
    window.location.reload();
}

function uploadTopClothes() {
    document.getElementById("uploadTopClothes").submit();
    topImageUrl = document.getElementById("topImageUrl").value;
}

function uploadBottomClothes() {
    document.getElementById("uploadBottomClothes").submit();
    bottomImageUrl = document.getElementById("bottomImageUrl").value;
}

function checkAllImage() {
    var button = document.getElementById("composeBtn");
    var isSample = document.querySelector("isSample").value;
    if (personImageUrl == null) {
        button.disabled=true;
    } else {
        button.disabled=false;
    }
    if (isSample == "true" || isSample == "") {
        if (sampleTopImageUrl == null) {
            if (sampleBottomImageUrl == null) {
                button.disabled=true;
            } else {
                button.disabled=false;
            }
        } else {
            button.disabled=false;
        }
    } else {
        if (topImageUrl == null) {
            if (bottomImageUrl == null) {
                button.disabled=true;
            } else {
                button.disabled=false;
            }
        } else {
            button.disabled=false;
        }
    }
}