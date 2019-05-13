<template>
  <div
    class="gameboard-letter" :style="changePressedLetterStyle"
    v-on:click="selectLetters"
  >
    <ion-label>{{ letter }}</ion-label>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "GameBoardLetter",
  props: ["letter", "letterIndex"],
  data() {
    return {};
  },
  methods: {

    selectLetters() {
      var payload = { letter: this.letter, letterIndex: this.letterIndex };
      var lastMoveIndex = this.getWord.letterIndexList[this.getWord.letterIndexList.length - 1];
      
      if(this.getInvalidMove && this.getWord.letterIndexList.length == 0){
        this.$store.commit("adjustInvalidMove", false);
      }
      if (
        this.getWord.letterIndexList.includes(this.letterIndex) &&
        !this.getInvalidMove && this.letterIndex == lastMoveIndex
      ) {
        let indexOfLetter = this.getWord.letterIndexList.length - 1;
        this.silceWord(indexOfLetter);
        return;
      }
      if (
        this.getWord.letterIndexList.includes(this.letterIndex) &&
        !this.getInvalidMove && this.letterIndex != lastMoveIndex
      ) {
        let indexOfLetter = this.getWord.letterIndexList.indexOf(this.letterIndex) +1;
        this.silceWord(indexOfLetter);
        return;
      }
      this.checkMoveValidity(lastMoveIndex, payload);
    },
    silceWord(indexOfLetter){
      let letterIndexList = this.getWord.letterIndexList.slice(0, indexOfLetter);
      let wordList = this.getWord.word.slice(0, indexOfLetter);
      this.$store.commit("removeLetter", { LetterIndexList: letterIndexList, WordList: wordList });
    },
    checkMoveValidity(lastMoveIndex, payload){
      if (
        !this.getWord.letterIndexList.includes(this.letterIndex) &&
        !this.getInvalidMove
      ) {
        if (
          this.getWord.letterIndexList.length === 0 ||
          lastMoveIndex === payload.letterIndex + 1 ||
          lastMoveIndex === payload.letterIndex - 1 ||
          lastMoveIndex === payload.letterIndex + 4 ||
          lastMoveIndex === payload.letterIndex - 4 ||
          lastMoveIndex === payload.letterIndex + 5 ||
          lastMoveIndex === payload.letterIndex - 5 ||
          lastMoveIndex === payload.letterIndex + 6 ||
          lastMoveIndex === payload.letterIndex - 6
        ) {
          this.$store.commit("addLetter", payload);
        } else {
        this.$store.commit("adjustInvalidMove", true);
        }
      } 
    }
  },
  computed: {
    ...mapGetters(["getWord", "getInvalidMove"]),
    changePressedLetterStyle(){
      return {
        backgroundColor : this.getWord.letterIndexList.includes(this.letterIndex)  ? '#3880FF' : '#00A591'
      }
    }
  }
};
</script>

<style>
.gameboard-letter {
  background-color: 3880FF;
  color: white;
  border: 1px solid;
  width: 2em;
  height: 2em;
  display: flex;
  justify-content: center;
  line-height: 2em;
  user-select: none;
  opacity: 0.9;
  transition: 0.3s;
  margin: 3px;
}

.gameboard-letter:hover {
  opacity: 1;
}
</style>
