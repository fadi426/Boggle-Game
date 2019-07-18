<template>
    <div>
        <div class="saveGameRoomContainer" v-if="getPlayer.name">
            <input type="text" v-model="name" placeholder="Enter your gameroom name here...">
            <router-link :to="{ name: 'multiplayer', params: { uuid: this.uuid }} ">
                <ion-button expand="full" v-on:click="postGameRoom">send</ion-button>
            </router-link>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';
import _ from "underscore";
export default {
    name: "SaveGameRoom",
	data() {
		return{
            name: "",
            uuid: "",
            playerArray: [],
            letterList: []
        };
    },
    computed: {
        ...mapGetters([
            "getPlayer",
        ]),
    },
	methods: {
        createUUID(){
            // create a unique ID for the game room to distinguish the rooms from each other
            var dt = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = (dt + Math.random()*16)%16 | 0;
                dt = Math.floor(dt/16);
                return (c=='x' ? r :(r&0x3|0x8)).toString(16);
            });
                this.uuid = uuid;
                return uuid;
        },
        postGameRoom() {
            // add the game room to the API
            return new Promise((resolve) => {
               this.playerArray.push(this.getPlayer);
               axios.post('http://192.168.137.1:8080/gamerooms',
               {
                    "uuid":  this.createUUID(),
                    "name": this.name,
                    "started": false,
                    "players": [{
                        "uuid": this.getPlayer.uuid,
                        "name": this.getPlayer.name,
                        "score": 0
                    }],
                    "letters": this.letterList,
                })
                .then((response) => {
                    if (response)
                    console.log("gameRoom has been added");
                    else
                    console.log("gameRoom has not been added");
                    resolve()
                });
            });
        },
        letters() {
            // get the dice configuration from the API to display it on the GameBoardLetters
            return new Promise((resolve) => {
                axios.get('http://192.168.137.1:8080/board')
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
    },
    beforeRouteLeave (to, from, next) {
        // make sure to check if the player really wants to leave the page with unsaved changes
        const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
        if (answer) {
            next()
        } else {
            next(false)
        }
    },
    mounted() {
    this.letters();
  }
}
</script>

<style lang="scss">

.saveGameRoomContainer {
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
    a{
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    }
    ion-button{
        width: 50%;
    }
}
</style>
