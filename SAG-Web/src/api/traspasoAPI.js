import { api } from ".";

const get = async (idTraspaso) => {
	let url = idTraspaso
		? `traspaso/getTraspasoById/${idTraspaso}`
		: "traspaso/getAllTraspaso";

	let { data } = await api.get(url);
	return data;
};

const create = async ({
	numArete,
	descripcion,
	motivo,
	loteOriginal,
	loteDestino,
}) => {
	let url = "traspaso/registrarTraspaso";
	let body = new URLSearchParams({
		numArete,
		descripcion,
		motivo,
		loteOriginal,
		loteDestino,
	});
	let { data } = await api.post(url, body);
	return data;
};

const update = async (
	idTraspaso,
	{ numArete, descripcion, motivo, loteOriginal, loteDestino }
) => {
	let url = `traspaso/actualizarTraspaso/${idTraspaso}`;

	let body = new URLSearchParams({
		numArete,
		descripcion,
		motivo,
		loteOriginal,
		loteDestino,
	});
	let { data } = await api.put(url, body);
	return data;
};

const eliminar = async (idTraspaso, motivoDeCancelacion) => {
	let url = `traspaso/eliminarTraspaso/${idTraspaso}`;

	let body = new URLSearchParams({
		motivoDeCancelacion,
	});

	let { data } = await api.delete(url, {
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
		},
		data: body,
	});

	return data;
};

const reactivate = async (idTraspaso) => {
	let url = `traspaso/actualizarEstatus/${idTraspaso}`;

	let { data } = await api.put(url);

	return data;
};

export const traspasosAPI = {
	get,
	update,
	create,
	delete: eliminar,
	reactivate,
};
