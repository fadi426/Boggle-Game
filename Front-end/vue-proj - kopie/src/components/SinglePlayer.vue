<template>
  <div class="singlePlayerContainer">
      <ion-card>
        <ion-card-content >
          <GameBoard v-bind:letterArray="letterList"/>
          <InformationBoard />
        </ion-card-content>
      </ion-card>
 
    <ion-card>
      <ion-card-content>
        <h2>Total score : {{ getPlayer.totalScore }}</h2>
        <WordBoard/>
      </ion-card-content>
   </ion-card>

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
      letterList: this.letters()
    };
  },
  computed: {
    ...mapGetters(["getPlayer", "getGameOver"]),
    ...mapState(["capturing"]),
    gameOver() {
      return this.getGameOver;
    }
  },
  watch: {
    gameOver: function() {
      this.showResultScreen();
    }
  },
  methods: {
    showResultScreen() {
      if (this.getGameOver == true) {
        swal({
          title: "Time is up!",
          text: "Your score is: " + this.getPlayer.totalScore,
          type: "success",
          icon: Medal,
          buttons: "Play again"
        }).then(() => {
          this.resetGame(); // this should execute now
        });
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
  }
};
</script>

<style lang="scss">
.singlePlayerContainer {
  position: absolute;
  display: flex;
  width: 100vw;
  flex-flow: row wrap;
  align-content: center;
  justify-content: center;

  ion-card {
    max-width: 400px;
    width: 98vw;
    display: inline-block;
  }
}
</style>


