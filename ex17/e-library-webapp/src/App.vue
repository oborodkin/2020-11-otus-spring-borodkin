<template>
  <v-app>
    <v-main>
      <v-tabs>
        <v-tab :to="'home'">Главная</v-tab>
        <v-tab :to="'books'">Книги</v-tab>
        <v-tab :to="'authors'">Авторы</v-tab>
        <v-tab :to="'genres'">Жанры</v-tab>
      </v-tabs>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: "App",

  data: () => ({
    //
  })
};
</script>
