<template>
  <v-card>
    <v-container v-if="enlace">
      <v-row>
        <h1>Details</h1>
        <v-spacer></v-spacer>
      </v-row>

      <v-row>
        <v-col cols="12" sm="6">
          <v-img :src="getImageSrc(enlace)"></v-img>
        </v-col>

        <v-col cols="12" sm="6">
          <v-row>
            <v-col cols="3"><span class="label">Name</span></v-col>
            <v-col cols="9">
              <span class="data">{{ enlace.nombre }}</span>
            </v-col>

            <v-col cols="3"><span class="label">Price</span></v-col>
            <v-col cols="9">
              <span class="data">{{ enlace.descripcion }}</span>
            </v-col>

            <v-col cols="3"> <span class="label">Category</span></v-col>
            <v-col cols="9">
              <span class="data" v-if="enlace.categoria != null">
                {{
                enlace.categoria.nombre
                }}
              </span>
            </v-col>

            <v-col cols="3"><span class="label">Year</span></v-col>
            <v-col cols="9">
              <span class="data">{{ enlace.enl }}</span>
            </v-col>

            <v-col cols="3" v-if="enlace.tags.length != 0">
              <span class="label">Tags: </span>
            </v-col>
            <v-col cols="9" v-if="enlace.tags.length != 0">
              <span class="data" v-if="enlace.tags != null">
                <div class="tags">
                  {{ tagsAsString }}
                </div>
              </span>
            </v-col>

            <v-col cols="3"><span class="label">Valoration</span></v-col>

            <v-col cols="9">
              <div class="valoracion">
                <div>{{ valoracion }}</div>
              </div>
            </v-col>

            <v-col cols="4" v-if="!IsValorado">
              <v-select v-if="isLogged"
                        v-model="userVote.valoracion"
                        :items="numeros"
                        item-value="valoracion"
                        label="Rate"
                        return-object
                        single-line
                        outlined
                        dense></v-select>
            </v-col>

            <v-col cols="4" v-if="!IsValorado">
              <v-btn v-if="isLogged"
                     class="ma-2"
                     @click="ValoracionEnlace(userVote.valoracion)"
                     color="orange accent-3"
                     dark>
                <v-icon dark left>mdi-star-check</v-icon>Rate!
              </v-btn>
            </v-col>

            <v-col cols="9" v-if="isLogged">
              <v-btn class="ma-2"
                     @click="favorito()"
                     color="green accent-3"
                     dark>
                <v-icon dark left>mdi-cart-outline</v-icon>Rent the movie!
              </v-btn>
            </v-col>
            <v-col cols="2" v-if="isLogged">
              <div v-if="isLogged">
                <div v-if="!booleanSave">
                  <v-btn @click="save()" text icon color="pink">
                    Add to your favourites! <v-icon disabled>mdi-heart</v-icon>
                  </v-btn>
                </div>
                <div v-else>
                  <v-btn @click="unsave()" text icon color="pink">
                    Add to your favourites! <v-icon>mdi-heart</v-icon>
                  </v-btn>
                </div>
              </div>
            </v-col>
            <v-col cols="9" v-if="isLogged">
              <div v-if="isAdmin">
                <v-btn class="ma-2"
                       @click="edit()"
                       outlined
                       color="green darken-2"
                       dark>
                  <v-icon dark left>mdi-pencil</v-icon>Edit
                </v-btn>
              </div>
            </v-col>

          </v-row>
        </v-col>
      </v-row>

      <v-card-actions>
        <v-spacer />
        <v-col cols="3" v-if="isLogged">
          <v-btn class="ma-2"
                 @click="back()"
                 outlined
                 color="orange darken-2"
                 dark>
            <v-icon dark left>mdi-arrow-left</v-icon>Back
          </v-btn>
        </v-col>
      </v-card-actions>
      <v-divider inset></v-divider>
    </v-container>
  </v-card>
</template>

<script>
import EnlacesRepository from "@/repositories/EnlacesRepository";
import UserVoteRepository from "@/repositories/UserVoteRepository";
import UsersRepository from "@/repositories/UsersRepository";
import { getImageSrcGenerator } from "./enlace-utils.js";
import store from "@/common/store";
import auth from "@/common/auth";

export default {
  name: "EnlaceDetail",
  data() {
    return {
      enlace: null,
      booleanFav: null,
      booleanSave: null,
      valoracion: false,
      tmpValoracion: 0,
      userLogged: store.state.user.id,
      user: store.state.user,
      userVote: {},
      numeros: [1, 2, 3, 4, 5],
      valorado: null
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.fetchData(to.params.enlaceId);
    next();
  },
  async created() {
    try {
      this.fetchData(this.$route.params.enlaceId);

      this.getEnlaceValoracion();
      if (this.user.logged == true) {
        this.valorado = await EnlacesRepository.comprobarvalorado(
          this.$route.params.enlaceId
        );
        this.booleanFav = await EnlacesRepository.comprobarfavorito(
          this.$route.params.enlaceId
        );
        this.booleanSave = await EnlacesRepository.comprobarguardado(
          this.$route.params.enlaceId
        );
      }
    } catch (err) {
      this.$notify({
        text: err.response.data.message,
        type: "error"
      });
    }
  },
  methods: {
    async fetchData(enlaceId) {
      this.enlace = await EnlacesRepository.getEnlace(enlaceId);

      if (!this.enlace) {
        alert("Enlace not found!");
        this.$router.go(-1);
      }
    },
    back() {
      this.$router.go(-1);
    },

    async favorito() {
      this.booleanFav = await EnlacesRepository.marcarfavorita(this.enlace.id);
    },
    async unfavorito() {
      this.booleanFav = await EnlacesRepository.desmarcarfavorita(
        this.enlace.id
      );
      location.reload();
    },
    async save() {
      this.booleanFav = await EnlacesRepository.marcarguardado(this.enlace.id);
      location.reload();
    },
    async unsave() {
      this.booleanFav = await EnlacesRepository.desmarcarguardado(
        this.enlace.id
      );
      location.reload();
    },
    edit() {
      this.$router.replace({
        name: "EnlaceUpdate",
        params: { id: this.enlace.id }
      });
    },

    getImageSrc: getImageSrcGenerator(true),

    async ValoracionEnlace(v) {
      this.userVote.user = await UsersRepository.getById(this.userLogged);
      this.userVote.enlace = this.enlace;
      this.userVote.valoracion = v;

      await UserVoteRepository.save(this.userVote);
      this.getEnlaceValoracion();
      location.reload();
    },

    async getEnlaceValoracion() {
      this.tmpValoracion = await UserVoteRepository.avgValoracion(
        this.$route.params.enlaceId
      );
      if (this.tmpValoracion >= 0) {
        this.valoracion = this.tmpValoracion.toFixed(2);
      } else {
        this.valoracion = 0;
      }
    }
  },

  computed: {
    IsValorado() {
      if (this.valorado) {
        return true;
      } else {
        return false;
      }
    },
    isAdmin() {
      return auth.isAdmin();
    },
    isUser() {
      return auth.isUser();
    },
    isLogged() {
      return this.user.logged;
    },
    tagsAsString() {
      return this.enlace.tags.map(t => t.nombre).join(", ");
    }
  }
};
</script>

<style scoped>
.label {
  font-weight: bold;
}
.tags {
  font-style: italic;
}
</style>
