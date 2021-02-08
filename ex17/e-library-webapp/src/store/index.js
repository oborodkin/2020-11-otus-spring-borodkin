import Vue from "vue";
import Vuex from "vuex";

import genre from "./genre.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    genre
  },
});