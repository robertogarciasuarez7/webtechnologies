import HTTP from "@/common/http";

const resource = "users";
const resource1 = "users/favoritos";
const resource2 = "users/guardados";

export default {
  async getAll() {
    const response = await HTTP.get(resource);
    return response.data;
  },
  async getFavoritos(userId) {
    return (await HTTP.get(`${resource1}/${userId}`)).data;
  },
  async save(user) {
    (await HTTP.post("register", user)).data;
  },
  async getEnlacesFav() {
    return (await HTTP.get(resource1)).data;
  },
  async getEnlacesSave() {
    return (await HTTP.get(resource2)).data;
  },
  async getById(userId) {
    return (await HTTP.get(resource + "/" + userId)).data;
  },
  async delete(id) {
    (await HTTP.delete(`${resource}/${id}`)).data;
  },
  async update(id) {
    (await HTTP.put(`${resource}/${id}`)).data;
  }
};
