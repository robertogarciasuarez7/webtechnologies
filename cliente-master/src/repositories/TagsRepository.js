import HTTP from "@/common/http.js";

const RESOURCE = "tags";
const RESOURCE2 = "tags/enlaces";

export default {
  async getAll() {
    const response = await HTTP.get(RESOURCE);
    return response.data;
  },
  async findByTag(keywords) {
    return (await HTTP.get(`${RESOURCE2}/${keywords}`)).data;
  }
};
