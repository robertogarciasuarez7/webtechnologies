<template>
  <v-container>
    <v-row>
      <v-col cols="12" class="text-center">
        <div class="float-right">
          <v-btn :to="{ name: 'EnlaceCreate' }" color="primary">
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </div>

        <h1>Category {{ categoria.nombre }}</h1>
      </v-col>
      <v-col cols="12" sm="6" lg="3" v-for="enlace in enlaces" :key="enlace.id">
        <enlace-card :enlace="enlace"></enlace-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import EnlaceCard from "./EnlaceCard.vue";
//import EnlacesRepository from "@/repositories/EnlacesRepository";
import CategoriasRepository from "@/repositories/CategoriasRepository";

export default {
  name: "Categoria",
  components: { EnlaceCard },
  data() {
    return {
      categoria: {},
      enlaces: [],
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.fetchData(to.params.categoriaId);
    next();
  },
  created() {
    this.fetchData(this.$route.params.categoriaId);
  },
  methods: {
    async fetchData(categoriaId) {
      try {
        this.categoria = await CategoriasRepository.findById(categoriaId);
        this.enlaces = await CategoriasRepository.findByCategoria(categoriaId);
      } catch (err) {
        this.$notify({
          text: err.response.data.message,
          type: "error"
        });
      }
    }
  }
};
</script>
