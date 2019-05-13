<template>
    <div class="savePlayerContainer" v-if="!getPlayer.name">
        <input type="text" v-model="name" placeholder="Enter your name here...">
        <ion-button expand="full" v-on:click="PostPlayer">send</ion-button>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "SavePlayer",
  data() {
    return {
      name: ""
    };
  },
  computed: {
    ...mapGetters(["getPlayer"])
  },
  methods: {
    create_UUID() {
      var dt = new Date().getTime();
      var uuid = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(
        /[xy]/g,
        function(c) {
          var r = ((dt + Math.random() * 16) % 16) | 0;
          dt = Math.floor(dt / 16);
          return (c == "x" ? r : (r & 0x3) | 0x8).toString(16);
        }
      );
      return uuid;
    },
    PostPlayer() {
      return new Promise(resolve => {
        var payload = {
          uuid: this.create_UUID(),
          name: this.name,
          totalScore: 0
        };
        axios
          .post("http://192.168.0.11:8080/players", {
            uuid: payload.uuid,
            name: payload.name,
            score: payload.totalScore
          })
          .then(response => {
            if (response) this.$store.commit("createPlayer", payload);
            else console.log("player has not been added");
            resolve();
          });
      });
    }
  }
};
</script>

<style lang="scss">
.savePlayerContainer{
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
    input[type=text] {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;
        border: none;
        border-bottom: 1.5px solid black;
        outline: none
    }
    ion-button{
        width: 50%;
    }
}
</style>
