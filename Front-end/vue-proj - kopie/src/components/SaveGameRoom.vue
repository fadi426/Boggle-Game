<template>
    <div>
        <div class="gameRoomForm" v-if="getPlayer.name">
            <input type="text" class="roomInputfield" id="name" v-model="name" placeholder="Enter your gameroom name here...">
            <router-link :to="{ name: 'multiplayer', params: { uuid: this.uuid }} ">
                <button class="gameRoomButton" v-on:click="PostPlayer">Add Room</button>
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
            playerArray: []
        };
    },
    computed: {
        ...mapGetters([
            "getPlayer",
        ]),
    },
	methods: {
        create_UUID(){
        var dt = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (dt + Math.random()*16)%16 | 0;
            dt = Math.floor(dt/16);
            return (c=='x' ? r :(r&0x3|0x8)).toString(16);
        });
            this.uuid = uuid;
            return uuid;
        },
        PostPlayer() {
            return new Promise((resolve) => {
               this.playerArray.push(this.getPlayer);
               axios.post('http://localhost:8080/gamerooms',
               {
                    "uuid":  this.create_UUID(),
                    "name": this.name,
                    "started": false,
                    "players": [{
                        "uuid": this.getPlayer.uuid,
                        "name": this.getPlayer.name,
                        "score": 0
                    }],
                    "letters": this.letters(),
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
            let alphabet = [
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z"
            ];
            let letters = [];
            for (let i = 0; i < 25; i++) {
                letters.push(alphabet[_.random(alphabet.length - 1)]);
            }
            return letters;
        },
    },
    beforeRouteLeave (to, from, next) {
        const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
        if (answer) {
            next()
        } else {
            next(false)
        }
    }
}
</script>

<style>
    .gameRoomButton {
        background-color: #00A591;
        border: none;
        color: white;
        height: 50px;
        padding: 3px 32px;
        text-align: center;
        font-size: 16px;
        margin: 4px 2px;
        opacity: 0.6;
        transition: 0.3s;
        display: inline-block;
        text-decoration: none;
        cursor: pointer;
    }
    .gameRoomButton:hover {
        opacity: 1
    }
    .roomInputfield {
        height: 50px;
        width: 360px;
    }
</style>
