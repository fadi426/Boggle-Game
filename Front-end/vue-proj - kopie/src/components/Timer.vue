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
      playtime_seconds: 180,
    };
  },
  methods: {
    Timer() {
      let tick = () => {
        var hours = Math.floor(this.playtime_seconds / 3600);
        var minutes = Math.floor((this.playtime_seconds - (hours * 3600)) / 60);
        var seconds = this.playtime_seconds - (hours * 3600) - (minutes * 60);

        if (hours < 10) { hours = "0" + hours; }
        if (minutes < 10) { minutes = "0" + minutes; }
        if (seconds < 10) { seconds = "0" + seconds; }
        this.time = minutes + ':' + seconds;

        if (this.playtime_seconds <= 0) {
          clearInterval(timer);
          this.$store.commit("stateGameOver");
        }

        this.playtime_seconds -= 1;
      };

      let timer = setInterval(tick, 1000);
      tick();
    },
    resetTimer() {
      if(this.time == "00:00"  && this.gameOver == false){
        this.playtime_seconds = 180;
        this.Timer();
      }
    }
  },
  created() {
    this.Timer();
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
    gameOver: function() {

      this.resetTimer();
    }
  },
};
</script>

