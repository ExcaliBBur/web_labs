<template>
    <tr v-for="hit in hits" v-bind:key="hit">
        <td> {{hit.curTime}}</td>
        <td> {{hit.x}}</td>
        <td> {{hit.y}}</td>
        <td> {{hit.r}}</td>
        <td> {{hit.hit}}</td>
        <td> {{hit.workTime}} ms</td>
    </tr>
</template>

<script>
    export default {
        inject: ['hits']
    }
</script>