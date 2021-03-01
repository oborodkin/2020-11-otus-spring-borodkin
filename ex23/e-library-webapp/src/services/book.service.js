import {RestService} from "@/services/rest.service";

const BookService = {
    get: async function () {
        let response = await RestService.get("books");
        return response.data;
    },
    put: async function (book) {
        let formData = new FormData();
        formData.append("title", book.title);
        formData.append("genreId", book.genre.id);
        book.authors.forEach(author => {
            formData.append("authorId", author.id);
        });
        await RestService.put(`books/${book.id}`, formData);
    },
    post: async function (book) {
        let formData = new FormData();
        formData.append("title", book.title);
        formData.append("genreId", book.genre.id);
        book.authors.forEach(author => {
            formData.append("authorId", author.id);
        });
        let response = await RestService.post(`books`, formData);
        return response.data;
    },
    delete: async function (id) {
        let response = await RestService.delete(`books/${id}`);
    },

};

export {BookService};
