function addPhoto() {
    let file = $('#image_file')[0].files[0];
    if (file) {
        let name = file.name.split(".")[0];
        let fd = new FormData;
        fd.append('img', file);
        $.ajax({
            url: "/car_trade/photoUpload.do?name=" + name + ".jpg",
            data: fd,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function () {
                loadPhoto("/car_trade/download.do?name=" + name + ".jpg");
            }
        });
    }
};