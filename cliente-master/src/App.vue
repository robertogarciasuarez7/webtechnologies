<template>
  <v-app>
    <!-- Notificaciones usando vue-notification -->
    <notifications :max="3" :width="400" position="top center" />
    <v-navigation-drawer v-model="app.drawer" app>
      <!-- Si hay usuario se muestra su info y una imagen (mock) -->
      <template v-slot:prepend>
        <v-list-item two-line v-if="isLogged">
          <v-list-item-avatar>
            <img
              src="https://www.dropbox.com/s/lxnmktt5uw5hcqx/person.png?raw=1"
            />
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>
              <router-link
                :to="{ name: 'UserDetail', params: { userId: user.id } }"
              >
                {{ user.login }}
              </router-link>
            </v-list-item-title>

            <v-list-item-subtitle
              >Logged as {{ user.authority }}</v-list-item-subtitle
            >
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-list dense>
        <v-list-item to="/login" v-if="!isLogged">
          <v-list-item-icon>
            <v-icon>mdi-login</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- Enlazando una ruta usando el nombre en vez del path. Esto permite
        añadir parámetros si fuera necesario directamente desde el HTML.-->

        <v-list-item to="/findbytag">
          <v-list-item-icon>
            <v-icon>mdi-magnify</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Search</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="/enlaces">
          <v-list-item-icon>
            <v-icon>mdi-star</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Populars</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="/enlaces/favoritos">
          <v-list-item-icon>
            <v-icon>mdi-cart</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Cart</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item to="/enlaces/guardados">
          <v-list-item-icon>
            <v-icon>mdi-heart</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Favourites</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <div v-for="categoria in categorias" :key="categoria.id">
          <v-list-item
            :to="{ name: 'Categoria', params: { categoriaId: categoria.id } }"
          >
            <v-list-item-icon>
              <v-icon>mdi-folder-open</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ categoria.nombre }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </div>
        <div v-if="isLogged">
          <v-list-item to="/users">
            <v-list-item-icon>
              <v-icon>mdi-account-multiple</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Users</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </div>
      </v-list>

      <template v-slot:append v-if="isLogged">
        <div class="pa-2">
          <v-btn block @click="logout()">Logout</v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-app-bar app color="primary" dark>
      <v-app-bar-nav-icon
        @click.stop="app.drawer = !app.drawer"
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="title">
        <router-link to="/" tag="span">Home</router-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-toolbar-items>
        <v-btn active-class="hide-active" icon to="/login" v-if="!isLogged">
          <v-icon>mdi-login</v-icon>
        </v-btn>
        <v-btn icon @click="logout()" v-if="isLogged">
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import store from "@/common/store";
import auth from "@/common/auth";
import CategoriasRepository from "@/repositories/CategoriasRepository.js";

export default {
  name: "App",
  data() {
    return {
      app: store.state.app,
      user: store.state.user,
      categorias: []
    };
  },
  async created() {
    this.categorias = await CategoriasRepository.getAll();
  },

  computed: {
    isLogged() {
      return this.user.logged;
    }
  },

  methods: {
    logout() {
      auth.logout();
      this.$router.push({ name: "Login" });
    }
  }
};
</script>

<style lang="scss" src="./App.scss"></style>
