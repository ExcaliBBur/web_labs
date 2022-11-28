
let staticResults = new Vue({
    el: "#staticResults",
    template: '<staticResults :hits="hits" />',
    data: {
        hits: []
    }
})

Vue.component('hits', {
  props: ['hits'],
  template:
      '<tr v-for="hit in hits.reverse()"> ' +
          '<td>  {{hit.curTime}} </td> ' +
          '<td>  {{hit.x}}  </td> ' +
          '<td>  {{hit.y}}  </td>'  +
          '<td>  {{hit.R}}  </td> ' +
          '<td>  {{hit.hit}}  </td> ' +
          '<td>  {{hit.workTime}}  ms</td>'  +
      '</tr>',
})

let results = new Vue({
    el:"#results",
    data: {
        hits: []
    }
})
