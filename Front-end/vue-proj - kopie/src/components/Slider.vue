<template>
<div>
  <div class="slider-container">
    <div class="slider" :style="styleObject">
      <div class="green slide">
        <img
          width="200"
          height="200"
          src="@/assets/gameBoard.png"
        />
        <h3> Select your words! </h3>
      </div>
      <div class="blue slide">Slide 2</div>
      <div class="grey slide">Slide 3</div>
      <div class="red slide">Slide 4</div>
    </div>
  </div>
      <div class="navigator">
        <div @click="prevSlide" class="lightgrey"> &laquo; </div>
        <div @click="changeSlide(1)" class="green">1</div>
        <div @click="changeSlide(2)" class="blue">2</div>
        <div @click="changeSlide(3)" class="grey">3</div>
        <div @click="changeSlide(4)" class="red">4</div>
        <div @click="nextSlide" class="lightgrey"> &raquo; </div>
  </div>
</div>
</template>

<script>
export default {
 data() {
     return{
        activeSlide: 1
     }
  },
  computed: {
    styleObject: function() {
      const width = 0 - ((this.activeSlide-1) * 700);
      return {
        transform: 'translateX('+width+'px)'
      }
    }
  },
  methods: {
    changeSlide: function(num) {
      this.activeSlide = num;
    },
    nextSlide: function() { 
      if(this.activeSlide < 4) 
        this.activeSlide++;
    },
    prevSlide: function() {
      if(this.activeSlide > 1) 
        this.activeSlide--;
    }
  }
}
</script>

<style>
.slider-container {
  overflow: hidden;
  width: 700px;
  height: 300px;
  position: absolute;
  margin: 10% auto;
  left: 50%;
  transform: translate(-50%, -50%);
}
.slider {
  width: 2800px;
  height: 300px;
  overflow: hidden;
  transition: all 0.32s ease;
}
.slide {
  float: left;
  width: 700px;
  height: 300px;
  font-size: 40px;
  color: #eee;
  padding-top: 70px;
}
.green {
  background: #1abc9c;
}
.blue {
  background: #3498db;
}
.grey {
  background: #34495e;
}
.red {
  background: #e74c3c;
}
.lightgrey {
  background: #95a5a6;
}
.navigator {
  text-align: left;
  display: inline-block;
  margin: 17% auto;
  overflow: hidden;
  padding-bottom: 10px;
  padding-top: 10px;
}
.navigator div {
  float: left;
  color: #ecf0f1;
  border-radius: 50%;
  padding: 7px 11px;
  margin: 0 10px;
  cursor: pointer;
  box-shadow: 0 3px 17px rgba(0, 0, 0, .3);
  transition: all 0.32s ease;
  position: relative;
}
.navigator li:hover {
  transform: translateY(-2px);
}


</style>
