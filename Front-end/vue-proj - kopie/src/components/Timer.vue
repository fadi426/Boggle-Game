<template>
  <div class="timer"> 
    <h4>{{time}}</h4>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  name: "Timer",
  data() {
    return {
      time: "",
      playtimeSeconds: 180,
    };
  },
  methods: {
    timer() {
      let tick = () => {
        // calculate the time
        var hours = Math.floor(this.playtimeSeconds / 3600);
        var minutes = Math.floor((this.playtimeSeconds - (hours * 3600)) / 60);
        var seconds = this.playtimeSeconds - (hours * 3600) - (minutes * 60);

        if (hours < 10) { hours = "0" + hours; }
        if (minutes < 10) { minutes = "0" + minutes; }
        if (seconds < 10) { seconds = "0" + seconds; }
        // build the timer string
        this.time = minutes + ':' + seconds;

        // set the stateGameOver on true to end the game once the timer reaches 0
        if (this.playtimeSeconds <= 0) {
          clearInterval(timer);
          this.$store.commit("stateGameOver");
        }

        // countdown
        this.playtimeSeconds -= 1;
      };

      let timer = setInterval(tick, 1000);
      tick();
    },
    resetTimer() {
      if(this.time == "00:00"  && this.gameOver == false){
        this.playtimeSeconds = 180;
        this.timer();
      }
    }
  },
  created() {
    this.timer();
  },
  computed: {
    ...mapGetters([
      "getGameOver",
    ]),
    gameOver() {
      return this.getGameOver;
    },
  },
  watch: {
      // check if the game
      gameOver: function() {
      this.resetTimer();
    }
  },
};
</script>

