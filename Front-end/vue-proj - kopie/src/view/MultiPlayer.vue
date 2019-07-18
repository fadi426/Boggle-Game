<template>
  <div class="multiPlayerContainer">
    <!-- check if the current player is a valid player -->
    <div v-if="getPlayer.name.length !== 0">
      <!-- check if the player is linked to this game room in the API -->
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
            <!-- show the name and scores of the players -->
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
      <!-- return back to /gamerooms when the player is not registered -->
      <router-link to="/gamerooms" id="component-button">Lobby</router-link>
    </ion-card>
  </div>
</template>

<script>
import GameBoard from "../components/GameBoard";
import WordBoard from "../components/WordBoard";
import InformationBoard from "../components/InformationBoard";
import GoldMedal from "../assets/gold-medal.png";
import SilverMedal from "../assets/silver-medal.png";
import { mapGetters, mapState } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from 'axios';

export default {
  name: "MultiPlayer",
  components: {
    GameBoard,
    WordBoard,
    InformationBoard
  },
    data() {
    return {
      receivedMessages: [],
      sendMessage: null,
      connected: false,
      letterList: [],
      opponent: '',
    };
  },
  watch: {
      score: function() {
        this.sendMessage = this.getPlayer.totalScore;
        this.send();
      },
      gameOver: function() {
        this.showResultScreen();
      }
    },
    methods: {
      // check if the player is linked to this game room in the API
      checkValidSession() {
        let messageQueueLength = this.receivedMessages.length-1;
            for (var i = 0; i < this.receivedMessages[messageQueueLength].players.length; i++) {
              if(this.receivedMessages[messageQueueLength].players[i].uuid == this.getPlayer.uuid){
                return true;
              }
            }
        return false;
      },
      // show the end screen with the game results on it
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
        // read the websocket message and filter out the player names and scores
        let matchInfoString = "";

        let messageQueueLength = this.receivedMessages.length-1;
        for (var i = 0; i < this.receivedMessages[messageQueueLength].players.length; i++) {

          this.letterList = this.receivedMessages[messageQueueLength].letters; 
          for (var j = 0; j < this.receivedMessages[messageQueueLength].players.length; j++) {
            if(this.receivedMessages[messageQueueLength].players[j].uuid != this.getPlayer.uuid){
              this.opponent = this.receivedMessages[messageQueueLength].players[j];
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
    //  send the current player and the current total score of the player 
    //  and the room info to the API to update the info by using websockets
    console.log(this.$route.params.uuid);
      console.log("Send message:" + this.sendMessage);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          uuid: this.$route.params.uuid,
          name: this.receivedMessages.name,
          players: {
		        uuid: this.getPlayer.uuid,
		        name: this.getPlayer.name,
            score: this.getPlayer.totalScore
          },
          letters: this.receivedMessages.letters
        };
        this.stompClient.send("/app/topic/gameroom/" + this.$route.params.uuid, JSON.stringify(msg), {});
      }
    },
    connect() {
      // establish the connection to the API by using websockets
      this.socket = new SockJS("http://192.168.137.1:8080/boggle-game");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          // listen to incomming messages and push them to the receivedMessages list
          this.stompClient.subscribe("/topic/gameroom/" + this.$route.params.uuid, tick => {
            this.receivedMessages.push(JSON.parse(tick.body));
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
      // disconnect from the websocket connection
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    removePlayer() {
      // remove the player from the gameroom in the API
      if(this.getPlayer.name.length > 1){
        return new Promise((resolve) => {
            axios.post('http://192.168.137.1:8080/gamerooms/removeplayer', {
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
    this.removePlayer();
    this.send();
    this.disconnect();
    this.resetGame();
    next();
  },
};
</script>

<style lang="scss">
.multiPlayerContainer{
  padding-top: 15%;
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
      width: 94vw;
      display: inline-block;
    }
  }
  ion-card {
    max-width: 400px;
    width: 94vw;
    display: inline-block;
  }
}
</style>
