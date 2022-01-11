<template>
  <v-card class="espacio-izquierda">
    <v-container v-if="user">
      <v-row>
        <h1 class="espacio-izquierda">
          Public profile
        </h1>
        <v-spacer></v-spacer>
      </v-row>

      <v-row>
        <v-col cols="12" sm="6">
          <v-list-item-avatar>
            <img
              src="https://www.dropbox.com/s/lxnmktt5uw5hcqx/person.png?raw=1"
            />
          </v-list-item-avatar>
          <span class="data">{{ user.login }}</span>
          <v-col>
            <h4>In the app since:</h4>
            <span class="data">{{ user.fechaRegistro }}</span>
          </v-col>
        </v-col>

        <v-col cols="12" sm="6" lg="3">
          <v-row>
            <v-col cols="9"
              ><span class="label"
                >Rented: {{ user.numFavoritos }}</span
              ></v-col
            >
            <v-col cols="9"
              ><span class="label"
                >Favourites: {{ user.numGuardados }}</span
              ></v-col
            >

            <v-col cols="9"
              ><span class="label"
                >Rated: {{ user.numValorados }}</span
              ></v-col
            >
          </v-row>
        </v-col>
      </v-row>
      <v-divider inset></v-divider>
      <v-card>
        <v-row>
          <v-col
            cols="12"
            sm="6"
            lg="3"
            v-for="enlace in enlacesfav"
            :key="enlace.id"
          >
            <enlace-card :enlace="enlace"></enlace-card>
          </v-col>
        </v-row>
        <v-card-actions>
          <v-btn
            class="ma-2"
            @click="back()"
            outlined
            color="orange darken-2"
            dark
          >
            <v-icon dark left>mdi-arrow-left</v-icon>Back
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </v-card>
</template>

<script>
import UsersRepository from "@/repositories/UsersRepository";
import EnlaceCard from "./EnlaceCard.vue";

export default {
  name: "UserDetail",
  components: { EnlaceCard },
  data() {
    return {
      user: null,
      enlacesfav: [],
      enlacessave: [],
      enlaces: []
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.fetchData(to.params.userId);
    next();
  },
  async created() {
    try {
      this.fetchData(this.$route.params.userId);
      this.enlacesfav = await UsersRepository.getFavoritos(
        this.$route.params.userId
      );
    } catch (err) {
      this.$notify({
        text: err.response.data.message,
        type: "error"
      });
    }
  },
  methods: {
    async fetchData(userId) {
      this.user = await UsersRepository.getById(userId);
    },
    back() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.label {
  font-weight: bold;
}
.espacio-izquierda {
  margin-left: 50px;
}
</style>
