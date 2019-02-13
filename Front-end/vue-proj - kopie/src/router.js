import Vue from 'vue'
import Router from 'vue-router'
import Timer from './components/Timer'
import Home from './components/Home'
import SinglePlayer from './components/SinglePlayer'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
      {
        path: '/',
        name: 'home',
        components: {
            maincontainer: Home,
        }
      },
      {
        path: '/timer',
        name: 'timer',
        components: {
            maincontainer: Timer,
        }
      },
      {
        path: '/singleplayer',
        name: 'singlePlayer',
        components: {
            maincontainer: SinglePlayer,
        }
      },
  ]
})
