<template>
  <div
    class="gameboard-letter" :style="changePressedLetterStyle"
    v-on="capturing ? { mouseover: () => selectLetters() } : {} "
  >
    <p id="p2">{{ letter }}</p>

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

      if (
        this.getWord.letterIndexList.includes(this.letterIndex) &&
        !this.getInvalidMove && this.letterIndex != lastMoveIndex
      ) {
        let indexOfLetter = this.getWord.letterIndexList.indexOf(this.letterIndex) +1;

        let letterIndexList = this.getWord.letterIndexList.slice(0,
          this.getWord.letterIndexList.indexOf(indexOfLetter));

        let wordList = this.getWord.word.slice(0, indexOfLetter);

        this.$store.commit("removeLetter", { LetterIndexList: letterIndexList, WordList: wordList });
      }

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
    },
  },
  computed: {
    ...mapGetters(["getWord", "getInvalidMove", "getCapturing"]),
    capturing() {
      return this.$store.state.capturing;
    },
    changePressedLetterStyle(){
      return {
        backgroundColor : this.getWord.letterIndexList.includes(this.letterIndex)  ? '#00A591' : '#FF6F61'
      }
    }
  }
};
</script>

<style>
.gameboard-letter {
  background-color: #FF6F61;
  color: white;
  border: 1px solid;
  width: 2em;
  height: 2em;
  display: flex;
  justify-content: center;
  line-height: 2em;
  user-select: none;
  opacity: 0.7;
  transition: 0.3s;
  margin: 3px;
}

.gameboard-letter:hover {
  opacity: 1;
}
</style>
