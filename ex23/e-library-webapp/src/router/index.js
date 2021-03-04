import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import GenresView from "@/views/GenresView";
import BooksView from "@/views/BooksView";
import AuthorsView from "@/views/AuthorsView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/books",
    name: "books",
    component: BooksView
  },
  {
    path: "/authors",
    name: "authors",
    component: AuthorsView
  },
  {
    path: "/genres",
    name: "genres",
    component: GenresView
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
