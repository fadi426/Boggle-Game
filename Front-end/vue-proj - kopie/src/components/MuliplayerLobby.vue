</<template>
    <div>
        <h2>Hallo</h2>
        <button v-on:click="create_UUID">Greet</button>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
	name: "MultiplayerLobby",
	data() {
		return{
            name : "Fadi Qadadi",
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
        var payload = { uuid: uuid, name: this.name, totalScore: 0};
            this.$store.commit("createPlayer", payload);
        }
	}
}
</script>

<style>

</style>
