<template>
  <div class="gameBoardContainer">
    <div class="flex-container">
      <div class="row">
        <div class="gameboard">
          <GameBoardLetter 
            v-for="(letter, index) in letterArray"
            :key="index"
            :letter="letter"
            :letterIndex="index"
          />
        </div>
      </div>
    </div>
    <ion-button v-on:click="sendWord"> Send </ion-button>
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
        "getInvalidMove"
    ])
  },
  methods: {
    sendWord() {
      // reset the current word
      var Word = this.getWord;
      this.$store.commit("resetWord");

      // check if a word is present that's not already in the wordList 
      // while the word is longer than 2 characters and the move is valid
      if (Word.word && 
        !this.getWordList.some(e => e.word === Word.word) &&
        !this.getInvalidMove && Word.word.length > 2) {
          this.$store.commit("addWord", Word);
          return new Promise((resolve) => {

            // send the word to the Back-end to check if its a valid word or not
            axios.post('http://192.168.137.1:8080/words', { 'word': Word.word, isValid: false, score: 0  })
            .then((response) => {
              if (response.data.isValid == true){
                // add score points when the word is valid
                Word.isValid = true;
                var payload = { word: Word, score: response.data.score };
                this.$store.commit("addScorePoints", payload);
              }
              if (response.data.isValid == false)
                Word.isValid = false;
              resolve()
            });
          });
      }
    }
  },
};
</script>

<style lang="scss">
.gameBoardContainer{

  .flex-container {
    height: 100%;
    padding: 0;
    margin: 0;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .gameboard {
  display: flex;
  flex-wrap: wrap;
  list-style-type: none;
  width: calc(11em + 15px);
  padding: 0;
  font-size: 25px;
  }
  .letter {
    border: 1px solid;
    width: 2em;
    height: 2em;
    display: flex;
    justify-content: center;
    line-height: 2em;
  }
}
</style>
