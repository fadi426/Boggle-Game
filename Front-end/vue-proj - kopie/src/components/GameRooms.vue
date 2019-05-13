<template>
  <div class="gameRoomsContainer">
      <ion-card class="gameInfo">
        <ion-card-content >
          <ion-card-title>Lobby</ion-card-title>
          <SavePlayer/>
          <SaveGameRoom/>
        </ion-card-content>
      </ion-card>
 
      <div v-for="room in received_messages"
        :key="room.uuid"
      >
      <ion-card class="gameroom"
        :style=" {backgroundColor : room.players.length > 1  ? '#FF6F61' : '#00A591'}"
      >
       <ion-card-content>
            <ion-card-title> {{ room.name }} </ion-card-title>
            <div>
              <ion-card-subtitle v-if="room.players[0]"> 
                {{room.players[0].name}} </ion-card-subtitle> 
              <ion-card-subtitle v-if="room.players[1]"> 
                  {{room.players[1].name}} 
              </ion-card-subtitle>
              <ion-card-subtitle> 2 / {{room.players.length}}</ion-card-subtitle>
            </div>
            <div class="joinContainer" v-if="room.players.length < 2 ">
              <ion-card>
                <AddPlayerToRoom v-bind:gameRoomId="room.uuid"/>
              </ion-card>
            </div>
        </ion-card-content>
      </ion-card>
      </div>
    </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import AddPlayerToRoom from "./AddPlayerToRoom";
import SavePlayer from "./SavePlayer";
import SaveGameRoom from "./SaveGameRoom";
export default {
  name: "GameRooms",
  components: { AddPlayerToRoom, SavePlayer, SaveGameRoom },
  data() {
    return {
      received_messages: [],
      received_scores: [],
      send_message: null,
      connected: false,
      gameRoomName : ""
    };
  },
  methods: {
    connect() {
      this.socket = new SockJS("http://192.168.137.1:8080/gs-guide-websocket");
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
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
  },
  mounted() {
    this.connect();
  },
  beforeRouteLeave (to, from, next) {
      console.log("connected has been closed");
      this.disconnect();
      next();
  },
};
</script>

<style lang="scss">
.gameRoomsContainer{
  padding-top: 8vw;
  width: 100%;
  margin: 0 auto;
  max-width: 400px;
  left: 0;
  right: 0;
  bottom: 0;
  top: 0;

  .gameInfo {
    max-width: 400px;
    width: 90vw;
    display: inline-block;
    }
  .gameroom{
    max-width: 400px;
    width: 90vw;
    display: inline-block;
    ion-card-title{
      color: white;
    }
    ion-card-subtitle{
      color: white;
    }
    .joinContainer{
      display: flex;
      justify-content: center;
      width: 50%;
      margin: 0 auto;
      ion-card {
        width: 98vw;
        display: inline-block;
        background-color: white;
      }
    }
  }
  ion-card {
    max-width: 400px;
    display: inline-block;
  }
  ion-button{
    width: 50%;
  }
}


</style>