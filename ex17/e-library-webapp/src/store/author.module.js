import {AuthorService} from "@/services/author.service";

const state = {
    authorsData: []
};

const getters = {};

const mutations = {
    setAuthorsData(state, authors) {
        state.authorsData = authors;
    }
};

const actions = {
    async get({commit, dispatch}) {
        const response = await AuthorService.get();
        commit("setAuthorsData", response);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
