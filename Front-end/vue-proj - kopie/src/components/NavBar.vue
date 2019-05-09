<template>
  <div class="navBarContainer"> 
    <ion-app>
      <ion-menu side="start">
        <ion-header>
          <ion-toolbar color="secondary">
            <ion-title>Boggle Menu</ion-title>
          </ion-toolbar>
        </ion-header>
        <ion-content padding>

      <ion-card>
        <ion-card-content >
          <router-link v-bind:to="'/'" >Home</router-link>
        </ion-card-content>
      </ion-card>

      <ion-card>
        <ion-card-content >
          <router-link v-bind:to="'/singleplayer'">SinglePlayer</router-link>
        </ion-card-content>
      </ion-card>

      <ion-card>
        <ion-card-content>
          <router-link v-bind:to="'/gamerooms'">MultiPlayer</router-link>
        </ion-card-content>
      </ion-card>

        </ion-content>
      </ion-menu>
      <div class="ion-page" main>
        <ion-button @click="openStart">
        <ion-icon name="menu"></ion-icon>
        Menu
        </ion-button>
      </div>

    </ion-app>
    <ion-menu-controller></ion-menu-controller>
  </div>
</template>
<script>
export default {
  name: 'App',
  data () {
    return {
      items: [{id: 0, name: 'Ahmed'}, {id: 1, name: 'Naima'}]
    }
  },
  methods: {
    openStart () {
      document.querySelector('ion-menu-controller').open('start')
    },
    openEnd () {
      document.querySelector('ion-menu-controller').open('end')
    }
  }
}
</script>

<style lang="scss">
.navBarContainer{
  ion-button{
    max-width: 400px;
  }
  ion-icon{
    zoom: 2.0;
  }
}
</style>
