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
            url: "servlets.ControllerServlet",
            type: "get",
            data: {'x': x, 'y': y, 'R': R, 'timeZone': Intl.DateTimeFormat().resolvedOptions().timeZone,
                "isCanvas": false},
            success: function (result) {
                $('#rows').html(result);
                chart.clearRect(0, 0, canvas.width, canvas.height)
                drawHitStatic()
            }
        });
    }
}

function validateX() {
    x = $('input[name="X"]:checked').val()
    if (x == null) sendError("X некорректен")
}

function validateY() {
    y = Number($('#text_input_y').val().replace(",", "."));
    if (y === "" || y <= -3 || y >= 3 || !Number.isFinite(y)) sendError("Y некорректен");
    else if (calculateDigitsAfterPoint(y) >= 7) sendError("Введите меньше 7 знаков после запятой у У!")
}

function validateR() {
    R = $('input[name="R"]:checked').val()
    if (R == null) sendError("R некорректен")
}

function sendError(message) {
    $('#errorMessage').text(message);
    isError = true;
}

const applicationForm = document.getElementById('answer');
applicationForm.addEventListener("submit", handleFormSubmit);