<template>
	<div>
		<MainLayout
			v-model="form"
			:campos="campos"
			:title="titulo"
			@buscar="buscar"
			@nuevo-registro="
				mostrarFormulario = true;
				opcionesFormulario.esEdicion = false;
			"
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
							<AccionDeTabla
								icon="pencil"
								color="blue"
								tooltip="Editar"
								:action="
									() => {
										editarRegistro(item);
									}
								"
							/>
							<AccionDeTabla
								v-if="item.estatus === 'Activo'"
								icon="trash-can"
								color="red"
								tooltip="Borrar"
								:action="() => mostrarDialogoEliminar(item)"
							/>
							<AccionDeTabla
								v-else
								icon="delete-restore"
								color="green"
								tooltip="Reactivar"
								:action="() => reactivarItem(item)"
							/>
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
		<Formulario
			v-model="mostrarFormulario"
			v-bind="opcionesFormulario"
			:item="registroAEditar"
		></Formulario>

		<DialogoConfirmar
			:pedir-motivo="true"
			v-model="mostrarConfirmacion"
			mensaje="¿Seguro que desea eliminar el lote?"
			texto-aceptar="Eliminar"
			colorAceptar="error"
			@aceptar="eliminarRegistro"
		></DialogoConfirmar>
	</div>
</template>
<script>
import { lotesAPI } from "@/api/loteAPI";
import AccionDeTabla from "@/components/AccionDeTabla.vue";
import Busqueda from "@/components/Busqueda.vue";
import DialogoConfirmar from "@/components/DialogoConfirmar.vue";
import Formulario from "@/components/Formulario.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";
import { formRules } from "@/utils/rules";

export default {
	name: "AdministracionLote",
	props: {},
	data() {
		return {
			titulo: "Administración de lotes",
			opcionesDeTabla: {
				headers: [
					{ text: "Acciones", value: "acciones", sortable: false },
					// { text: "#", value: "idLote" },
					{ text: "Nombre", value: "nombreLote" },
					{ text: "Número de lote", value: "numLote" },
					{ text: "Estatus", value: "estatus" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "texto",
					nombre: "idLote",
					atributos: {
						label: "Lote",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
			],
			mostrarFormulario: false,
			opcionesFormulario: {
				camposAgregar: [
					{
						tipo: "texto",
						nombre: "numLote",
						atributos: {
							label: "Número de lote",
							type: "text",
							rules: [formRules.required, formRules.number],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "nombreLote",
						atributos: {
							label: "Nombre",
							type: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
				],
				camposEditar: [
					{
						tipo: "texto",
						nombre: "numLote",
						atributos: {
							label: "Número de lote",
							type: "text",
							rules: [formRules.required, formRules.number],
							disabled: true,
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "nombreLote",
						atributos: {
							label: "Nombre",
							type: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
				],
				funcionAgregar: async (datos) => {
					try {
						let { mensaje } = await lotesAPI.create(datos);
						mostrarAlerta(mensaje, "success");
						this.buscar();
						return true;
					} catch ({ data }) {
						if (data) {
							mostrarAlerta(data.mensaje, "error");
						}
					}
				},
				funcionEditar: async (datos) => {
					try {
						let { mensaje } = await lotesAPI.update(
							this.registroAEditar.idLote,
							datos
						);
						mostrarAlerta(mensaje, "success");
						this.buscar();
						return true;
					} catch ({ data }) {
						if (data) {
							mostrarAlerta(data.mensaje, "error");
						}
					}
				},
				title: "lote",
			},
			form: {
				idLote: null,
			},
			mostrarConfirmacion: false,
			registroAEliminar: null,
			registroAEditar: null,
		};
	},
	created() {},
	mounted() {
		this.cargarLotes();
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.cargarLotes();
		},
		async cargarLotes() {
			this.opcionesDeTabla.loading = true;
			try {
				this.opcionesDeTabla.items = await lotesAPI.get(
					this.form.idLote
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
			this.opcionesDeTabla.loading = false;
		},
		mostrarDialogoEliminar(item) {
			this.registroAEliminar = item;
			this.mostrarConfirmacion = true;
		},
		async eliminarRegistro(motivo) {
			try {
				let { mensaje } = await lotesAPI.delete(
					this.registroAEliminar.idLote,
					motivo
				);
				mostrarAlerta(mensaje, "success");
				this.mostrarConfirmacion = false;
				this.buscar();
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		async reactivarItem(item) {
			try {
				let { mensaje } = await lotesAPI.reactivate(item.idLote);
				mostrarAlerta(mensaje, "success");
				this.buscar();
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		editarRegistro(item) {
			this.opcionesFormulario.esEdicion = true;
			this.mostrarFormulario = true;
			this.registroAEditar = item;
		},
	},
	components: {
		MainLayout,
		Busqueda,
		MainConDetalle,
		AccionDeTabla,
		DialogoConfirmar,
		Formulario,
	},
};
</script>

<style>
.error {
	background-color: red;
}
</style>
