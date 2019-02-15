import Vue from "vue";
import Vuex from "vuex";
import axios from 'axios'
import { stat } from "fs";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    Word: {
      word: "",
      score: "",
      letterIndexList: [],
      isValidWord: undefined
    },
    Player: {
      uuid: "",
      name: "",
      totalScore: 0
    },
    gameOver: false,
    capturing: false,
    wordList: [],
    invalidMove: false
  },
  getters: {
    getWord: state => {
      return state.Word;
    },
    getWordList: state => {
      return state.wordList;
    },
    getTotalScore: state => {
      return state.Player.totalScore;
    },
    getInvalidMove: state => {
      return state.invalidMove;
    },
    getCapturing: state => {
      return state.capturing;
    },
    getGameOver: state => {
      return state.gameOver;
    },
    getPlayer: state => {
      return state.Player;
    }
  },
  mutations: {
    addLetter(state, payload) {
      //console.log(payload.letterIndex);

      state.Word.word += payload.letter;
      state.Word.letterIndexList.push(payload.letterIndex);
      state.Word.score = state.Word.word.length;
      state.Word.letterIndex = payload.letterIndex;
    },
    addWord(state, payload){
      state.wordList.push(payload);
    },
    createPlayer(state, payload){
      state.Player.uuid = payload.uuid;
      state.Player.name = payload.name;
      state.Player.totalScore = payload.totalScore;
    },
    resetWord(state){
      state.Word = {
        word: "",
        score: "",
        letterIndexList: [],
        isValidWord: undefined,
      } 
    },
    adjustInvalidMove(state, payload) {
      state.invalidMove = payload;
    },
    startCapturing(state) {
      state.capturing = true;
    },
    stopCapturing(state) {
      state.capturing = false;
    },
    addScorePoints(state, payload){
      state.Player.totalScore += payload;
    },
    stateGameOver(state) {
      state.gameOver = true;
    }
  },
  actions: {
    startCapturing({
      commit
    }) {
      commit("startCapturing");
    },
    stopCapturing({
      commit
    }) {
      commit("stopCapturing");
    }
  }
});