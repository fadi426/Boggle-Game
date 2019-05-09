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
                <td v-if="Word.isValidWord == true">{{ Word.score }}</td>
                <td v-else> 0 </td>
                <td > 
                    <img
                        style="width:30px;height:30px;"
                        v-if="Word.isValidWord == true"
                        src="@/assets/ok.png"
                    />
                    <img
                    style="width:30px;height:30px;"
                        v-if="Word.isValidWord == false"
                        src="@/assets/star.png"
                    />
                    <LoadingIcon
                        v-if="Word.isValidWord == undefined"
                    />
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
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
    height: 50px;
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
    background: #324960;
  }

  .table tr:nth-child(even) {
    background: #f8f8f8;
  }
}
</style>
