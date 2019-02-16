<<template>
    <div>
        <h2>Hallo</h2>
        <SavePlayer/>
        <SaveGameRoom/>
        <GameRoomBoard/>
    </div>
</template>

<script>
import SavePlayer from "./SavePlayer";
import GameRoomBoard from "./GameRoomBoard";
import SaveGameRoom from "./SaveGameRoom";
export default {
    name: "MultiPlayer",
    components: { SavePlayer, GameRoomBoard, SaveGameRoom },
	data() {
		return{};
    }
}
</script>

<style>

</style>
