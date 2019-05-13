<template>
    <div class="lds-dual-ring">
        <div>   
        </div>
   </div>
</template>

<script>
export default {
    name: "LoadingIcon",
    data() {
        return {};
    },
}
</script>



<style>
@keyframes lds-dual-ring {
  0% {
    -webkit-transform: rotate(0);
    transform: rotate(0);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@-webkit-keyframes lds-dual-ring {
  0% {
    -webkit-transform: rotate(0);
    transform: rotate(0);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
.lds-dual-ring {
  position: relative;
}
.lds-dual-ring div {
  position: absolute;
  width: 15px;
  height: 15px;
  left: 45px;
  border-radius: 50%;
  border: 2px solid #000;
  border-color: #3880FF transparent #3880FF transparent;
  -webkit-animation: lds-dual-ring 1s linear infinite;
  animation: lds-dual-ring 1s linear infinite;
}
.lds-dual-ring {
  width: 20 !important;
  height: 20 !important;
  -webkit-transform: translate(-20, -20) scale(1) translate(20px, 20px);
  transform: translate(-20px, -20px) scale(1) translate(20px, 20px);
}
</style>