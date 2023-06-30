import { api } from ".";

const get = async () => {
	let { data } = await api.get("raza/getAllRazaActivo");
	return data;
};

export const razaAPI = {
	get,
};
