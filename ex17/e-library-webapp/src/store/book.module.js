import {BookService} from "@/services/book.service";

const state = {
    booksData: []
};

const getters = {};

const mutations = {
    setBooksData(state, books) {
        state.booksData = books;
    }
};

const actions = {
    async get({commit, dispatch}) {
        const response = await BookService.get();
        commit("setBooksData", response);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
