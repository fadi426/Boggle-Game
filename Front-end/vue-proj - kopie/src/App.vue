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
