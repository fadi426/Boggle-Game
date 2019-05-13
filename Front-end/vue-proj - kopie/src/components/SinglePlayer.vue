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
          this.resetGame();
        });
      }
    },
    letters() {
      return new Promise((resolve) => {
          axios.get('http://192.168.0.11:8080/board')
          .then((response) => {
              if (response){
                  console.log(response.data);
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


