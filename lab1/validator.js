let x, y, R;
let isError;

function calculateDigitsAfterPoint(x) {
    if (x.toString().includes('.')) return x.toString().split('.').pop().length;
    return 0;
}

function handleFormSubmit(event) {
    isError = false;
    event.preventDefault();
    $('#errorMessage').text("")
    validateR();
    validateY();
    validateX();
    if (!isError) {
        $.ajax({
            url: "handler.php",
            type: "get",
            data: {'x': x, 'y': y, 'R': R},
            success: function (result) {
                $('#rows').html(result);
            }
        });
    }
}

function validateX() {
    x = $('input[name="X"]:checked').val()
    if (x == null) sendError("X некорректен")
}

function validateY() {
    y = Number($('#text_input').val().replace(",", "."));
    if (y === "" || y <= -3 || y >= 5 || !Number.isFinite(y)) sendError("Y некорректен");
    else if (calculateDigitsAfterPoint(y) >= 10) sendError("Введите меньше 10 знаков после запятой у У!")
}

function validateR() {
    R = $('input[name="R"]:checked').val()
    if ((R == null) || ($('input[name="R"]:checkbox:checked').length !== 1)) sendError("R некорректен")
}

function sendError(message) {
    $('#errorMessage').text(message);
    isError = true;
}

const applicationForm = document.getElementById('answer');
applicationForm.addEventListener("submit", handleFormSubmit);