<template>
    <div>
        <div class="gameRoomForm" v-if="getPlayer.name">
            <label for="name">What is your room name?</label>
            <input type="text" id="name" v-model="name" placeholder="Your gameroom name here...">
            <button v-on:click="PostPlayer">Save</button>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';
export default {
    name: "SaveGameRoom",
	data() {
		return{
            name: "",
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
            return uuid;
        },
        PostPlayer() {
            return new Promise((resolve) => {
               this.playerArray.push(this.getPlayer);
                axios.post('http://localhost:8080/gamerooms', { 'uuid': this.create_UUID(), 'name': this.name, 'available': true, 'players': this.playerArray})
                .then((response) => {
                    if (response)
                    console.log("something went right");
                    else
                    console.log("something went wrong");
                    resolve()
                });
            });
        }
	}
}
</script>

<style>

</style>
