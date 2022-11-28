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
            let x = this.x
            let y = this.y
            let r = this.r
            if (r == null || r <= 0 || r >= 5) form.$data.errorMessage = "Введён неправильный R"
            if (y == null || y <= -5 || y >= 5) form.$data.errorMessage = "Введён неправильный Y"
            if (x == null || x <= -3 || x >= 5) form.$data.errorMessage = "Введён неправильный X"
            if (form.$data.errorMessage != null) return;
            let hit = {
                x: x,
                y: y,
                r: r
            }
            sendHit.save({}, hit).then(
                result => result.json().then(
                    data => {
                        results.$data.hits.unshift(data)
                    }, error => {
                        dynamicResults.response = false;
                        dynamicResults.lastHit = [];
                        console.log(error.body.error)
                    },
                error => {
                    console.log(error.body.error)
                })
            )
        }
    },
    created: function () {
        sendHit.get().then(
            result => result.json().then(
                data => {
                    data = data.reverse()
                    results.$data.hits = data
        }));
    }
})
