<template>
  <v-container>
    <v-form @submit.prevent="save()">
      <v-card>
        <v-card-text>
          <v-text-field
            label="Login"
            name="user.login"
            prepend-icon="mdi-account"
            type="text"
            v-model="user.login"
          ></v-text-field>

          <v-text-field
            v-model="user.email"
            label="Email"
            name="user.email"
            prepend-icon="mdi-email"
            type="email"
          ></v-text-field>

          <v-text-field
            v-model="user.password"
            label="Password"
            name="user.password"
            prepend-icon="mdi-lock"
            type="password"
          ></v-text-field>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn type="submit" color="primary">Register</v-btn>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-container>
</template>

<script>
import auth from "../common/auth";
import UsersRepository from "../repositories/UsersRepository";

export default {
  data() {
    return {
      user: {
        login: "",
        email: "",
        password: ""
      }
    };
  },
  methods: {
    async save() {
      try {
        await UsersRepository.save(this.user);
        auth
          .login({
            login: this.user.login,
            password: this.user.password
          })
          .then(() => {
            // vamos a la página anterior después de autenticarnos
            this.$router.go(-1);
          })
          .catch(err => {
            this.$notify({
              text: err.response.data.message,
              type: "error"
            });
          });
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
