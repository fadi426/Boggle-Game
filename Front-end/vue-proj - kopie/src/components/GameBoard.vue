<template>
  <div class="flex-container">
    <div class="row">
      <div
        class="gameboard"
        @mousedown="startCapturing"
        @mouseup="stopCapturing"
      >
        <GameBoardLetter
          v-for="(letter, index) in letters()"
          :key="index"
          :letter="letter"
          :letterIndex="index"
          :coord="{x: (index -1) % 5 + 1, y: Math.floor(index/5.01)}"
        />
      </div>
    </div>
  </div>
</template>

<script>
import _ from "underscore";
import GameBoardLetter from "./GameBoardLetter";
export default {
  name: "GameBoard",
  components: { GameBoardLetter },
  data() {
    return {};
  },
  methods: {
    letters() {
      let alphabet = [
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
      ];
      let letters = [];
      for (let i = 0; i < 25; i++) {
        letters.push(alphabet[_.random(alphabet.length - 1)]);
      }
      return letters;
    },
    startCapturing() {
      this.$store.dispatch("startCapturing");
    },
    stopCapturing() {
      
      this.$store.dispatch("stopCapturing");
    }
  }
};
</script>

<style>
.gameboard {
  display: flex;
  flex-wrap: wrap;
  list-style-type: none;
  width: calc(11em + 15px);
  padding: 0;
  font-size: 30px;
}
.letter {
  border: 1px solid;
  width: 2em;
  height: 2em;
  display: flex;
  justify-content: center;
  line-height: 2em;
}

.flex-container {
  height: 100%;
  padding: 0;
  margin: 0;
  display: -webkit-box;
  display: -moz-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
