window.onload = function() {
    document.getElementById("closet").style.display="block";
    document.getElementById("clothesUpload").style.display="none";
}

function showClothes() {
    document.getElementById("closet").style.display = "block";
    document.getElementById("clothesUpload").style.display = "none";
}

function uploadClothes() {
    document.getElementById("closet").style.display = "none";
    document.getElementById("clothesUpload").style.display = "block";
}