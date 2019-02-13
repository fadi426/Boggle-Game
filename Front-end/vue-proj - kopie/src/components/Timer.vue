<template>
  <div class="timer"> 
    {{time}}
  </div>
</template>

<script>
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
  },
  created() {
    this.Timer();
  },
};
</script>

