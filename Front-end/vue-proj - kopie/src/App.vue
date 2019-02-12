<template>
  <div id="app">
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
import GameBoard from "./components/GameBoard";
import WordBoard from "./components/WordBoard";
import InformationBoard from "./components/InformationBoard";
import store from "./store";
import { mapGetters, mapState } from "vuex";

export default {
  name: "App",
  store,
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
