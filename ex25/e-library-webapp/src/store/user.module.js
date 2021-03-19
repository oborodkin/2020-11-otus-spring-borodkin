import {UserService} from "@/services/user.service";

const state = {
    userRoles: [],
    routesToRoles:
        {
            books: ["ROLE_ADMIN", "ROLE_READ_ALL", "ROLE_READ_AND_WRITE_ALL"],
            authors: ["ROLE_ADMIN", "ROLE_READ_ALL", "ROLE_READ_AND_WRITE_ALL", "ROLE_READ_AUTHORS"],
            genres: ["ROLE_ADMIN", "ROLE_READ_ALL", "ROLE_READ_AND_WRITE_ALL", "ROLE_READ_GENRES"]
        },
    actionsToRoles:
        {
            newBook: ["ROLE_ADMIN", "ROLE_READ_AND_WRITE_ALL"],
            editBook: ["ROLE_ADMIN", "ROLE_READ_AND_WRITE_ALL"],
            deleteBook: ["ROLE_ADMIN"]
        }

};

const getters = {
    checkRouteAccess: (state) => (routeName) => {
        if (state.routesToRoles[routeName]) {
            return state.routesToRoles[routeName].some(role => state.userRoles.indexOf(role) >= 0)
        } else {
            return false;
        }
    },
    checkActionAccess: (state) => (actionName) => {
        if (state.actionsToRoles[actionName]) {
            return state.actionsToRoles[actionName].some(role => state.userRoles.indexOf(role) >= 0)
        } else {
            return false;
        }
    }
};

const mutations = {
    setUserRoles(state, userRoles) {
        state.userRoles = userRoles;
    }
};

const actions = {
    async getCurrentUser({commit, dispatch}) {
        const response = await UserService.get();
        const userRoles = response.authorities.map(element => element.authority);
        commit("setUserRoles", userRoles);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
