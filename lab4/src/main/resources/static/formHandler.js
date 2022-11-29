let sendHit = Vue.resource('/hit')

let form = new Vue({
    el: "#content-form",
    data: {
        x: null,
        y: null,
        r: null,
        errorMessage: null,
    },
    methods: {
        validateLength: function () {
            if (this.x != null && this.x.length >= 8) this.x = this.x.substr(0, 7)
            if (this.y != null && this.y.length >= 8) this.y = this.y.substr(0, 7)
            if (this.r != null && this.r.length >= 8) this.r = this.r.substr(0, 7)
        },
        addHit: function() {
            form.$data.errorMessage = null
            let x = this.x.replace(",", ".")
            let y = this.y.replace(",", ".")
            let r = this.r.replace(",", ".")
            if (r == null || r <= 0 || r >= 5) form.$data.errorMessage = "Введён неправильный R"
            if (y == null || y <= -5 || y >= 5) form.$data.errorMessage = "Введён неправильный Y"
            if (x == null || x <= -3 || x >= 5) form.$data.errorMessage = "Введён неправильный X"
            if (form.$data.errorMessage != null) return;

            postReq(x, y, r)
        }
    },
    created: function () {
        sendHit.get().then(
            result => result.json().then(
                data => {
                    results.$data.hits = data.reverse()
                    drawHits(results.$data.hits)
        }));
    }
})

let results = new Vue({
    el:"#results",
    data: {
        hits: []
    }
})

function postReq(x, y, r) {
    let hit = {
        x: x,
        y: y,
        r: r
    }
    sendHit.save({}, hit).then(
        result => result.json().then(
            data => {
                results.$data.hits = data.reverse()
                clearCanvas()
                drawHits(results.$data.hits)
            }, error => {
                console.log(error.body.error)
            },
            error => {
                console.log(error.body.error)
            })
    )
}