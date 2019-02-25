<template>
  <div>
    <vue-3d-menu id="navBar"/>
    <div v-if="getPlayer.name.length !== 0">
      <div v-if="opponent != '' && checkValidSession() == true">
        <div class="column">
          <GameBoard v-bind:letterArray="letterList"/>
          <InformationBoard />
        </div>
        <div class="column">
          <h3 align="left"> Score </h3>
          <h4 align="left" v-html="findMatchInfo()"></h4>
          <WordBoard />
        </div>
      </div>
      <div v-else>
        <center>
        <h1> Waiting for 1 more player </h1>
        <h4 v-html="findMatchInfo() "></h4>
        <LoadingIconMultiPlayer/>
        </center>
      </div> 
    </div>
    <div v-else>**
      <h2> No player detected</h2>
      <h2> Click here to go back to the lobby </h2>
      <router-link to="/gamerooms" id="component-button">MultiPlayer</router-link>
    </div>
  </div>
</template>

<script>
import GameBoard from "./GameBoard";
import WordBoard from "./WordBoard";
import LoadingIconMultiPlayer from "../assets/LoadingIconMultiPlayer";
import InformationBoard from "./InformationBoard";
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
    InformationBoard,
    LoadingIconMultiPlayer
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
        for (var i = 0; i < this.received_messages.length; i++) {
          if(this.received_messages[i].uuid == this.$route.params.uuid){
            for (var j = 0; j < this.received_messages[i].players.length; j++) {
              if(this.received_messages[i].players[j].uuid == this.getPlayer.uuid){
                return true;
              }
            }
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
        for (var i = 0; i < this.received_messages.length; i++) {
          if(this.received_messages[i].uuid == this.$route.params.uuid){
            this.letterList = this.received_messages[i].letters; 
            for (var j = 0; j < this.received_messages[i].players.length; j++) {
              if(this.received_messages[i].players[j].uuid != this.getPlayer.uuid){
                this.opponent = this.received_messages[i].players[j];
                return this.getPlayer.name + " : " + this.getPlayer.totalScore + '<br/>' + 
                this.opponent.name + " : " + this.opponent.score;
              }
          }
        }
      }
      this.opponent = '';
      this.resetGame();
      return this.getPlayer.name + " : " + this.getPlayer.totalScore + '<br/>' + 
         this.opponent.name + " : " + this.opponent.score;
    },
    send() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
        	"gameRoomId": this.$route.params.uuid,
	        "players": {
		        "uuid": this.getPlayer.uuid,
		        "name": this.getPlayer.name,
		        "score": this.getPlayer.totalScore
            }
        }
        this.stompClient.send("/app/hello", JSON.stringify(msg), {});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          this.stompClient.subscribe("/topic/gamerooms", tick => {
            this.received_messages = (JSON.parse(tick.body));
          });
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
</style>
