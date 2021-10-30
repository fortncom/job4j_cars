function validate() {
    const answer = "Пожалуйста заполните ";
    let result = answer;
    if ($('#emailField').val() === '') {
        result += "-Имя ";
    }
    if ($('#passwordField').val() === '') {
        result += "-Пароль";
    }
    if (answer !== result) {
        alert(result);
        return false;
    }
    return true;
}