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
import GameBoard from "../components/GameBoard";
import WordBoard from "../components/WordBoard";
import InformationBoard from "../components/InformationBoard";
import { mapGetters } from "vuex";
import Medal from "../assets/gold-medal.png";
import axios from "axios";

export default {
  name: "SinglePlayer",
  components: {
    GameBoard,
    WordBoard,
    InformationBoard
  },
  data() {
    return {
      letterList: []
    };
  },
  computed: {
    ...mapGetters(["getPlayer", "getGameOver"]),
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
      // shows the medal popup when gameOver is true by looking at the timer to reach 0 seconds
      // and reset the game when the "play again" button is pressed
      if (this.getGameOver == true) {
        swal({
          title: "Time is up!",
          text: "Your score is: " + this.getPlayer.totalScore,
          type: "success",
          icon: Medal,
          buttons: "Play again"
        }).then(() => {
          this.resetGame();
        });
      }
    },
    letters() {
      // get the dice configuration from the API to display it on the GameBoardLetters
      return new Promise((resolve) => {
          axios.get('http://192.168.137.1:8080/board')
          .then((response) => {
              if (response){
                  this.letterList = response.data;
              }
              else
              console.log("failed to make gameBoard");
              resolve();
          });
      });
    },
    resetGame() {
      this.$store.commit("resetGame");
    }
  },
  mounted() {
    this.resetGame();
    this.letters();
  }
};
</script>

<style lang="scss">
.singlePlayerContainer {
  padding-top: 15%;
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


