<template>
  <v-container>
    <v-data-table
        :headers="headers"
        :items="books"
        sort-by="id"
        class="elevation-1"
        :loading="loading"
        loading-text="Идёт загрузка..."
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Книги</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>

          <v-dialog v-model="dialog" max-width="1200">
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-if="checkActionAccess('newBook')" color="primary" dark class="mb-2" v-bind="attrs" v-on="on">Добавить</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="6" sm="6" md="6">
                      <v-text-field v-model="editedItem.title" label="Название книги"></v-text-field>
                    </v-col>

                    <v-col cols="6" sm="6" md="6">
                      <v-text-field readonly v-model="editedItem.genre.name" label="Жанр книги"
                                    append-icon="mdi-file-document-edit"
                                    @click:append="dialogGenre = true"
                      >
                      </v-text-field>

                      <div class="text-center">
                        <v-dialog v-model="dialogGenre" width="900">
                          <v-card>
                            <v-card-title class="headline grey lighten-2">Выберить жанр для книги</v-card-title>

                            <Genres showSelectAction="true" @genreSelected="onGenreSelected"></Genres>

                            <v-divider></v-divider>

                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="primary" text @click="dialogGenre = false">Закрыть</v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </div>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="6" sm="6" md="6">
                      <v-list flat>
                        <v-subheader>Автор(ы)
                          <v-btn icon small @click="dialogAuthors = true">
                            <v-icon>mdi-account-plus</v-icon>
                          </v-btn>

                        </v-subheader>
                        <v-list-item v-for="(author, i) in editedItem.authors" :key="i">
                          <v-list-item-content>
                            <v-list-item-title v-text="author.fullName"></v-list-item-title>
                          </v-list-item-content>
                          <v-list-item-action>
                            <v-btn elevation="2" icon small @click="removeAuthor(author.id)">
                              <v-icon>mdi-delete</v-icon>
                            </v-btn>
                          </v-list-item-action>
                        </v-list-item>
                      </v-list>
                      <div class="text-center">
                        <v-dialog v-model="dialogAuthors" width="900">
                          <v-card>
                            <v-card-title class="headline grey lighten-2">Выберите автора для книги</v-card-title>

                            <Authors showSelectAction="true" @authorSelected="onAuthorSelected"></Authors>

                            <v-divider></v-divider>

                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="primary" text @click="dialogAuthors = false">Закрыть</v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </div>
                    </v-col>

                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Отмена</v-btn>
                <v-btn color="blue darken-1" text @click="save">Сохранить</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="headline">Удалить?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">Нет</v-btn>
                <v-btn color="blue darken-1" text @click="deleteItemConfirm">Да</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>

      <template v-slot:item.authors="{ item }">
        <v-list flat>
          <v-list-item v-for="(author, i) in item.authors" :key="i">
            <v-list-item-content>
              <v-list-item-title v-text="author.fullName"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </template>

      <template v-slot:item.actions="{ item }">
        <v-icon v-if="checkActionAccess('editBook')" small class="mr-2" @click="editItem(item)">mdi-file-document-edit</v-icon>
        <v-icon v-if="checkActionAccess('deleteBook')" small @click="deleteItem(item)">mdi-delete</v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="initialize">Reset</v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import {mapGetters, mapState} from "vuex/dist/vuex.mjs";
import {mapActions} from "vuex";
import Genres from "@/components/Genres";
import Authors from "@/components/Authors";

export default {
  name: "Books",
  components: {
    Authors,
    Genres
  },
  data: () => ({
    loading: false,
    dialog: false,
    dialogDelete: false,
    dialogGenre: false,
    dialogAuthors: false,
    headers: [
      {
        text: 'ID', value: 'id', align: 'center', sortable: true,
      },
      {
        text: 'Название', value: 'title', align: 'left', sortable: true
      },
      {
        text: 'Жанр', value: 'genre.name', align: 'left', sortable: true
      },
      {
        text: 'Автор(ы)', value: 'authors', align: 'left', sortable: true
      },
      {
        text: 'Действия', value: 'actions', sortable: false
      }
    ],
    books: [],
    selectedIndex: -1,
    selectedItem: {
      id: 0,
      title: '',
      genre: {
        id: 0,
        name: ''
      },
      authors: []
    },
    editedIndex: -1,
    editedItem: {
      id: 0,
      title: '',
      genre: {
        id: 0,
        name: ''
      },
      authors: []
    },
    defaultItem: {
      id: 0,
      title: '',
      genre: {
        id: 0,
        name: ''
      },
      authors: []
    },
  }),

  computed: {
    ...mapState("book", ["booksData"]),
    ...mapGetters("user", ["checkActionAccess"]),

    formTitle() {
      return this.editedIndex === -1 ? 'Добавить' : 'Изменить'
    },
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },

  methods: {
    ...mapActions("book", ["get", "put", "post", "delete"]),

    async initialize() {
      this.loading = true;
      await this.get();
      this.books = this.booksData;
      this.loading = false;
    },

    selectItem(item) {
      this.selectedIndex = this.books.indexOf(item)
      this.selectedItem = Object.assign({}, item)
      this.selectedItem.authors = item.authors.slice();
      this.selectedItem.genre = Object.assign({}, item.genre)
    },

    editItem(item) {
      this.editedIndex = this.books.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.editedItem.authors = item.authors.slice();
      this.editedItem.genre = Object.assign({}, item.genre)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.books.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.editedItem.authors = item.authors.slice();
      this.editedItem.genre = Object.assign({}, item.genre)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      this.delete(this.editedItem.id);
      this.books.splice(this.editedIndex, 1)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedItem.authors = this.defaultItem.authors.slice();
        this.editedItem.genre = Object.assign({}, this.defaultItem.genre)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedItem.authors = this.defaultItem.authors.slice();
        this.editedItem.genre = Object.assign({}, this.defaultItem.genre)
        this.editedIndex = -1
      })
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.books[this.editedIndex], this.editedItem)
        this.books[this.editedIndex].authors = this.editedItem.authors.slice();
        Object.assign(this.books[this.editedIndex].genre, this.editedItem.genre)
        this.put(this.editedItem);
      } else {
        this.books.push(this.editedItem);
        this.post(this.editedItem);
      }
      this.close()
    },

    onGenreSelected(data) {
      this.editedItem.genre = data.genre;
      this.dialogGenre = false;
    },

    onAuthorSelected(data) {
      this.removeAuthor(data.author.id);
      this.editedItem.authors.push(data.author);
      this.dialogAuthors = false;
    },

    removeAuthor(id) {
      this.editedItem.authors.forEach((author, i) => {
        if (author.id == id) {
          this.editedItem.authors.splice(i, 1)
        }
      });
    }

  }
}
</script>