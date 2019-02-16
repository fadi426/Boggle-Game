<template>
    <div>
        <div class="playerForm" v-if="!getPlayer.name">
            <label for="name">What is your name?</label>
            <input type="text" id="name" v-model="name" placeholder="Your name here...">
            <button v-on:click="PostPlayer">Save</button>
        </div>
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
