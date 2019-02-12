<template>
    <div>
        <h2 align="left">Total score : {{ getTotalScore }}</h2>
        <table class="table">
            <thead>
                <th>Guessed words</th>
                <th>Score</th>
            </thead>
            <tbody>
                <tr v-for="Word in getWordList" :key="Word.state">
                <td>{{ Word.word }}</td>
                <td>{{ Word.score }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
    name: "WordBoard",
    data() {
        return {};
    },
    computed: {
        ...mapGetters([
            "getWordList",
            "getTotalScore",
        ])
    }
};
</script>

<style>
</style>
