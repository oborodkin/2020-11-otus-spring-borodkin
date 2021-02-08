import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import {RestService} from "@/services/rest.service";

Vue.config.productionTip = false;

RestService.init("/rest");

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
