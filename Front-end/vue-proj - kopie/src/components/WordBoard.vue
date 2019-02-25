<template>
    <div>
        <table class="table">
            <thead>
                <th>Guessed words</th>
                <th>Score</th>
                <th>Valid</th>
            </thead>
            <tbody>
                <tr v-for="Word in getWordList" :key="Word.state" >
                <td>{{ Word.word }}</td>
                <td>{{ Word.score }}</td>
                <td > 
                    <img
                        width="30"
                        height="30"
                        v-if="Word.isValidWord == true"
                        src="@/assets/ok.png"
                    />
                    <img
                        width="30"
                        height="30"
                        v-if="Word.isValidWord == false"
                        src="@/assets/star.png"
                    />
                    <LoadingIcon
                        v-if="Word.isValidWord == undefined"
                    />
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import LoadingIcon from "../assets/LoadingIcon";
export default {
    name: "WordBoard",
    components: { LoadingIcon },
    data() {
        return {};
    },
    computed: {
        ...mapGetters([
            "getWordList",
        ])
    }
};
</script>

<style>
th {
  color: white;
}
td {
  color: white;
}
</style>
