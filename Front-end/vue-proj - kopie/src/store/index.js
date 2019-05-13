import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    Word: {
      word: "",
      score: 0,
      letterIndexList: [],
      isValid: undefined
    },
    Player: {
      uuid: "",
      name: "",
      totalScore: 0,
    },
    gameOver: false,
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
    getInvalidMove: state => {
      return state.invalidMove;
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
      state.Word.word += payload.letter;
      state.Word.letterIndexList.push(payload.letterIndex);
      state.Word.letterIndex = payload.letterIndex;
    },
    addWord(state, payload){
      state.wordList.push(payload);
    },
    removeLetter(state, payload){
      state.Word.letterIndexList = payload.LetterIndexList;
      state.Word.word = payload = payload.WordList;
    },
    createPlayer(state, payload){
      state.Player.uuid = payload.uuid;
      state.Player.name = payload.name;
      state.Player.totalScore = payload.totalScore;
    },
    resetWord(state){
      state.Word = {
        word: "",
        score: 0,
        letterIndexList: [],
        isValid: undefined,
      } 
    },
    resetGame(state) {
      state.Word = {
        word: "",
        score: 0,
        letterIndexList: [],
        isValid: state.isValid,
      };
      state.Player.totalScore = 0;
      state.gameOver = false;
      state.wordList = [];
      state.invalidMove = false;
    },
    adjustInvalidMove(state, payload) {
      state.invalidMove = payload;

      if(payload){
        state.Word = {
          word: "",
          score: 0,
          letterIndexList: [],
          isValid: state.isValid,
        };
      }
    },
    addScorePoints(state, payload){
      state.wordList.forEach(w => {
        if(w == payload.word)
          w.score += payload.score;
      });
      state.Player.totalScore += payload.score;
    },
    stateGameOver(state) {
      state.gameOver = true;
    }
  },
});