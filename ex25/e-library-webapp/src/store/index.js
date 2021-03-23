import Vue from "vue";
import Vuex from "vuex";

import genre from "./genre.module";
import book from "./book.module";
import author from "./author.module";
import user from "./user.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    genre,
    book,
    author,
    user
  },
});