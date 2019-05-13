<template>
	<div class="homeContainer">
      <ion-card>
        <ion-card-content>
          <ion-card-title>Boggle Game</ion-card-title>
          <ion-card-subtitle>Made by Fadi Qadadi</ion-card-subtitle>
        </ion-card-content>
      </ion-card>
    <Slider/>
	</div>
</template>

<script>
import Slider from "./Slider";
export default {
  name: "home",
  components: { Slider },
  data() {
    return {};
  }
};
</script>

<style lang="scss">
.homeContainer {
  padding-top: 15%;
  position: absolute;
  width: 100vw;
  flex-flow: column wrap;
  align-content: center;
  justify-content: center;

  ion-card {
    max-width: 400px;
    width: 98vw;
    display: inline-block;
  }
}
</style>
