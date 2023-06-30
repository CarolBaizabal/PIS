<template>
	<v-dialog :value="value" @input="$emit('input', false)" width="600">
		<v-card>
			<!--  -->
			<v-card-title>Confirmar acción</v-card-title>
			<v-card-text>
				<p>
					{{ mensaje }}
				</p>
				<v-text-field v-if="pedirMotivo" label="Motivo" v-model="motivo" ></v-text-field>
			</v-card-text>
			<v-card-actions class="justify-end">
				<v-btn rounded :color="colorAceptar" class="px-4" @click="aceptar" >
					<v-icon>mdi-check</v-icon>
					{{ textoAceptar ? textoAceptar : "Aceptar" }}
				</v-btn>
				<v-btn rounded color="default" class="px-4 justify-space-between align-center" @click="cancelar" >
					<v-icon>mdi-close</v-icon>Cancelar</v-btn >
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>
<script>
export default {
	name: "DialoigoConfirmar",
	props: {
		mensaje: String,
		textoAceptar: {
			type: String,
			default: "Aceptar",
		},
		colorAceptar: {
			type: String,
			default: "success",
		},
		value: Boolean,
		pedirMotivo: Boolean,
	},
	data() {
		return {
			motivo: "",
		};
	},
	methods: {
		aceptar() {
			this.$emit("aceptar", this.motivo);
		},
		cancelar() {
			this.$emit("input", false);
		},
	},
	watch: {
		value(valor) {
			if (valor === false) {
				this.motivo = "";
			}
		},
	},
};
</script>
