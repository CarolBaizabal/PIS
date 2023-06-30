import { api } from ".";

const get = async (idEmp) => {
	let url = idEmp
		? `cria/getCriasByNumArete/${numArete}`
		: "emp/reporte";

	let { data } = await api.get(url);
	return data;
};

const getEstatus = async () => {
	let url = `catalogo/getAllEstatusContrato`;
	let { data } = await api.get(url);
	return data;
};

const getMotivos = async () => {
	let url = `/categoria/datosCategoriaEgreso`;
	let { data } = await api.get(url);
	return data;
};

const create = async ({ numArete, sexo, fechaNac, idRaza, observaciones }) => {
	let url = "cria/registrarCria";
	let body = new URLSearchParams({
		numArete,
		sexo,
		fechaNac,
		idRaza,
		observaciones,
	});
	let { data } = await api.post(url, body);
	return data;
};

const update = async (
	idCria,
	{ numArete, sexo, fechaNac, idRaza, observaciones }
) => {
	let fecha = new Date(fechaNac);
	let url = `cria/actualizarCria/${idCria}`;

	let body = new URLSearchParams({
		numArete,
		sexo,
		fechaNac: `${fecha.getFullYear()}-${
			fecha.getMonth() + 1
		}-${fecha.getDate()}`,
		idRaza,
		observaciones,
	});
	let { data } = await api.put(url, body);
	return data;
};

const eliminar = async (idCria, motivoDeBaja) => {
	let url = `cria/eliminarCria/${idCria}`;

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

const reactivate = async (idCria) => {
	let url = `cria/actualizarEstatus/${idCria}`;

	let { data } = await api.put(url);

	return data;
};

export const catalogoAPI = {
    getEstatus,
	getMotivos,
	get,
	update,
	create,
	delete: eliminar,
	reactivate,
};