<template>
  <v-form>
    <v-layout justify-center>
      <v-card width="50%" class="espacio-arriba">
        <v-card-title>
          <v-text-field
            v-model="enlace.nombre"
            label="Name"
            counter="25"
            outlined
            required
          ></v-text-field>
        </v-card-title>
        <v-card-title>
          <v-text-field
            v-model="enlace.descripcion"
            label="Price"
            counter="10"
            outlined
          ></v-text-field>
        </v-card-title>

        <v-card-title>
          <v-text-field
            v-model="enlace.enl"
            label="Year"
            counter="6"
            outlined
          ></v-text-field>
        </v-card-title>
        <v-card-text>
          <input type="file" class="d-none" ref="hiddenFileInput" />
          <v-btn block text @click="uploadImage()">
            Select an image
          </v-btn>
        </v-card-text>

        <v-col cols="12">
          <v-select
            v-model="enlace.categoria"
            :items="categorias"
            item-text="nombre"
            label="Select a category"
            return-object
            single-line
            outlined
            dense
            clearable
          ></v-select>
        </v-col>
        <v-col cols="12">
          <v-autocomplete
            v-model="enlace.tags"
            :items="tags"
            item-text="nombre"
            label="Select a tag"
            return-object
            single-line
            outlined
            dense
            multiple
            clearable
          >
          </v-autocomplete>
        </v-col>

        <v-card-actions class="d-sm-flex">
          <v-btn class="ma-2"
                 @click="back()"
                 outlined
                 color="orange darken-2"
                 dark>
            <v-icon dark left>mdi-arrow-left</v-icon>Back
          </v-btn>
          <v-spacer />
          <v-btn class="ma-2" outlined color="primary" dark @click="save()">
            <v-icon dark right>mdi-checkbox-marked-circle</v-icon>Submit
          </v-btn>

          <div v-if="isAdmin">
            <v-btn class="ma-2"
                   outlined
                   color="red"
                   @click="remove()"
                   v-if="enlace.id">
              <v-icon left>mdi-delete</v-icon>Delete
            </v-btn>
          </div>

        </v-card-actions>
      </v-card>
    </v-layout>
  </v-form>
</template>

<script>
import EnlacesRepository from "@/repositories/EnlacesRepository.js";
import CategoriasRepository from "@/repositories/CategoriasRepository.js";
import TagsRepository from "@/repositories/TagsRepository.js";
import auth from "@/common/auth";

export default {
  name: "EnlaceForm",
  data() {
    return {
      enlace: {},
      categorias: [],
      tags: []
    };
  },
  async created() {
    if (this.$route.params.enlaceId) {
      this.enlace = await EnlacesRepository.getEnlace(
        this.$route.params.enlaceId
      );

      this.categorias = await CategoriasRepository.getAll();
      this.tags = await TagsRepository.getAll();
    } else {
      this.enlace = {};
      this.categorias = await CategoriasRepository.getAll();
      this.tags = await TagsRepository.getAll();
    }
  },
  computed: {
    isAdmin() {
      return auth.isAdmin();
    }
  },
  methods: {
    async save() {
      try {
        const savedEnlace = await EnlacesRepository.saveEnlace(this.enlace); 
        if (this.$refs.hiddenFileInput.files[0]) {
          //solo permitimos coger un fichero
          await EnlacesRepository.saveImage(
            savedEnlace.id, //para este enlace
            this.$refs.hiddenFileInput.files[0] //enviamos el fichero
          );
        }

        this.$router.replace({
          name: "EnlaceDetail",
          params: { enlaceId: savedEnlace.id }
        }); 
      } catch (err) {
        this.$notify({
          text: err.response.data.message,
          type: "error"
        });
      }
    },
    back() {
      this.$router.go(-1);
    },
    async remove() {
      await EnlacesRepository.removeEnlace(this.enlace.id);
      this.$router.replace({ name: "EnlaceList" });
    },
    uploadImage() {
      this.$refs.hiddenFileInput.click();
    }
  }
};
</script>

<style scoped>
.espacio-arriba {
  margin-top: 48px;
}
</style>
