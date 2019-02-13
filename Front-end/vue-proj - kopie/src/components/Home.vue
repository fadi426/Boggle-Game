<template>
	<div>
		<div id="home-content">
			<router-link to="/singleplayer" id="singlePlayer-button">Singleplayer</router-link>
		</div>
	</div>
</template>

<script>
export default {
	name: "home",
	data() {
		return{

		};
	},
	methods: {

	}
}
</script>

<style>
#singlePlayer-button {
  background-color: #FF6F61;
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
  opacity: 0.6;
  transition: 0.3s;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
}
#singlePlayer-button:hover {
	opacity: 1
}
</style>
