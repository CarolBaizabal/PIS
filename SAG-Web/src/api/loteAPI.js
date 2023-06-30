import { api } from ".";

const get = async (id) => {
	let url = id ? `lote/getLoteById/${id}` : "lote/getAllLote";

	let { data } = await api.get(url);
	return data;
};

const create = async ({ nombreLote, numLote }) => {
	let url = "lote/registrarLote";

	let body = new URLSearchParams({ nombreLote, numLote });
	const { data } = await api.post(url, body);
	return data;
};

const update = async (idLote, { nombreLote, numLote }) => {
	let url = `lote/actualizarLote/${idLote}`;

	let body = new URLSearchParams({ nombreLote, numLote });
	const { data } = await api.put(url, body);
	return data;
};

const eliminar = async (idLote, motivoDeBaja) => {
	let url = `lote/eliminarLote/${idLote}`;

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

const reactivate = async (idLote) => {
	let url = `lote/actualizarEstatus/${idLote}`;

	let { data } = await api.put(url);

	return data;
};

export const lotesAPI = {
	get,
	delete: eliminar,
	create,
	update,
	reactivate,
};
