<template>
  <div id="app">
    <router-view name="maincontainer" />
    <router-view name="additional" />
  </div>
</template>

<script>

export default {
  components: {
  }
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
  background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
  background-position: center center;
  background-repeat:  no-repeat;
  background-attachment: fixed;
  background-size:  cover;
  background-color: #999;
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
</style>
