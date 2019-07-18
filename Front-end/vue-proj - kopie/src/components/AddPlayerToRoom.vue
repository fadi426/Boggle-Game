<template>
    <div>
        <div class="playerForm" v-if="getPlayer.name" v-on:click="postPlayerToGame">
            <router-link :to="{ name: 'multiplayer', params: { uuid: gameRoomId }} ">Join Room</router-link>
        </div>
    </div>
</template>y

<script>
import { mapGetters } from "vuex";
import axios from 'axios';
export default {
    name: "AddPlayerToRoom",
    props: ["gameRoomId"],
	data() {
		return{
        };
    },
    computed: {
        ...mapGetters([
            "getPlayer",
        ]),
    },
	methods: {
        postPlayerToGame() {
            // link the current player userUUID to the gameroomUUID in the API
            return new Promise((resolve) => {
                axios.post('http://192.168.137.1:8080/gamerooms/player', {
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
