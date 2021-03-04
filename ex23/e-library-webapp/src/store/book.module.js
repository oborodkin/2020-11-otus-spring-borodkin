import {BookService} from "@/services/book.service";

const state = {
    booksData: []
};

const getters = {};

const mutations = {
    setBooksData(state, books) {
        state.booksData = books;
    },
    setBookData(state, book) {
        state.booksData.forEach((b, i) => {
            if (b.id == book.id) {
                state.booksData.splice(i, 1)
            }
        });
        state.booksData.push(book);
    },
    deleteBookData(state, id) {
        state.booksData.forEach((b, i) => {
            if (b.id == id) {
                state.booksData.splice(i, 1)
            }
        });
    }
};

const actions = {
    async get({commit}) {
        const response = await BookService.get();
        commit("setBooksData", response);
    },
    async put({commit}, book) {
        await BookService.put(book);
        commit("setBookData", book);
    },
    async post({commit}, book) {
        const response = await BookService.post(book);
        commit("setBookData", response);
    },
    async delete({commit}, id) {
        await BookService.delete(id);
        commit("deleteBookData", id);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
