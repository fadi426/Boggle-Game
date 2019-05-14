<template>
  <div id="app">
    <NavBar/>
    <!-- show content of the routerview -->
    <router-view name="maincontainer" />
    <router-view name="additional" />
  </div>
</template>

<script>
import NavBar from "./components/NavBar";
export default {
	components: { NavBar },
}
</script>


<style lang="scss">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  background-color: white;
  background-position: center center;
  background-repeat:  no-repeat;
  background-attachment: fixed;
  background-size:  cover;
  background-size: cover;
  margin: 0;
  overflow: hidden;
  user-select: none;
}

a{
	text-decoration: none;
	color: inherit;
}

html {
  overflow-y: scroll;
  overflow-x: hidden;
}
::-webkit-scrollbar {
  width: 0px;
  height: 0px;
}
h1 {
  color: black;
}
h2 {
  color: black;
}
h3 {
  color: black;
}
</style>
