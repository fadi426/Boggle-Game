<template>
  <div class="multiPlayerContainer">
    <div v-if="getPlayer.name.length !== 0">
      <div class="gameContainer" v-if="opponent != '' && checkValidSession() == true">
        <ion-card>
          <ion-card-content >
            <GameBoard v-bind:letterArray="letterList"/>
            <InformationBoard />
          </ion-card-content>
        </ion-card>
        <ion-card>
          <ion-card-content>
            <ion-card-title align="left"> Score </ion-card-title>
            <ion-card-subtitle align="left" v-html="findMatchInfo()"></ion-card-subtitle>
            <WordBoard />
          </ion-card-content>
        </ion-card>
      </div>
      <div v-else>
        <ion-card>
          <ion-card-title> Waiting for 1 more player </ion-card-title>
          <h4 v-html="findMatchInfo() "></h4>
            <ion-progress-bar type="indeterminate"></ion-progress-bar>
        </ion-card>
      </div> 
    </div>
    <ion-card v-else>
      <ion-card-title> No player detected</ion-card-title>
      <ion-card-subtitle> Click here to go back to the lobby </ion-card-subtitle>
      <router-link to="/gamerooms" id="component-button">Lobby</router-link>
    </ion-card>
  </div>
</template>

<script>
import GameBoard from "./GameBoard";
import WordBoard from "./WordBoard";
import InformationBoard from "./InformationBoard";
import GoldMedal from "../assets/gold-medal.png";
import SilverMedal from "../assets/silver-medal.png";
import { mapGetters, mapState } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from 'axios';
import SinglePlayerVue from './SinglePlayer.vue';

export default {
  name: "MultiPlayer",
  components: {
    GameBoard,
    WordBoard,
    InformationBoard
  },
    data() {
    return {
      received_messages: [],
      send_message: null,
      connected: false,
      letterList: "",
      opponent: '',
    };
  },
  watch: {
      score: function() {
        this.send_message = this.getPlayer.totalScore;
        this.send();
      },
      gameOver: function() {
        this.showResultScreen();
      }
    },
    methods: {
      checkValidSession() {
        let messageQueueLength = this.received_messages.length-1;
            for (var i = 0; i < this.received_messages[messageQueueLength].players.length; i++) {
              if(this.received_messages[messageQueueLength].players[i].uuid == this.getPlayer.uuid){
                return true;
              }
            }
        return false;
      },
      showResultScreen() {
        if(this.opponent != '' && this.getGameOver == true){
          if(this.getPlayer.totalScore == this.opponent.score){
            swal({
              title: "Time is up!",
              text: "It's a draw!",
              type: "success",
              icon: GoldMedal,
              buttons: "Back to Lobby"
            }).then(()=> {
              this.$router.push('/gamerooms')
            })
          }
          else if(this.getPlayer.totalScore > this.opponent.score){
            swal({
              title: "Time is up!",
              text: "Congratulations you won!!",
              type: "success",
              icon: GoldMedal,
              buttons: "Back to Lobby"
            }).then(()=> {
              this.$router.push('/gamerooms')
            })
          }
          else{
            swal({
              title: "Time is up!",
              text: "You lost, better luck next time!!",
              type: "success",
              icon: SilverMedal,
              buttons: "Back to Lobby"
            }).then(()=> {
              this.$router.push('/gamerooms')
            })
          }
        }
      },
      findMatchInfo(){
        let matchInfoString = "";

        let messageQueueLength = this.received_messages.length-1;
        for (var i = 0; i < this.received_messages[messageQueueLength].players.length; i++) {

          this.letterList = this.received_messages[messageQueueLength].letters; 
          for (var j = 0; j < this.received_messages[messageQueueLength].players.length; j++) {
            if(this.received_messages[messageQueueLength].players[j].uuid != this.getPlayer.uuid){
              this.opponent = this.received_messages[messageQueueLength].players[j];
              return this.getPlayer.name + " : " + this.getPlayer.totalScore + '<br/>' + 
              this.opponent.name + " : " + this.opponent.score;
            }
          }
        }
        this.opponent = '';
        this.resetGame();
        return this.getPlayer.name + " : " + this.getPlayer.totalScore + '<br/>' + 
          this.opponent.name + " : " + this.opponent.score;
    },
   send() {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          uuid: this.$route.params.uuid,
          name: this.received_messages.name,
          players: {
		        uuid: this.getPlayer.uuid,
		        name: this.getPlayer.name,
            score: this.getPlayer.totalScore
          },
          letters: this.received_messages.letters
        };
        this.stompClient.send("/app/topic/greetings/" + this.$route.params.uuid, JSON.stringify(msg), {});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/greetings/" + this.$route.params.uuid, tick => {
            console.log(tick);
            this.received_messages.push(JSON.parse(tick.body));
          });
          this.send();
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    },
    removePlayer() {
      if(this.getPlayer.name.length > 1){
        return new Promise((resolve) => {
            axios.post('http://localhost:8080/gamerooms/removeplayer', {
              "gameRoomId": this.$route.params.uuid,
              "players": {
                "uuid": this.getPlayer.uuid,
                "name": this.getPlayer.name,
                "score": this.getPlayer.totalScore
              }
            })
            .then((response) => {
                if (response)
                console.log("player has been removed from the room");
                else
                console.log("player has not been removed from the room");
                resolve()
            });
        });
      }
    },
    resetGame() {
      this.$store.commit("resetGame");
    }
  },
  mounted() {
     this.connect();
     this.resetGame();
  },
  computed: {
    ...mapGetters([
      "getPlayer",
      "getGameOver",
    ]),
    fullName: function () {
      return this.firstName;
    },
    score() {
      return this.getPlayer.totalScore;
    },
    gameOver() {
      return this.getGameOver;
    },
  },
  created() {
      window.addEventListener('beforeunload', () => {
        this.removePlayer();
        this.disconnect();
      }, false)
  },
  beforeRouteLeave (to, from, next) {
    console.log("connected has been closed");
    this.disconnect();
    this.removePlayer();
    this.resetGame();
    next();
  },
};
</script>

<style lang="scss">
.multiPlayerContainer{
  position: absolute;
  width: 100vw;
  flex-flow: column wrap;
  align-content: center;
  justify-content: center;
  .gameContainer{
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
  ion-card {
    max-width: 400px;
    width: 98vw;
    display: inline-block;
  }
}
</style>
