import {RestService} from "@/services/rest.service";

const BookService = {
    get: async function (pageable) {
        let response = await RestService.get("books");
        return response.data;
    }
};

export {BookService};
