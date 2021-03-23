import {RestService} from "@/services/rest.service";

const UserService = {
    get: async function () {
        let response = await RestService.get("user");
        return response.data;
    }
};

export {UserService};
