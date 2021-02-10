import {GenreService} from "@/services/genre.service";

const state = {
    genresData: []
};

const getters = {};

const mutations = {
    setGenresData(state, genres) {
        state.genresData = genres;
    }
};

const actions = {
    async get({commit, dispatch}) {
        const response = await GenreService.get();
        commit("setGenresData", response);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
