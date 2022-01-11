import HTTP from "@/common/http";

const resource = "uservotes";

export default {
  async save(userVote) {
    if (userVote.id) {
      return (await HTTP.put(`${resource}/${userVote.id}`, userVote)).data;
    } else {
      return (await HTTP.post(`${resource}`, userVote)).data;
    }
  },

  async avgValoracion(id) {
    return (await HTTP.get(`${resource}/enlace/${id}`)).data;
  }
};
