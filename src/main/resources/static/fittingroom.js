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

    if (sampleTopImageIndex === i) {
        sampleTopImageIndex = null;
        sampleTopImageUrl = null;
        document.getElementById("composeSampleTopImageUrl").value="";
        for (var j=0; j<=11; j++) {
            checks[j].style.display="none";
        }
    } else {
            sampleTopImageIndex = i;
            sampleTopImageUrl = topClothes[i].src;
            document.getElementById("composeSampleTopImageUrl").value = topClothes[i].src;
            for (var j=0; j<=11; j++) {
                if (i === j) {
                    var topClothesLocation = topClothes[i].getBoundingClientRect();
                    checks[j].style.top = window.scrollY + topClothesLocation.top + 'px';
                    checks[j].style.left = topClothesLocation.left + 'px';
                    checks[j].style.display = 'block';
                    checks[j].style.opacity = "0.5";
                } else {
                    checks[j].style.display="none";
                }
        }
    }
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

    if (sampleBottomImageIndex === i) {
        sampleBottomImageIndex = null;
        sampleBottomImageUrl = null;
        document.getElementById("composeSampleBottomImageUrl").value="";
        for (var j=0; j<=11; j++) {
            checks[j].style.display="none";
        }
    } else {
        sampleBottomImageIndex = i;
        sampleBottomImageUrl = bottomClothes[i].src;
        document.getElementById("composeSampleBottomImageUrl").value = bottomClothes[i].src;
        for (var j=0; j<=11; j++) {
            if (i === j) {
                var bottomClothesLocation = bottomClothes[i].getBoundingClientRect();
                checks[j].style.top = window.scrollY + bottomClothesLocation.top + 'px';
                checks[j].style.left = bottomClothesLocation.left + 'px';
                checks[j].style.display = 'block';
                checks[j].style.opacity = "0.5";
            } else {
                checks[j].style.display="none";
            }
        }
    }
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



