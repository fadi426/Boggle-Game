</<template>
    <div>
        <h2>Current word</h2>
        {{ getWord.word }}
        <h4>{{ detectInvalidMove() }}</h4>
    </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
    name: "InformationBoard",
    data() {
        return {};
    },
    methods: {
        detectInvalidMove() {
      var invalidMove = "";
      if (this.getInvalidMove === true)
        return "Invalid Move!";
    }
  },
    computed: {
        ...mapGetters([
            "getWord",
            "getInvalidMove"
        ])
    }
}
</script>

<style>
    table {
    display: block;
    overflow: auto;
    height: 300px;
    width: 300px;
    }
</style>
