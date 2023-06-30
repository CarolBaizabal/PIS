<template>
	<div class="alerta">
		<v-alert v-model="show" :type="tipo" dismissible close-label="Cerrar" transition="slide-x-reverse-transition">
			{{ mensaje }}
		</v-alert>
	</div>
</template>
<script>
import { bus } from "@/utils/alerta";
export default {
	name: "Alerta",
	data() {
		return {
			show: false,
			mensaje: "Mensaje de alerta",
			tipo: "success",
			interval: null,
		};
	},
	methods: {
		async mostrar() {
			clearTimeout(this.interval);
			this.show = true;
			this.interval = setTimeout(() => {
				this.show = false;
			}, 2000);
		},
	},
	mounted() {
		bus.$on("alerta", (message, type) => {
			this.mensaje = message;
			this.tipo = type;
			this.mostrar();
		});
	},
};
</script>
<style>
.alerta {
	position: fixed;
	top: 20px;
	right: 20px;
	width: 300px;
	z-index: 10000;
}
</style>
