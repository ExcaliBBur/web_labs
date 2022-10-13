let x, y, R;
let isError;

function validateX() {
    x = $('.input_x option:selected').val();
    if (x == null) sendError("X некорректен")
}

function validateY() {
    y = Number($('.input_y').val().replace(",", "."));
    if (y === "" || y <= -5 || y >= 5 || !Number.isFinite(y)) sendError("Y некорректен");
}

function validateR() {
    R = Number($('.input_R').val().replace(",", "."));
    if (R === "" || R < 1 || R > 5 || !Number.isFinite(R)) sendError("R некорректен")
}

function sendError(message) {
    $('#errorMessage').text(message);
    isError = true;
}


$('.submit').on('click', function (event){
    isError = false;
    event.preventDefault();
    $('#errorMessage').text("")
    validateR();
    validateY();
    validateX();
    if (!isError) {
        document.getElementById('form_hidden:x_hidden').value = x;
        document.getElementById('form_hidden:y_hidden').value = y;
        document.getElementById('form_hidden:r_hidden').value = R;
        document.getElementById('form_hidden:button_hidden').click();
    }
})


function getR() {
    return Number($('.input_R').val().replace(",", "."));
}