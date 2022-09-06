let x, y, R;
let flag;
let calculateDigitsAfterPoint = x => ((x.toString().includes('.')) ?
    (x.toString().split('.').pop().length) : (0));

function handleFormSubmit(event) {
    flag = false;
    event.preventDefault();
    $('#errorMessage').text("")
    validateR();
    validateY();
    validateX();
}

function validateX() {
    x = $('input[name="X"]:checked').val()
    if (x != null) {
        if (!flag) {
            $.ajax({
                url: "handler.php",
                type: "get",
                data: {'x': x, 'y': y, 'R': R},
                cache: false,
                dataType: 'html',
                success: function (result) {
                    $('#rows').html(result);
                }
            });
        }
    } else sendError("X некорректен")
}

function validateY() {
    y = $('#text_input').val().replace(",", ".");
    if (y !== "" && y >= -3 && y <= 5 && calculateDigitsAfterPoint(y) < 10) {

    } else if (calculateDigitsAfterPoint(y) >= 10) {
        sendError("Введите меньше 10 знаков после запятой у Y!")
    } else sendError("Y некорректен")
}

function validateR() {
    R = $('input[name="R"]:checked').val()
    if ((R != null) &&
        ($('input[name="R"]:checkbox:checked').length === 1)) {
    } else sendError("R некорректен")
}

function sendError(message) {
    $('#errorMessage').text(message);
    $('#result').text("");
    flag = true;
}

const applicationForm = document.getElementById('answer');
applicationForm.addEventListener("submit", handleFormSubmit);