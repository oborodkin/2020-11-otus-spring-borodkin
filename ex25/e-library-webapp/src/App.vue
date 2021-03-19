<template>
  <v-app>
    <v-main>
      <v-tabs>
        <v-tab :to="'/'">Главная</v-tab>
        <v-tab v-if="checkRouteAccess('books')" :to="'books'">Книги</v-tab>
        <v-tab v-if="checkRouteAccess('authors')" :to="'authors'">Авторы</v-tab>
        <v-tab v-if="checkRouteAccess('genres')" :to="'genres'">Жанры</v-tab>
      </v-tabs>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import {mapGetters} from "vuex/dist/vuex.mjs";
import {mapActions} from "vuex";

export default {
  name: "App",

  data: () => ({}),

  computed: {
    ...mapGetters("user", ["checkRouteAccess"]),
  },

  methods: {
    ...mapActions("user", ["getCurrentUser"]),

    async initialize() {
      await this.getCurrentUser();
    }
  },

  created() {
    this.initialize()
  }
};
</script>
