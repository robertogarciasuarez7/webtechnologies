<template>
  <v-container>
    <v-form @submit.prevent="userLogin">
      <v-card>
        <v-card-text>
          <v-text-field
            label="Login"
            name="login"
            prepend-icon="mdi-account"
            type="text"
            v-model="username"
          ></v-text-field>

          <v-text-field
            v-model="password"
            label="Password"
            name="password"
            prepend-icon="mdi-lock"
            type="password"
          ></v-text-field>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :to="{ name: 'Register' }" color="success"
            ><v-icon left>mdi-account-plus</v-icon>Sign Up</v-btn
          >
          <v-btn type="submit" color="primary">Login</v-btn>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-container>
</template>

<script>
import auth from "../common/auth";

export default {
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    userLogin() {
      auth
        .login({
          login: this.username,
          password: this.password
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
    }
  }
};
</script>
