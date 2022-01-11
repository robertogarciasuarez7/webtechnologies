<template>
  <v-container>
    <v-col cols="12" class="text-center">
      <h1>Users</h1>
    </v-col>

    <div v-for="user in users" :key="user.id">
      <div>
        <v-container>
          <v-layout justify-center>
            <v-card width="40%">
              <v-card-title>
                <span>
                  <v-list-item-avatar>
                    <img
                      src="https://www.dropbox.com/s/lxnmktt5uw5hcqx/person.png?raw=1"
                    />
                  </v-list-item-avatar>
                </span>
                {{ user.login }}
                <v-spacer />
                ({{ user.authority }})
              </v-card-title>
              <v-divider />
              <v-card-text> Rented movies: {{ user.numFavoritos }} </v-card-text>

              <v-card-actions>
                <v-spacer />
                <v-btn
                  outlined
                  rounded
                  color="warning"
                  dark
                  :to="{ name: 'UserDetail', params: { userId: user.id } }"
                >
                  <v-icon>mdi-account-circle</v-icon>
                </v-btn>
                <div v-if="isAdmin">
                  <v-btn
                    class="ma-2"
                    outlined
                    color="submmit"
                    @click="cambiarauth(user.id)"
                    v-if="user.id"
                  >
                    <v-icon left>mdi-account</v-icon>Admin
                  </v-btn>

                  <v-btn
                    class="ma-2"
                    outlined
                    color="red"
                    @click="remove(user.id)"
                    v-if="user.id"
                  >
                    <v-icon left>mdi-delete</v-icon>Delete
                  </v-btn>
                </div>
              </v-card-actions>
            </v-card>
          </v-layout>
        </v-container>
      </div>
    </div>
    <v-pagination
      class="mt-5"
      v-model="currentPage"
      :length="20"
      total-visible="10"
    ></v-pagination>
  </v-container>
</template>

<script>
import UsersRepository from "@/repositories/UsersRepository";
import auth from "@/common/auth";

export default {
  name: "UserList",
  data() {
    return {
      users: [],
      enlaces: [],
      enlacesFav: null,
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
  computed: {
    isAdmin() {
      return auth.isAdmin();
    }
  },
  methods: {
    async fetchData() {
      this.users = await UsersRepository.getAll();
    },
    async remove(id) {
      await UsersRepository.delete(id);
      location.reload();
    },
    async cambiarauth(id) {
      await UsersRepository.update(id);
      location.reload();
    }
  }
};
</script>
