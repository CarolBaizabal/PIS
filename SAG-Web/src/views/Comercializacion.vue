<template>
	<div>
		<MainLayout
			v-model="form"
			:campos="campos"
			:title="titulo"
			@buscar="buscar"
		>
			<template #main>
				<v-data-table
					v-bind="opcionesDeTabla"
					class="elevation-1"
					loading-text="Cargando..."
					no-data-text="No hay información"
				>
					<template #item.acciones="{ item }">
						<div class="d-flex justify-space-around">
						</div>
					</template>
					<template #item.estatus="{ value }">
						<v-chip
							:color="value === 'Activo' ? 'green' : 'red'"
							style="color: white; width: 100%"
							class="d-flex align-center justify-center"
							>{{ value }}</v-chip
						>
					</template>
				</v-data-table>
			</template>
		</MainLayout>
	</div>
</template>
<script>
import { comercializacionAPI } from "@/api/comercializacionAPI";
import Busqueda from "@/components/Busqueda.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";


export default {
	name: "Comercializacion",
	props: {},
	data() {
		return {
			titulo: "Resporte Comercialización",
			opcionesDeTabla: {
				headers: [
                    { text: "Comercialización", value: "idComercializacion", sortable: false },
					{ text: "Contrato", value: "idContrato" },
					{ text: "Empeño", value: "idEmp" },
					{ text: "prenda", value: "prenda" },
                    { text: "Precio Comercializacion", value: "precioComercializacion" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "fecha",
					nombre: "fechaComercializacion",
					atributos: {
						label: "Fecha Cormecialzacion",
					},
					cols: 12,
					sm: 12,
					md: 12,
				}
			],
			form: {
				fechaComercializacion: null
			},
		};
	},
	created() {},
	mounted() {
		this.cargarComercializacion();
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.cargarComercializacion();
		},
		async cargarComercializacion() {
			try {
				this.opcionesDeTabla.items = await comercializacionAPI.get(
					this.form.fechaComercializacion
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
		},
	},
	components: {
		MainLayout,
		Busqueda,
		MainConDetalle,
	},
};
</script>

<style>
.error {
	background-color: red;
}
</style>
