function load_mark() {
    $.ajax({
        cache: false,
        type: 'GET',
        url: '/car_trade/car.do',
        dataType: 'json'
    }).done(function(data) {
        let result = "";
        result += "<select class=\"form-control\" name=\"mark\" id=\"markSelection\" onchange=\"loadModel();\">"
            + "<option selected disabled value=\"\">Выберите марку</option>";
        for (let i = 0; i < data.length; i++) {
            result += '<option value=\"' + data[i].mark.id + '\">' + data[i].mark.name + '</option>';
        }
        result += "</select>";
        document.getElementById('markSelection').innerHTML = result;
    }).fail(function (err) {
        alert("err: " + err);
    });
};
