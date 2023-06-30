import { api } from ".";

const get = async (numArete) => {
	let url = `veterinario/getAllByHato/${numArete}`;
	let { data } = await api.get(url);
	return data;
};

const create = async ({
	nombreVisita,
	fechaisita,
	motivo,
	observaciones,
	numArete,
}) => {
	let url = `veterinario/registrarVeterinario`;

	let body = new URLSearchParams({
		nombreVisita,
		fechaisita,
		motivo,
		observaciones,
		numArete,
	});

	let { data } = await api.post(url, body);
	return data;
};

const eliminar = async (idVisita, motivoDeBaja) => {
	let url = `veterinario/eliminarVeterinario/${idVisita}`;
	let body = new URLSearchParams({
		motivoDeBaja,
	});

	let { data } = await api.delete(url, {
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
		},
		data: body,
	});

	return data;
};

const reactivate = async (idVisita) => {
	let url = `veterinario/actualizarEstatus/${idVisita}`;
	let { data } = await api.put(url);
	return data;
};

export const visitasAPI = {
	get,
	create,
	delete: eliminar,
	reactivate,
};
