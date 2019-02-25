<template>
    <div>
        <h2>Current word</h2>
        <h3 class= "currentWord"> {{getWord.word}} </h3>   
        <Timer/>
        <h4> {{ detectInvalidMove() }} </h4>
        <h4> {{ detectShortWord() }} </h4>
    </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import Timer from "./Timer";
export default {
    name: "InformationBoard",
    components: { Timer },
    data() {
        return {};
    },
    methods: {
        detectInvalidMove() {
            if (this.getInvalidMove === true)
                return "Invalid Move!";
    },
        detectShortWord() {
            if(this.getWord.word.length == 1){
                return "Word is too short";
            }
        }
  },
    computed: {
        ...mapGetters([
            "getWord",
            "getInvalidMove"
        ])
    }
}
</script>

<style>
    table {
        display: block;
        overflow: auto;
        height: 300px;
        width: 300px;
    }
    h3.currentWord {
	display: flex;
	align-items: flex-start;
	justify-content: center;
	height: 30px;
}
</style>
