<template>
  <div class="navBarContainer"> 
    <div class="dropdown">
      <ion-button v-on:click="dropDown">Menu
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
    dropDown() {
      // change the appearance of the dropdown menu by calling the .show css
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
