<template>
  <v-container>
    <v-data-table
        :headers="headers"
        :items="authors"
        sort-by="id"
        class="elevation-1"
        :loading="loading"
        loading-text="Идёт загрузка..."
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Жанры</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-if="showSelectAction" v-slot:item.actions="{ item }">
        <v-btn color="primary" elevation="2" @click="selectItem(item)">ВЫБРАТЬ</v-btn>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="initialize">Reset</v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import {mapState} from "vuex/dist/vuex.mjs";
import {mapActions} from "vuex";

export default {
  name: "Authors",
  props: ['showSelectAction'],
  data: () => ({
    loading: false,
    dialog: false,
    dialogDelete: false,
    headers: [
      {
        text: 'ID', value: 'id', align: 'center', sortable: true,
      },
      {
        text: 'ФИО', value: 'fullName', align: 'left', sortable: true
      },
      {
        text: 'Действия', value: 'actions', sortable: false
      }
    ],
    authors: [],
    selectedIndex: -1,
    selectedItem: {
      id: 0,
      name: ''
    },
  }),

  computed: {
    ...mapState("author", ["authorsData"]),

  },

  watch: {
    dialog(val) {
      val || this.close()
    },
  },

  created() {
    this.initialize()
  },

  methods: {
    ...mapActions("author", ["get"]),

    async initialize() {
      this.loading = true;
      await this.get();
      this.authors = this.authorsData;
      this.loading = false;
    },

    selectItem(item) {
      this.selectedIndex = this.authors.indexOf(item)
      this.selectedItem = Object.assign({}, item)
      this.$emit('authorSelected', {
        author: this.selectedItem
      })
    },

    close() {
      this.dialog = false
    },

  }
}
</script>