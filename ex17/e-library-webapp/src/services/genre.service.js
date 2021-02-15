import {RestService} from "@/services/rest.service";

const GenreService = {
    get: async function () {
        let response = await RestService.get("genres");
        return response.data;
    }
};

export {GenreService};
