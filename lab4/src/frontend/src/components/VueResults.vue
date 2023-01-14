<template>
    <tr v-for="dataBaseHit in dataBaseHits" v-bind:key="dataBaseHit">
        <td> {{dataBaseHit.curTime}}</td>
        <td> {{dataBaseHit.x}}</td>
        <td> {{dataBaseHit.y}}</td>
        <td> {{dataBaseHit.r}}</td>
        <td> {{dataBaseHit.hit}}</td>
        <td> {{dataBaseHit.workTime}} ms</td>
    </tr>
</template>

<script>

    export default {
        props: ['dataBaseHits']
    }
</script>