function filter() {
    let priceMin = $("#min").val();
    let priceMax = $("#max").val();
    if (priceMax <= priceMin || priceMax === 0) {
        priceMin = 0;
        priceMax = 999999999;
    }
    let form = document.createElement("form");
    document.body.appendChild(form);
    $(form).append("<input type='hidden' name='mark' value=" + $("#markSelection option:selected").val() + ">");
    $(form).append("<input type='hidden' name='model' value=" + $("#modelSelection option:selected").val() + ">");
    $(form).append("<input type='hidden' name='minPrice' value=" + priceMin + ">");
    $(form).append("<input type='hidden' name='maxPrice' value=" + priceMax + ">");
    form.action="http://localhost:8080/car_trade/index.do";
    form.method="post";
    form.submit();
    form.remove();
};

$(document).ready(function () {
    load_mark();
});