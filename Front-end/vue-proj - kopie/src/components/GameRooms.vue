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
        :style=" {backgroundColor : room.players.length > 1  ? '#00A591' : '#FF6F61'}"
      >
       <ion-card-content>
            <h4> {{ room.name }} </h4>
            <div>
              <h5 v-if="room.players[0]"> 
                {{room.players[0].name}} </h5> 
              <h5 v-if="room.players[1]"> 
                  {{room.players[1].name}} 
              </h5>
              <h4> 2 / {{room.players.length}}</h4>
            </div>
              <!-- <div class="joinButton" v-if="room.players.length < 2 ">
                  <AddPlayerToRoom v-bind:gameRoomId="room.uuid"/>
              </div> -->
              <ion-button color="primary" v-if="room.players.length > 2 " expand="full">Join
                  <AddPlayerToRoom v-bind:gameRoomId="room.uuid"/>
              </ion-button>
              <ion-button color="danger" v-else expand="full">Join
              </ion-button>
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

  position: absolute;
  display: flex;
  width: 100vw;
  flex-flow: row wrap;
  align-content: center;
  justify-content: center;

  #gameInfo {
    max-width: 400px;
    width: 98vw;
    display: inline-block;
    }
  #gameroom{
      position: absolute;
    display: flex;
    width: 300px;
    flex-flow: row wrap;
    align-content: center;
    justify-content: center;
    max-width: 400px;
    display: inline-block;
  }

  // #room{
  //   background-color: #FF6F61;
  //   border: none;
  //   color: white;
  //   padding: 16px 32px;
  //   text-align: center;
  //   font-size: 16px;
  //   margin: 8px 2px;
  //   opacity: 0.7;
  //   transition: 0.3s;
  //   display: inline-block;
  //   text-decoration: none;
  //   width: 20%
  // }

}


</style>