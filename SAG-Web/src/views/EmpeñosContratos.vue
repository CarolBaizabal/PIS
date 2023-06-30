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
				</v-data-table>
			</template>
		</MainLayout>
	</div>
</template>
<script>
import { empAPI } from "@/api/empAPI";
import { catalogoAPI } from "@/api/catalogoAPI";
import AccionDeTabla from "@/components/AccionDeTabla.vue";
import Busqueda from "@/components/Busqueda.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";


export default {
	name: "EmpeñosContratos",
	props: {},
	data() {
		return {
			titulo: "Resporte Empeños/Contratos",
			opcionesDeTabla: {
				headers: [
                    { text: "Empeño", value: "idEmp", sortable: false },
					{ text: "Cliente", value: "cliente" },
					{ text: "fecha Creacion", value: "fechaCreacion" },
					{ text: "Observaciones", value: "observaciones" },
                    { text: "Usuario", value: "usuario" },
                    { text: "Contrato", value: "idContrato" },
                    { text: "Fecha Actualización", value: "fechaActualizacion" },
                    { text: "Interes porcentaje", value: "interesPorcentaje" },
                    { text: "Almacenaje Porcentaje", value: "almacenajePorcentaje" },
                    { text: "Numero de periodos", value: "periodos" },
                    { text: "Días de periodos", value: "diasPeriodo" },
                    { text: "IVA", value: "iva" },
                    { text: "Tasa comercialización", value: "tasaComercializacion" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "seleccion",
					nombre: "estatus",
					atributos: {
						label: "Estatus",
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
				estatus: null,
				fechaCreacion: null
			},
		};
	},
	created() {},
	mounted() {
		this.cargarEmpeños();
		this.cargarEstatus();
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.cargarEmpeños();
		},
		async cargarEmpeños() {
			this.opcionesDeTabla.loading = true;
			try {
				this.opcionesDeTabla.items = await empAPI.get(
					this.form.estatus, this.form.fechaCreacion
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
			this.opcionesDeTabla.loading = false;
		},
		async cargarEstatus(){
			let items = await catalogoAPI.getEstatus();
			let campo = this.campos.find(
					(campo) => campo.nombre === "estatus"
				);
			campo.atributos.items = items;
		}
	},
	components: {
		MainLayout,
		Busqueda,
		MainConDetalle,
		AccionDeTabla,
	},
};
</script>

<style>
.error {
	background-color: red;
}
</style>
