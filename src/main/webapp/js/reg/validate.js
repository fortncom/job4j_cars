function validate() {
    const answer = "Пожалуйста заполните ";
    let result = answer;
    if ($('#nameField').val() === '') {
        result += "-Имя ";
    }
    if ($('#emailField').val() === '') {
        result += "-Почту ";
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