// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import VueSwal from 'vue-swal'
import Ionic from '@ionic/vue';
import './registerServiceWorker'

Vue.use(VueSwal);
Vue.use(Ionic);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

Vue.config.productionTip = false;