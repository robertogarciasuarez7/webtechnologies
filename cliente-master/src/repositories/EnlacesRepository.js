import HTTP from "@/common/http.js";

const RESOURCE = "enlaces";
const RESOURCE1 = "enlaces/favorito";
const RESOURCE2 = "enlaces/favoritocheck";
const RESOURCE3 = "enlaces/unfavorito";
const RESOURCE4 = "enlaces/save";
const RESOURCE5 = "enlaces/savecheck";
const RESOURCE6 = "enlaces/unsave";
const RESOURCE7 = "enlaces/valoradocheck";

export default {
  async getNombre() {
    return (await HTTP.get(RESOURCE)).data;
  },

  async getEnlace(enlaceId) {
    const enlace = (await HTTP.get(RESOURCE + "/" + enlaceId)).data;
    return enlace;
  },
  async removeEnlace(enlaceId) {
    return await HTTP.delete(RESOURCE + "/" + enlaceId);
  },
  async saveEnlace(enlace) {
    if (enlace.id) {
      return (await HTTP.put(`${RESOURCE}/${enlace.id}`, enlace)).data;
    } else {
      return (await HTTP.post(`${RESOURCE}`, enlace)).data;
    }
  },
  async saveImage(enlaceId, file) {
    const formData = new FormData(); // COMO EN EL POSTMAN CUANDO HACEMOS EL POST Y LE DAMOS A FORM-DATA Y LE PASAMOS EL FICHERO
    formData.append("file", file);
    return (
      await HTTP.post(`${RESOURCE}/${enlaceId}/image`, formData, {
        headers: {
          //AQUI CAMBIAMOS EN QUE EN VEZ DE HACER UN RAW HACEMOS UN FOR-DATA
          "Content-Type": "multipart/form-data"
        }
      })
    ).data;
  },
  async comprobarvalorado(enlaceId) {
    if (enlaceId) {
      return (await HTTP.get(`${RESOURCE7}/${enlaceId}`)).data;
    } else {
      return alert("Enlace no existe");
    }
  },

  async comprobarfavorito(enlaceId) {
    if (enlaceId) {
      return (await HTTP.get(`${RESOURCE2}/${enlaceId}`)).data;
    } else {
      return alert("Enlace no existe");
    }
  },

  async marcarfavorita(enlaceId) {
    return (await HTTP.put(`${RESOURCE1}/${enlaceId}`)).data;
  },
  async desmarcarfavorita(enlaceId) {
    return (await HTTP.put(`${RESOURCE3}/${enlaceId}`)).data;
  },
  async comprobarguardado(enlaceId) {
    if (enlaceId) {
      return (await HTTP.get(`${RESOURCE5}/${enlaceId}`)).data;
    } else {
      return alert("Enlace no existe");
    }
  },
  async marcarguardado(enlaceId) {
    return (await HTTP.put(`${RESOURCE4}/${enlaceId}`)).data;
  },
  async desmarcarguardado(enlaceId) {
    return (await HTTP.put(`${RESOURCE6}/${enlaceId}`)).data;
  }
};
