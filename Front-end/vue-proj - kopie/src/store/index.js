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
    capturing: false,
    wordList: [],
    totalScore: 0,
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
      return state.totalScore;
    },
    getInvalidMove: state => {
      return state.invalidMove;
    },
    getCapturing: state => {
      return state.capturing;
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
    adjustInvalidMove(state) {
      state.invalidMove = true;
    },
    startCapturing(state) {
      state.capturing = true;
    },
    stopCapturing(state) {
      state.capturing = false;

      function ApiCheck(Word) {
        return new Promise((resolve) => {
          axios.post('http://192.168.0.11:8080/words', { 'word': Word.word, 'score': Word.score })
            .then((response) => {
              if (response.data.validWord == true)
                Word.isValidWord = true;
              if (response.data.validWord == false)
                Word.isValidWord = false;
              resolve()
            });
        });
      }

      async function saveWord() {
        if (state.Word.word && 
            !state.wordList.some(e => e.word === state.Word.word)) {
          state.wordList.push(state.Word);

          state.Word = {
            word: "",
            score: "",
            letterIndexList: [],
            isValidWord: undefined,
          },
         state.invalidMove = false;

         var Word = state.wordList[state.wordList.length -1];
          await ApiCheck(Word);
          console.log(Word.word + Word.isValidWord);
          
          if(Word.isValidWord)
          state.totalScore += Word.score;
        }
        else{
          state.Word = {
            word: "",
            score: "",
            letterIndexList: [],
            isValidWord: undefined,
          }
        }
      }
      saveWord();
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