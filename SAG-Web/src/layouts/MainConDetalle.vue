<template>
	<v-container>
		<v-row class="my-2" v-if="title">
			<v-col cols="12" class="d-flex justify-start">
				<h1>{{ title }}</h1>
			</v-col>
		</v-row>
		<v-row class="my-2">
			<v-col cols="12">
				<slot name="busqueda">
					<Busqueda :campos="campos" :value="value" @input="(val) => $emit('input', val)" @buscar="buscar" ></Busqueda></slot>
			</v-col>
		</v-row>

		<v-row class="my-2">
			<v-container>
				<v-container>
					<slot name="main" ><v-data-table v-bind="opcionesDeTabla" class="elevation-1" ></v-data-table ></slot>
				</v-container>
				<v-container v-show="showDetalle">
					<h2 class="text-left mx-4 my-2">{{ titleDetalle }}</h2>
					<slot name="detalle" ><v-data-table v-bind="opcionesDeTablaDetalle" class="elevation-1" ></v-data-table></slot>
				</v-container>
			</v-container>
		</v-row>
	</v-container>
</template>
<script>
import Busqueda from "@/components/Busqueda.vue";

export default {
	name: "MainLayout",
	props: {
		value: {
			type: Object,
			default: () => {
				return {};
			},
		},
		campos: {
			type: Array,
			default: () => [],
		},
		title: [String, undefined],
		titleDetalle: [String, undefined],
		showDetalle: false,
		opcionesDeTabla: Object,
		opcionesDeTablaDetalle: Object,
	},
	created() {},
	mounted() {},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.$emit("buscar");
		},
	},
	components: { Busqueda },
};
</script>
