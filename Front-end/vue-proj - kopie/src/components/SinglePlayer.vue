<template>
  <div>
    <div class="column" style="background-color:#ffff;">
      <GameBoard />
      <InformationBoard />
    </div>
    <div class="column" style="background-color:#ffff;">
      <WordBoard />
    </div>
  </div>
</template>

<script>
import GameBoard from "./GameBoard";
import WordBoard from "./WordBoard";
import InformationBoard from "./InformationBoard";
import { mapGetters, mapState } from "vuex";

export default {
  name: "SinglePlayer",
  components: {
    GameBoard,
    WordBoard,
    InformationBoard
  },
  computed: {
    ...mapGetters([
      "getWord",
      "getWordList",
      "getTotalScore",
      "getInvalidMove"
    ]),
    ...mapState(["capturing"])
  }
};
</script>

<style>
* {
  box-sizing: border-box;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.column {
  float: left;
  width: 50%;
  padding: 10px;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
