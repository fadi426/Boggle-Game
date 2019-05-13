<template>
    <div>
        <div class="playerForm" v-if="getPlayer.name" v-on:click="PostPlayerToGame">
            <router-link :to="{ name: 'multiplayer', params: { uuid: gameRoomId }} ">Join Room</router-link>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';
export default {
    name: "AddPlayerToRoom",
    props: ["gameRoomId"],
	data() {
		return{
            name: ""
        };
    },
    computed: {
        ...mapGetters([
            "getPlayer",
        ]),
    },
	methods: {
        PostPlayerToGame() {
            return new Promise((resolve) => {
                axios.post('http://192.168.0.11:8080/gamerooms/addplayer', {
	                "gameRoomId": this.gameRoomId,
	                "players": {
		                "uuid": this.getPlayer.uuid,
		                "name": this.getPlayer.name,
		                "score": this.getPlayer.totalScore
	                }
                })
                .then((response) => {
                    if (response)
                    console.log("Player has been added to the room");
                    else
                    console.log("Player has not been added to the room");
                    resolve()
                });
        });
        }
	}
}
</script>

<style>

</style>
