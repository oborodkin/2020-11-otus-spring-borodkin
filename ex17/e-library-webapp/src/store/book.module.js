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
    },
    async put({}, book) {
        await BookService.put(book);
    },
    async post({}, book) {
        await BookService.post(book);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
