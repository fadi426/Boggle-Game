<template>
  <div class="navBarContainer"> 
    <!-- <ion-app>
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
    <ion-menu-controller></ion-menu-controller> -->

<div class="dropdown">
  <ion-button v-on:click="myFunction">Menu
    <ion-icon name="menu"></ion-icon>
  </ion-button>
  <div id="myDropdown" class="dropdown-content">
    <router-link v-bind:to="'/'" >Home</router-link>
    <router-link v-bind:to="'/singleplayer'">SinglePlayer</router-link>
    <router-link v-bind:to="'/gamerooms'">MultiPlayer</router-link>
  </div>
</div>
  </div>
</template>
<script>
export default {
  name: 'App',
  data () {
    return {
    }
  },
  methods: {
    openStart () {
      document.querySelector('ion-menu-controller').open('start')
    },
    openEnd () {
      document.querySelector('ion-menu-controller').open('end')
    },
    myFunction() {
      document.getElementById("myDropdown").classList.toggle("show");
    }
  }
}
</script>

<style lang="scss">
.navBarContainer{
  padding-top: 20px;
  padding-bottom: 20px;
  position:fixed;  
  z-index:1; /*Add this*/
  width:100%;
  text-align:center;
  vertical-align:middle;

  /* Dropdown button on hover & focus */
  .dropbtn:hover, .dropbtn:focus {
    background-color: #2980B9;
  }

  /* The container <div> - needed to position the dropdown content */
  .dropdown {
    position: relative;
    display: inline-block;
  }

  /* Dropdown Content (Hidden by Default) */
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
  }

  /* Links inside the dropdown */
  .dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
  }

  /* Change color of dropdown links on hover */
  .dropdown-content a:hover {background-color: #ddd}

  /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
  .show {display:block;}
    ion-button{
      max-width: 400px;
    }
  ion-icon{
    zoom: 2.0;
  }
}
</style>
