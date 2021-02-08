import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import GenresView from "@/views/GenresView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
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
