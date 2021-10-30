function loadPhoto(path) {
    let name = path.split("=")[1].split(".")[0];
    let result = "<li value=" + name + " class=\"nav-item\">"
        + "<label style=\"width: 180px;\" >"
        + "<img src=" + path
        + " width=\"150px\" height=\"150px\"/>"
        + "</label>"
        + "</li>";
    $("#photoList").append(result);
    setFileName(name);
};


function setFileName(path) {
    let photo = $('#advPhoto').val();
    let s = "";
    if (photo === "") {
        s = path;
    } else {
        s = photo + "|" + path;
    }
    document.getElementById('advPhoto').value = s;
};

