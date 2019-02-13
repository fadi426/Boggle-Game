<template>
  <div
    class="gameboard-letter" :style="changePressedLetterStyle"
    v-on="capturing ? { mouseover: () => addLetter() } : {} "
  >
    <p id="p2">{{ letter }}</p>

  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "GameBoardLetter",
  props: ["letter", "letterIndex", "coord"],
  data() {
    return {
      isActive : false,
    };
  },
  methods: {
    addLetter() {
      var payload = { letter: this.letter, letterIndex: this.letterIndex };
      var lastMoveIndex = this.getWord.letterIndexList[this.getWord.letterIndexList.length - 1];

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
          this.isActive = true;
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
      if (!this.$store.state.capturing) {
         this.isActive = false; 
      }
      return {
        backgroundColor : this.isActive  ? '#00A591' : '#FF6F61'
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
