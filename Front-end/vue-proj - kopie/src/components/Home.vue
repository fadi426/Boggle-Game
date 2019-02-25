<template>
	<div class="home">
		<vue-3d-menu/>
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
  },
}
</script>

<style>
#component-button {
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
  width: 20%
}
#component-button:hover {
	opacity: 1
}
</style>
