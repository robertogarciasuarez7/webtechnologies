import HTTP from "./http";
import store from "./store";

const user = store.state.user;

export default {
  login,
  logout,
  getToken,
  isAdmin,
  isAuthenticationChecked: isAuthenticationChecked()
};

function login(credentials) {
  return HTTP.post("authenticate", credentials).then(response => {
    _saveToken(response.data.token);
    return _authenticate();
  });
}

function logout() {
  _removeToken();
  user.login = "";
  user.authority = "";
  user.logged = false;
  user.id = null;
}

function getToken() {
  return localStorage.getItem("token");
}

function isAdmin() {
  return user.authority == "ADMIN";
}

// usamos localStorage para guardar el token, de forma
// que sea persistente (se inhabilita con el tiempo o
// al hacer logout)
function _saveToken(token) {
  localStorage.setItem("token", token);
}

function _removeToken() {
  localStorage.removeItem("token");
}

// si tenemos el token guardado, esta petición se hará
// con el filtro definido en http-common y por tanto nos
// devolverá el usuario logueado
function _authenticate() {
  return HTTP.get("account").then(response => {
    user.login = response.data.login;
    user.authority = response.data.authority;
    user.logged = true;
    user.id = response.data.id;
    return user;
  });
}

// este método devuelve una promesa que se resuelve cuando
// se haya comprobado si el token, de existir, es válido o no
function isAuthenticationChecked() {
  return new Promise(res => {
    if (getToken()) {
      _authenticate()
        .catch(() => logout())
        .finally(() => res(true));
    } else {
      res(true);
    }
  });
}
