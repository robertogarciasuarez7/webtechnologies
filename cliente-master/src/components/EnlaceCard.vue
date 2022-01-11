<template>
  <v-card :to="{ name: 'EnlaceDetail', params: { enlaceId: enlace.id } }">

    <v-img class="red--text align-end" :src="getImageSrc(enlace)"> </v-img>
    <v-card-title class="headline">
      {{ enlace.nombre }}
    </v-card-title>
    <v-card-text>
      <span class="espacio-izquierda">
        Valoration: {{ enlace.valoracion }}
      </span>
    </v-card-text>
  </v-card>
</template>

<script>
import { getImageSrcGenerator } from "./enlace-utils.js";

export default {
  name: "EnlaceCard",
  props: {
    enlace: {
      type: Object,
      required: true
    }
  },
  methods: {
    getImageSrc: getImageSrcGenerator(false)
  }
};
</script>

<style scoped>
.espacio-izquierda {
  margin-left: 6px;
}
</style>
