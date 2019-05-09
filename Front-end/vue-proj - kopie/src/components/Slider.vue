<template>
<div class="sliderContainer">
  <ion-slides pager="true" :options="slideOpts">
    <ion-slide>
      <ion-card>
        <ion-card-header>
          <ion-card-title>Play alone</ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <ion-icon name="contact"></ion-icon>
        </ion-card-content>
          Practice makes perfect!
      </ion-card>
    </ion-slide>
    <ion-slide>
      <ion-card>
        <ion-card-header>
          <ion-card-title>Play together</ion-card-title>
        </ion-card-header>
       <ion-card-content>
          <ion-icon name="contacts"></ion-icon>
        </ion-card-content>
        Battle against your friends!
      </ion-card>
    </ion-slide>
    <ion-slide>
  <ion-card>
    <ion-card-header>
      <ion-card-title>Randomized dice</ion-card-title>
    </ion-card-header>
    <ion-card-content>
      <ion-icon name="trophy"></ion-icon>
    </ion-card-content>
      Randomly generated dice to test your limits!
  </ion-card>
    </ion-slide>
  </ion-slides>
</div>
</template>


<script>
export default {
  data() {
    return {
      slideOpts: {
        initialSlide: 1,
        speed: 400
      }
    };
  }
};
</script>

<style lang="scss">
.sliderContainer{
  ion-slides{
    padding-bottom: 25px
  }
  ion-card{
    max-width: 400px;
  }
  ion-icon{
    zoom: 10.0;
  }
}
</style>
