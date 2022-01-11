<template>
  <v-container>
    <div id="header">
      <div class="text-center clear">
        <h2>Search by tags:</h2>
      </div>
      <div class="search">
        <v-form ref="form" @submit.prevent="findByTag">
          <v-autocomplete
            v-model="search.text"
            :items="tags"
            item-text="nombre"
            counter
            label="New search"
            outlined
            color="primary"
            rows="1"
            :rules="requiredField"
            class="searchText"
            clearable
          >
          </v-autocomplete>

          <v-btn type="submit" outlined color="primary" class="searchButton">
            <v-icon center>mdi-send</v-icon>
          </v-btn>
        </v-form>
        <v-container>
          <v-card>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                lg="3"
                v-for="enlace in enlaces"
                :key="enlace.id"
              >
                <enlace-card :enlace="enlace"></enlace-card>
              </v-col>
            </v-row>
          </v-card>
        </v-container>
        <h3 class="text-center">{{ enlaces.length }} found</h3>

        <div>
          <v-btn
            class="ma-2"
            @click="back()"
            outlined
            color="orange darken-2"
            dark
          >
            <v-icon dark left>mdi-arrow-left</v-icon>Back
          </v-btn>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import EnlaceCard from "./EnlaceCard.vue";
import TagsRepository from "@/repositories/TagsRepository";

export default {
  data: () => ({
    search: {},
    enlaces: [],
    requiredField: [v => !!v || "Field is required"],
    error: null,
    tags: []
  }),
  components: { EnlaceCard },
  async created() {
    this.tags = await TagsRepository.getAll();
  },
  methods: {
    async findByTag() {
      this.enlaces = await TagsRepository.findByTag(this.search.text);
    }, //SIN EXCEPCION SACAR UNA LISTA VACIA SIEMPRE
    back() {
      this.$router.go(-1);
    }
  }
};
</script>

<style>
.search {
  margin: 0 auto;
}
.search .searchText {
  width: 80%;
  float: left;
}
.search .searchButton {
  width: 10%;
  margin-left: 15px;
  height: 59px !important;
}
.searchButton i {
  font-size: 40px;
}
.search h3 {
  clear: both;
}
.search form {
  width: 80%;
  margin: 0 auto;
  margin-bottom: 65px;
  clear: both;
}
</style>
