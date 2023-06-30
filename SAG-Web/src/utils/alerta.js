import Vue from "vue";

export const bus = new Vue();

export const mostrarAlerta = (message, tipo) => {
	if (message == undefined && tipo === undefined) {
		bus.$emit("alerta", "Ocurrió un error inesperado", "error");
		return;
	}
	bus.$emit("alerta", message, tipo);
};
