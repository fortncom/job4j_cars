function validate() {
    const answer = "Пожалуйста заполните ";
    let result = answer;
    if ($('#description').val() === '') {
        result += "-Описание ";
    }
    if ($("#markSelection option:selected").val() === '') {
        result += "-Марку ";
    }
    if ($("#modelSelection option:selected").val() === undefined
        || $("#modelSelection option:selected").val() === '') {
        result += "-Модель ";
    }
    if ($('#priceId').val() == 0) {
        result += "-Цену ";
    }
    if (answer !== result) {
        alert(result);
        return false;
    }
    return true;
};
