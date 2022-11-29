let graph = new Vue({
    el: "#graph",
    data: {
        mobile: false,
        tablet: false,
        desktop: false,
        width: null,
        height: null
    },
    created: function () {
        let width = window.innerWidth
        if (width >= 1061) {
            this.desktop = true
            this.tablet = false
            this.mobile = false
        }
        if (width <= 1061 && width >= 886) {
            this.desktop = false
            this.tablet = true
            this.mobile = false
        }
        if (width < 886) {
            this.desktop = false
            this.tablet = false
            this.mobile = true
        }
        if (this.desktop) {
            this.width = "600px"
            this.height = "600px"
        }
        if (this.tablet) {
            this.width = "500px"
            this.height = "500px"
        }
        if (this.mobile) {
            this.width = "450px"
            this.height = "450px"
        }
    }
})

addEventListener("resize", (event) => {
    let width = window.innerWidth
    if (width >= 1061) {
        graph.$data.desktop = true
        graph.$data.tablet = false
        graph.$data.mobile = false
        clearCanvas()
        drawHits(results.$data.hits)
    }
    if (width <= 1061 && width >= 886) {
        graph.$data.desktop = false
        graph.$data.tablet = true
        graph.$data.mobile = false
        clearCanvas()
        drawHits(results.$data.hits)
    }
    if (width < 886) {
        graph.$data.desktop = false
        graph.$data.tablet = false
        graph.$data.mobile = true
        clearCanvas()
        drawHits(results.$data.hits)
    }
    if (graph.$data.desktop) {
        graph.$data.width = "600px"
        graph.$data.height = "600px"
    }
    if (graph.$data.tablet) {
        graph.$data.width = "500px"
        graph.$data.height = "500px"
    }
    if (graph.$data.mobile) {
        graph.$data.width = "350px"
        graph.$data.height = "350px"
    }
});
