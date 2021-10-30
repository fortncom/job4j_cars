$(document).ready(function () {
    let advId = $("#advId").val();
    if (advId === "0") {
        load_mark();
    }
    $('#photoList li').each(function() {
        let v = "" + $(this).attr('value');
        setFileName(v);
    });
});
