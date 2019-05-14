<template>
    <div class="wordBoardContainer">
        <table class="table">
            <thead>
                <th>Guessed words</th>
                <th>Score</th>
                <th>Valid</th>
            </thead>
            <tbody>
                <tr v-for="Word in getWordList" :key="Word.state" >
                <td>{{ Word.word }}</td>
                <td v-if="Word.isValid == true">{{ Word.score }}</td>
                <td v-else> 0 </td>
                <td > 
                  <!-- show check mark when the word is valid -->
                  <ion-icon name="checkmark-circle-outline" 
                    style="zoom: 1.5; color: #4fc3a1;"
                    v-if="Word.isValid == true">
                  </ion-icon>
                  <!-- show X mark when the word is innvalid -->
                  <ion-icon name="close-circle-outline"
                    style="zoom: 1.5; color: #c34f71;"
                    v-if="Word.isValid == false"
                  >
                  <!-- show loading mark when validity has not been checked yet -->
                  </ion-icon>
                    <LoadingIcon
                        v-if="Word.isValid == undefined"
                    />
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import LoadingIcon from "../assets/LoadingIcon";
export default {
  name: "WordBoard",
  components: { LoadingIcon },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["getWordList"])
  }
};
</script>

<style lang="scss">
.wordBoardContainer {
    margin-top: 20px; 
    height: 350px;; 
    overflow: auto;
  .table {
    border-radius: 5px;
    font-size: 12px;
    font-weight: normal;
    border: none;
    border-collapse: collapse;
    max-width: 100%;
    white-space: nowrap;
    background-color: white;
    display: table;
    width: 100%;
    table-layout: fixed;
  }

  .table td,
  .table th {
    text-align: center;
    padding: 8px;
  }

  .table td {
    border-right: 1px solid #f8f8f8;
    font-size: 12px;
  }

  .table thead th {
    color: #ffffff;
    background: #4fc3a1;
  }

  .table thead th:nth-child(odd) {
    color: #ffffff;
    background: #3880FF;
  }

  .table tr:nth-child(even) {
    background: #f8f8f8;
  }
}
</style>
