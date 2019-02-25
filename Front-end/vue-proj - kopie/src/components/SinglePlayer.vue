<template>
  <div>
    <vue-3d-menu id="navBar"/>
    <div class="secondLayer">
      <div class="column">
        <GameBoard v-bind:letterArray="letterList"/>
        <InformationBoard />
      </div>
      <div class="column">
        <h2 align="left">Total score : {{ getPlayer.totalScore }}</h2>
        <WordBoard />
      </div>
    </div>
    </div>
</template>

<script>
import GameBoard from "./GameBoard";
import WordBoard from "./WordBoard";
import InformationBoard from "./InformationBoard";
import { mapGetters, mapState } from "vuex";
import _ from "underscore";
import Medal from "../assets/gold-medal.png";

export default {
  name: "SinglePlayer",
  components: {
    GameBoard,
    WordBoard,
    InformationBoard
  },
    data() {
    return {
      letterList: this.letters(),
    };
  },
  computed: {
    ...mapGetters([
      "getPlayer",
      "getGameOver",
    ]),
    ...mapState(["capturing"]),
    gameOver() {
      return this.getGameOver;
    },
  },
  watch: {
    gameOver: function() {
      this.showResultScreen();
    }
  },
  methods: {
    showResultScreen() {
      if(this.getGameOver == true){
        swal({
          title: "Time is up!",
          text: "Your score is: " + this.getPlayer.totalScore,
          type: "success",
          icon: Medal,
          buttons: "Play again"
        }).then(()=> {

          this.resetGame(); // this should execute now

        })
      }
    },
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
    resetGame() {
     this.$store.commit("resetGame");
    }
  },
  mounted() {
    this.resetGame();
  },
};
</script>

<style>
* {
  box-sizing: border-box;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.column {
  float: left;
  width: 50%;
  padding: 10px;
  
  
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
#navBar {
   position:absolute;
   top:0;
   right:0;
}
</style>
