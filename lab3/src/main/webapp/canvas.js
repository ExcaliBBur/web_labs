let canvas = document.getElementById("chart");
let chart = canvas.getContext('2d');

canvas.addEventListener("mousedown", function (e) {
    getMousePosition(canvas, e);
})

$(document).ready(function () {
    drawHitStatic();
})

function getMousePosition(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    let x = event.clientX - rect.left - canvas.width / 2;
    let y = event.clientY - rect.top - canvas.height / 2;
    let R = getR()
    if (R == null) {
        $('#errorMessage').text("Нет информации о радиусе, невозможно определить координаты по графику");
    } else if (R < 1 || R > 5) {
        $('#errorMessage').text("Радиус не входит в заданный диапазон");
    } else {
        let rateX = (x * R) / 200;
        let rateY = (y * R) / 200;
        sendAndDrawHit(rateX, -rateY, R)
    }
}

function sendAndDrawHit(x, y, R) {
    $('#errorMessage').text("");
    document.getElementById('form_hidden:x_hidden').value = x;
    document.getElementById('form_hidden:y_hidden').value = y;
    document.getElementById('form_hidden:r_hidden').value = R;
    document.getElementById('form_hidden:button_hidden').click();
}

function drawHitStatic() {
    clearCanvas()
    let tableInfo = Array.prototype.map.call(document.querySelectorAll('.answerTable tr'), function (tr) {
        return Array.prototype.map.call(tr.querySelectorAll('td'), function (td) {
            return td.innerHTML;
        });
    });
    let offsetX = canvas.width / 2 - 1
    let offsetY = canvas.height / 2 - 1
    for (let i = 1; i < tableInfo.length; i++) {
        if (tableInfo[i][4] === "Hit") {
            chart.fillStyle = 'green';
        } else {
            chart.fillStyle = "red";
        }
        let R = parseFloat(tableInfo[i][3]);
        let x = parseFloat(tableInfo[i][1]);
        let y = parseFloat(tableInfo[i][2]);
        let rateX = (x / R) * 200;
        let rateY = (y / R) * 200;
        chart.beginPath();
        chart.arc(rateX + offsetX, offsetY - rateY,
            7, 0, 2 * Math.PI);
        chart.fill();
    }
}

function clearCanvas() {
    chart.clearRect(0, 0, canvas.width, canvas.height)
}