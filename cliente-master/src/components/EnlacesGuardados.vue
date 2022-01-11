<template>
  <v-container>
    <v-row>
      <v-col cols="12" class="text-center">
        <h1>List of favourite movies</h1>
      </v-col>
      <v-col cols="12" sm="6" lg="3" v-for="enlace in enlaces" :key="enlace.id">
        <enlace-card :enlace="enlace"></enlace-card>
      </v-col>
    </v-row>
    <v-pagination
      class="mt-5"
      v-model="currentPage"
      :length="20"
      total-visible="10"
    ></v-pagination>
  </v-container>
</template>

<script>
import EnlaceCard from "./EnlaceCard.vue";
import UsersRepository from "@/repositories/UsersRepository";

export default {
  name: "EnlacesGuardados",
  components: { EnlaceCard },
  data() {
    return {
      enlaces: [],
      currentPage: 1
    };
  },
  watch: {
    currentPage() {
      this.fetchData();
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      this.enlaces = await UsersRepository.getEnlacesSave();
    }
  }
};
</script>
