let canvas = document.getElementById("chart");
let chart = canvas.getContext('2d');
let tableInfo;
let time = []
let timeOut = 200

canvas.addEventListener("mousedown", function (e) {
    getMousePosition(canvas, e);
})


$('#contain').on('mousedown', '.paginate_button', function () {
    setTimeout(drawHitStatic,timeOut)
});

$(document).ready(function () {
    $('#example').DataTable({
        "ordering": false,
        "searching": false,
        "bDestroy": true,
        "stateSave": true,
    })
    drawHitStatic(false);
    $('#example').on( 'length.dt', function () {
        setTimeout(drawHitStatic,timeOut)
    });
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

function drawHitStatic(updateTable) {
    if (updateTable) {
        $('#example').DataTable({
            "ordering": false,
            "searching": false,
            "bDestroy": true,
            "stateSave": true
        })
        $('#example').on( 'length.dt', function () {
            setTimeout(drawHitStatic,100)
        });
    }
    clearCanvas();
    setTableInfo();
    let offsetX = canvas.width / 2 - 1
    let offsetY = canvas.height / 2 - 1
    for (let i = 0; i < tableInfo.length; i++) {
        if (tableInfo[i][4] === "Hit") {
            chart.fillStyle = 'green';
        } else {
            chart.fillStyle = "red";
        }
        let R = parseFloat(tableInfo[0][3]);
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

function setTableInfo() {
    tableInfo = Array.prototype.map.call(document.querySelectorAll('#rows tr'), function (tr) {
        return Array.prototype.map.call(tr.querySelectorAll('td'), function (td) {
            return td.innerHTML;
        });
    });
    time.push(tableInfo[0][0])
}
