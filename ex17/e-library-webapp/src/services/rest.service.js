import axios from "axios";

const RestService = {
    init(baseURL) {
        axios.defaults.baseURL = baseURL;
    },

    get(resource, options = {}) {
        return axios.get(resource, options);
    },

    post(resource, data, options = {}) {
        return axios.post(resource, data, options);
    },

    put(resource, data) {
        return axios.put(resource, data);
    },

    delete(resource) {
        return axios.delete(resource);
    }
};

export {RestService};
