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
import { movimientosAPI } from "@/api/movimientosAPI";
import { catalogoAPI } from "@/api/catalogoAPI";
import Busqueda from "@/components/Busqueda.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";


export default {
	name: "EgresosIngresos",
	props: {},
	data() {
		return {
			titulo: "Resporte Egresos-Ingresos",
			opcionesDeTabla: {
				headers: [
                    { text: "Cantidad", value: "cantidad", sortable: false },
					{ text: "Motivo", value: "motivo" },
					{ text: "fecha Creacion", value: "fechaCreacion" },
					{ text: "Observaciones", value: "observaciones" },
                    { text: "Usuario", value: "usuario" },
                    { text: "Fecha Actualización", value: "fechaModificacion" },
                    { text: "Usuario actualizacion", value: "usuarioA" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "seleccion",
					nombre: "motivo",
					atributos: {
						label: "Motivo",
						type: "text",
                        items: [],
                        itemText: "nombre",
                        itemValue: "nombre",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
				{
					tipo: "fecha",
					nombre: "fechaCreacion",
					atributos: {
						label: "Fecha Creacion",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
			],
			form: {
				fechaCreacion: null,
				motivo: null
			},
		};
	},
	created() {},
	mounted() {
		this.cargarEgresosIngresos();
		this.cargarMotivos();
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.cargarEgresosIngresos();
		},
		async cargarEgresosIngresos() {
			try {
				this.opcionesDeTabla.items = await movimientosAPI.get(
					this.form.fechaCreacion, this.form.motivo
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
		},
		async cargarMotivos(){
			let items = await catalogoAPI.getMotivos();
			let campo = this.campos.find(
					(campo) => campo.nombre === "motivo"
				);
			campo.atributos.items = items;
		}
	},
	components: {
		MainLayout,
		Busqueda,
		MainConDetalle
	},
};
</script>

<style>
.error {
	background-color: red;
}
</style>
