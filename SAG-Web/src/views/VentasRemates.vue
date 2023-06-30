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
import Busqueda from "@/components/Busqueda.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";


export default {
	name: "VentaRemates",
	props: {},
	data() {
		return {
			titulo: "Resporte Ventas remates",
			opcionesDeTabla: {
				headers: [
					{ text: "Venta Remate", value: "idVentaRemateDetalle", sortable: false },
					{ text: "Prenda", value: "prenda" },
					{ text: "Número de empeño", value: "idEmp" },
					{ text: "Precio", value: "precio" },
                    { text: "IVA", value: "iva" },
                    { text: "Prestamo", value: "prestamo" },
                    { text: "Descripcion prenda", value: "descripcionPrenda" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "texto",
					nombre: "folioVenta",
					atributos: {
						label: "Folio venta",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
                {
					tipo: "texto",
					nombre: "fechaCreacion",
					atributos: {
						label: "fecha Creacion",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
                {
					tipo: "texto",
					nombre: "idContrato",
					atributos: {
						label: "Contrato",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
                {
					tipo: "texto",
					nombre: "idEmp",
					atributos: {
						label: "Empeño",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
			],
			form: {
				fechaCreacion: null,
                folioVenta: null,
                idContrato: null,
                idEmp: null
			},
			mostrarConfirmacion: false,
			registroAEliminar: null,
			registroAEditar: null,
		};
	},
	created() {},
	mounted() {
		
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			
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
