import Vue from "vue";
import VueRouter from "vue-router";

import EnlaceList from "../components/EnlaceList.vue";
import Categoria from "../components/Categoria.vue";
import UserList from "../components/UserList.vue";
import UserDetail from "../components/UserDetail.vue";
import EnlacesFavoritos from "../components/EnlacesFavoritos.vue";
import EnlacesGuardados from "../components/EnlacesGuardados.vue";
import EnlaceDetail from "../components/EnlaceDetail.vue";
import EnlaceForm from "../components/EnlaceForm.vue";
import FindByTag from "../components/FindByTag.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";
import NotFound from "@/views/NotFound.vue";
import auth from "@/common/auth";
import store from "@/common/store";
const user = store.state.user;

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: { public: true, isLoginPage: true }
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: { public: true, isLoginPage: true }
  },
  {
    path: "/",
    redirect: "/enlaces"
  },
  {
    name: "FindByTag",
    path: "/findbytag",
    component: FindByTag,
    meta: { public: true }
  },
  {
    path: "/users",
    name: "UserList",
    component: UserList
  },
  {
    path: "/users/:userId",
    name: "UserDetail",
    component: UserDetail
  },

  {
    path: "/enlaces",
    name: "EnlaceList",
    component: EnlaceList,
    meta: { public: true } //para que la pagina sea publica(sin loggearse)
  },
  {
    path: "/enlaces/favoritos",
    name: "EnlacesFavoritos",
    component: EnlacesFavoritos
  },
  {
    path: "/enlaces/guardados",
    name: "EnlacesGuardados",
    component: EnlacesGuardados
  },
  {
    path: "/categoria/:categoriaId",
    name: "Categoria",
    component: Categoria,
    meta: { public: true } //para que la pagina sea publica(sin loggearse)
  },

  {
    path: "/enlaces/new",
    name: "EnlaceCreate",
    component: EnlaceForm
  },
  {
    path: "/enlaces/:enlaceId",
    name: "EnlaceDetail",
    component: EnlaceDetail,
    meta: { public: true }
  },
  {
    path: "/enlaces/:enlaceId/update",
    name: "EnlaceUpdate",
    component: EnlaceForm
  },
  {
    path: "*",
    name: "NotFound",
    component: NotFound
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  // Lo primero que hacemos antes de cargar ninguna ruta es comprobar si
  // el usuario está autenticado (revisando el token)
  auth.isAuthenticationChecked.finally(() => {
    // por defecto, el usuario debe estar autenticado para acceder a las rutas
    const requiresAuth = !to.meta.public;

    const requiredAuthority = to.meta.authority;
    const userIsLogged = user.logged;
    const loggedUserAuthority = user.authority;

    if (requiresAuth) {
      if (userIsLogged) {
        if (requiredAuthority && requiredAuthority != loggedUserAuthority) {
          // usuario logueado pero sin permisos
          alert(
            "Acceso no permitido para el usuario actual. Trate de autenticarse de nuevo"
          );
          auth.logout();
          next("/login");
        } else {
          // usuario logueado y con permisos adecuados
          next();
        }
      } else {
        // usuario no está logueado
        alert("Esta página requiere autenticación");
        next("/login");
      }
    } else {
      // página pública
      if (userIsLogged && to.meta.isLoginPage) {
        // si estamos logueados no hace falta volver a mostrar el login
        next({ name: "EnlaceList", replace: true });
      } else {
        next();
      }
    }
  });
});

export default router;
