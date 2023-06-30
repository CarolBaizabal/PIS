import { api } from "@/api";
import router from "@/router";
import { mostrarAlerta } from "@/utils/alerta";
import Vue from "vue";

const vm = new Vue();

export const validateSession = () => {
	let usuario = vm.$session.get("usuario");
	return usuario !== undefined ? usuario : false;
};

export const cerrarSesion = () => {
	vm.$session.remove("usuario");
	router.push({
		name: "login",
	});
	return;
};

export const login = async (usuario, password) => {
	try {
		let body = new URLSearchParams({ usuario, password });
		let { data } = await api.post("/sesion/login", body);
		vm.$session.set("usuario", data);
		mostrarAlerta("Inicio de sesión correcto", "success");
		return true;
	} catch ({ data, status }) {
		if (data) {
			mostrarAlerta(data.mensaje, "error");
		}
		return false;
	}
};
