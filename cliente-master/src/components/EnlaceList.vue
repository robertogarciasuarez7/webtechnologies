<template>
  <v-container>
    <v-row>
      <v-col cols="12" class="text-center">
        <div class="float-right">
          <v-btn :to="{ name: 'EnlaceCreate' }" color="primary">
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </div>
        <h1>Popular movies</h1>
      </v-col>
      <v-col cols="12" sm="6" lg="3" v-for="enlace in enlaces" :key="enlace.id">
        <enlace-card :enlace="enlace"></enlace-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import EnlaceCard from "./EnlaceCard.vue";
import EnlacesRepository from "@/repositories/EnlacesRepository";

export default {
  name: "EnlaceList",
  components: { EnlaceCard },
  data() {
    return {
      enlaces: []
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      this.enlaces = await EnlacesRepository.getNombre();
    }
  }
};
</script>
