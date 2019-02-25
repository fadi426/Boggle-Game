<template>
    <div class="playerForm" >
        <h1> Hoi </h1>
        <h1>{{this.getPlayer.name}} </h1>
        <h1>{{this.$route.params.uuid}} </h1>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
export default {
  name: "GameRoomBoard",
  data() {
    return {
      received_messages: [],
      received_scores: [],
      send_message: null,
      connected: false
    };
  },
    computed: {
        ...mapGetters([
            "getPlayer",
        ]),
    },
};
</script>

<style>
div.room {
  background-color: #FF6F61;
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  font-size: 16px;
  margin: 8px 2px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  width: 20%
}
</style>