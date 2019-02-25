import Vue from 'vue'
import Router from 'vue-router'
import Home from './components/Home'
import SinglePlayer from './components/SinglePlayer'
import GameRooms from './components/GameRooms'
import MultiPlayer from './components/MultiPlayer'

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
        path: '/singleplayer',
        name: 'singlePlayer',
        components: {
            maincontainer: SinglePlayer,
        }
      },
      {
        path: '/gamerooms',
        name: 'gamerooms',
        components: {
            maincontainer: GameRooms,
        }
      },
      {
        path: '/multiplayer/:uuid',
        name: 'multiplayer',
        components: {
            maincontainer: MultiPlayer,
        }
      },
    ]
  })
