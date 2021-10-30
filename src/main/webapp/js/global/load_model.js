function loadModel() {
    let id = parseInt($("#markSelection option:selected").val());
    $.ajax({
        cache: false,
        type: 'GET',
        url: '/car_trade/car.do',
        dataType: 'json'
    }).done(function(data) {
        let map = new Map();
        for (let i = 0; i < data.length; i++) {
            if (data[i].mark.id === id) {
                map.set(data[i].model.id, data[i].model.name);
            }
        }

        let result = "";
        result += "<select class=\"form-control\" id=\"modelSelection\">"
            + "<option selected disabled value=\"\">Выберите модель</option>";

        for (const key of map.keys()) {
            result += '<option value=\"' + key + '\">' + map.get(key) + '</option>';
        }
        result += "</select>";
        document.getElementById('modelSelection').innerHTML = result;
    }).fail(function (err) {
        alert("err: " + err);
    });
};