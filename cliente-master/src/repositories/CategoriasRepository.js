import HTTP from "@/common/http.js";

const RESOURCE = "categorias";

export default {
  async getAll() {
    const response = await HTTP.get(RESOURCE);
    return response.data;
  },

  async save(categoria) {
    (await HTTP.post(`${RESOURCE}`, categoria)).data;
  },

  async findByCategoria(idCat) {
    return (await HTTP.get(RESOURCE + "/" + idCat + "/enlaces")).data;
  },
  async findById(categoriaId) {
    return (await HTTP.get(RESOURCE + "/" + categoriaId)).data;
  }
};
