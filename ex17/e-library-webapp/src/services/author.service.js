import {RestService} from "@/services/rest.service";

const AuthorService = {
    get: async function (pageable) {
        let response = await RestService.get("authors");
        return response.data;
    }
};

export {AuthorService};
