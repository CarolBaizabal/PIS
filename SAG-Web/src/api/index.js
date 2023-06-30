import { mostrarAlerta } from "@/utils/alerta";
import axios from "axios";
import Vue from "vue";

const vueInstance = new Vue();
let host = "localhost:8084";

export const api = axios.create({
	baseURL: `http://${host}/SistemEmpWS/ws/`,
});

const globalErrorResponseHandler = (error) => {
	if (!error.response) {
		console.error(data);
		mostrarAlerta();
		return Promise.reject({ data: undefined, status: undefined });
	}
	let { data, status } = error.response;
	if (status === 500) {
		console.error(data);
	}
	return Promise.reject({ data, status });
};

const globalRequestHandler = (req) => {
	if (req.method === "get") {
		return Promise.resolve(req);
	}

	let usuario = vueInstance.$session.get("usuario");
	if (usuario !== undefined && req.data instanceof URLSearchParams) {
		req.data.append("idUsuario", usuario.idUsuario);
	}
	return Promise.resolve(req);
};

api.interceptors.response.use(undefined, globalErrorResponseHandler);
api.interceptors.request.use(globalRequestHandler);
