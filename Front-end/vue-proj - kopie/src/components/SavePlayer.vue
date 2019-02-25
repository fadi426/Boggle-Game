<template>
    <div class="playerForm" v-if="!getPlayer.name">
        <input type="text" class="nameInputfield" id="name" v-model="name" placeholder="Enter your name here...">
        <button class="nameButton" v-on:click="PostPlayer">Save</button>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';
export default {
    name: "SavePlayer",
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
                var payload = { uuid: this.create_UUID(), name: this.name, totalScore: 0};
                axios.post('http://localhost:8080/players', { 'uuid': payload.uuid, 'name': payload.name, 'score': payload.totalScore})
                .then((response) => {
                    if (response)
                    this.$store.commit("createPlayer", payload);
                    else
                    console.log("player has not been added");
                    resolve()
                });
        });
        }
	}
}
</script>

<style>
    .nameButton {
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
    .nameButton:hover {
        opacity: 1
    }
    .nameInputfield {
        height: 50px;
        width: 400px;
    }
</style>
