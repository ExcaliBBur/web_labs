new Vue({
    el:"#graph",
    data: {
        width: window.innerWidth/16*5 + "px",
        height: window.innerHeight/13*8 + "px",
        viewBox: "0 0 " + window.innerWidth/16*5 + " " + window.innerHeight/13*8
    },
})
console.log(window.innerWidth)
console.log(window.innerHeight)
let canvas = document.getElementById("chart");
let chart = canvas.getContext('2d');
let offsetX = canvas.width / 2
let offsetY = canvas.height / 2

canvas.addEventListener("mousedown", function (e) {
    getMousePosition(canvas, e);
})

function getMousePosition(canvas, event) {
    form.$data.errorMessage = null;
    let rect = canvas.getBoundingClientRect();
    let x = event.clientX - rect.left - canvas.width / 2;
    let y = event.clientY - rect.top - canvas.height / 2;
    let R = form.$data.r
    if (R == null) {
        form.$data.errorMessage = "Нет информации о радиусе, невозможно определить координаты по графику"
    }
    else if (R <= 0 || R >= 5) {
        form.$data.errorMessage = "Радиус не входит в заданный диапазон"
    } else {
        let rateX = (x * R) / 200;
        let rateY = (y * R) / 200;
        postReq(rateX, -rateY, R)
    }
}

function drawHits(hits)
{
    let r = hits[0].r
    hits.forEach(hit => {
        let rateX = (hit.x / r) * 200;
        let rateY = (hit.y / r) * 200;
        if (hit.hit === "Hit") {
            chart.fillStyle = 'green';
        } else {
            chart.fillStyle = "red";
        }
        chart.beginPath();
        chart.arc(rateX + offsetX, offsetY - rateY,
            7, 0, 2 * Math.PI);
        chart.fill();
    })
}

function clearCanvas() {
    chart.clearRect(0, 0, canvas.width, canvas.height)
}
