import { api } from ".";

const get = async (id) => {
	let url = id ? `rancho/getRanchoById/${id}` : "rancho/getAllRancho";

	let { data } = await api.get(url);
	return data;
};

export const ranchosAPI = {
	get,
};
