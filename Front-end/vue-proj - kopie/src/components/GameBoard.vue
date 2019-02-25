<template>
  <div class="flex-container">
    <div class="row">
      <div
        class="gameboard"
        @mousedown="startCapturing"
        @mouseup="stopCapturing"
      >
        <GameBoardLetter 
          v-for="(letter, index) in letterArray"
          :key="index"
          :letter="letter"
          :letterIndex="index"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import GameBoardLetter from "./GameBoardLetter";
import axios from 'axios';

export default {
  name: "GameBoard",
  components: { GameBoardLetter },
  props: ["letterArray"],
  data() {
    return {};
  },
  computed: {
    ...mapGetters([
        "getWord",
        "getWordList",
        "getInvalidMove",
        "getGameOver"
    ])
  },
  methods: {
    startCapturing() {
      if(!this.getGameOver)
      this.$store.dispatch("startCapturing");
    },
    stopCapturing() {
      
      this.$store.dispatch("stopCapturing");
      var Word = this.getWord;
      this.$store.commit("adjustInvalidMove", false);
      this.$store.commit("resetWord");

      if (Word.word && 
        !this.getWordList.some(e => e.word === Word.word) &&
        !this.getInvalidMove && Word.word.length > 1) {
          this.$store.commit("addWord", Word);
          return new Promise((resolve) => {
            axios.post('http://localhost:8080/words', { 'word': Word.word, 'score': Word.score })
            .then((response) => {
              if (response.data.validWord == true){
                Word.isValidWord = true;
                this.$store.commit("addScorePoints", Word.score);
              }
              if (response.data.validWord == false)
                Word.isValidWord = false;
              resolve()
            });
          });
      }
    }
  },
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
