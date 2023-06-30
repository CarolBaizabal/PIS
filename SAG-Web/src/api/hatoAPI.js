import { api } from ".";

const get = async (id) => {
	let url = id ? `hato/getHatoById/${id}` : "hato/getAllHato";

	let { data } = await api.get(url);
	return data;
};

const create = async ({
	numArete,
	sexo,
	descripcion,
	tipoGanado,
	idRaza,
	idLote,
	idRancho,
}) => {
	let url = "hato/registrarHato";

	let body = new URLSearchParams({
		numArete,
		sexo,
		descripcion,
		tipoGanado,
		idRaza,
		idLote,
		idRancho,
	});
	let { data } = await api.post(url, body);
	return data;
};

const update = async (
	numArete,
	{ sexo, descripcion, tipoGanado, idRaza, idLote }
) => {
	let url = `hato/actualizarHato/${numArete}`;

	let body = new URLSearchParams({
		sexo,
		descripcion,
		tipoGanado,
		idRaza,
		idLote,
	});

	let { data } = await api.put(url, body);
	return data;
};

const eliminar = async (numArete, motivoDeBaja) => {
	let url = `hato/eliminarHato/${numArete}`;

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

const reactivate = async (numArete) => {
	let url = `hato/actualizarEstatus/${numArete}`;

	let { data } = await api.put(url);

	return data;
};

export const hatosAPI = {
	get,
	create,
	delete: eliminar,
	update,
	reactivate,
};
