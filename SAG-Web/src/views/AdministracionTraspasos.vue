<template>
	<div>
		<MainLayout v-model="form" :campos="campos" :title="titulo"
			@buscar="buscar"
			@nuevo-registro="
				mostrarFormulario = true;
				opcionesFormulario.esEdicion = false; " >
			<template #main>
				<v-data-table v-bind="opcionesDeTabla" class="elevation-1" loading-text="Cargando..." no-data-text="No hay información" >
					<template #item.acciones="{ item }">
						<div class="d-flex justify-space-around" style="gap: 10px" >
							<AccionDeTabla icon="pencil" color="blue" tooltip="Editar" :action="
									() => { editarRegistro(item); } " />
							<AccionDeTabla v-if="item.estatus === 'Activo'"
								icon="trash-can" color="red" tooltip="Borrar"
								:action="() => mostrarDialogoEliminar(item)" />
							<AccionDeTabla v-else icon="delete-restore" color="green"
								tooltip="Reactivar" :action="() => reactivarItem(item)" />
						</div>
					</template>
					<template #item.estatus="{ value }">
						<v-chip
							:color="value === 'Activo' ? 'green' : 'red'"
							style="color: white; width: 100%"
							class="d-flex align-center justify-center"
							>{{ value }}</v-chip >
					</template>
					<template #item.fechaCreacion="{ value }">
						<span>
							{{ formatDate(value) }}
						</span>
					</template>
					<template #item.fechaModificacion="{ value }">
						<span v-if="value">
							{{ formatDate(value) }}
						</span>
					</template>
				</v-data-table>
			</template>
		</MainLayout>
		<Formulario
			v-model="mostrarFormulario"
			v-bind="opcionesFormulario"
			:item="registroAEditar">
		</Formulario>

		<DialogoConfirmar
			:pedir-motivo="true"
			v-model="mostrarConfirmacion"
			mensaje="¿Seguro que desea borrar el traspaso?"
			texto-aceptar="Borrar"
			colorAceptar="error"
			@aceptar="eliminarRegistro"
		></DialogoConfirmar>
	</div>
</template>
<script>
import { lotesAPI } from "@/api/loteAPI";
import { hatosAPI } from "@/api/hatoAPI";
import { traspasosAPI } from "@/api/traspasoAPI";
import AccionDeTabla from "@/components/AccionDeTabla.vue";
import Busqueda from "@/components/Busqueda.vue";
import DialogoConfirmar from "@/components/DialogoConfirmar.vue";
import Formulario from "@/components/Formulario.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";
import { formRules } from "@/utils/rules";
import { formatDate } from "@/utils/general";

export default {
	name: "AdministracionTraspasos",
	props: {},
	data() {
		return {
			titulo: "Administración de traspasos",
			opcionesDeTabla: {
				headers: [
					{ text: "Acciones", value: "acciones", sortable: false },
					// { text: "#", value: "idLote" },
					{ text: "# Traspaso", value: "idTraspaso" },
					{ text: "Hato traspasado", value: "numArete" },
					{ text: "Lote original", value: "loteOriginal" },
					{ text: "Lote de destino", value: "loteDestino" },
					{ text: "Fecha de traspaso", value: "fechaCreacion" },
					{ text: "Motivo", value: "motivo" },
					{ text: "Descripción", value: "descripcion" },
					{
						text: "Fecha de modificación",
						value: "fechaModificacion",
					},
					{ text: "Estatus", value: "estatus" },
				],
				items: [],
				loading: false,
			},
			campos: [
				{
					tipo: "texto",
					nombre: "idTraspaso",
					atributos: {
						label: "Traspaso",
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
						tipo: "seleccion",
						nombre: "numArete",
						atributos: {
							label: "Hato",
							items: [],
							rules: [formRules.required],
							itemText: "numArete",
							itemValue: "numArete",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "loteOriginal",
						atributos: {
							label: "Lote original",
							type: "text",
							disabled: true,
							rules: [formRules.required],
						},
						dependeDe: "numArete",
						update: this.cargarLoteOriginal,
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "loteDestino",
						atributos: {
							label: "Lote de Destino",
							items: [],
							rules: [formRules.required],
							itemText: "nombreLote",
							itemValue: "idLote",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "motivo",
						atributos: {
							label: "Motivo",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "descripcion",
						atributos: {
							label: "Descripción",
							rows: 1,
							rules: [formRules.required],
						},
						cols: 12,
						md: 12,
						sm: 12,
					},
				],
				camposEditar: [
					{
						tipo: "seleccion",
						nombre: "numArete",
						atributos: {
							label: "Hato",
							items: [],
							rules: [formRules.required],
							itemText: "numArete",
							itemValue: "numArete",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "loteOriginal",
						atributos: {
							label: "Lote original",
							type: "text",
							disabled: true,
							rules: [formRules.required],
						},
						dependeDe: "numArete",
						update: this.cargarLoteOriginal,
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "loteDestino",
						atributos: {
							label: "Lote de Destino",
							items: [],
							rules: [formRules.required],
							itemText: "nombreLote",
							itemValue: "idLote",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "motivo",
						atributos: {
							label: "Motivo",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "descripcion",
						atributos: {
							label: "Descripción",
							rows: 1,
							rules: [formRules.required],
						},
						cols: 12,
						md: 12,
						sm: 12,
					},
				],
				funcionAgregar: async (datos) => {
					try {
						let { mensaje } = await traspasosAPI.create(datos);
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
						let { mensaje } = await traspasosAPI.update(
							this.registroAEditar.idTraspaso,
							datos
						);
						mostrarAlerta(mensaje, "success");
						this.buscar();
						return true;
					} catch (error) {
						if (error.data) {
							mostrarAlerta(data.mensaje, "error");
						} else {
							console.log(error);
						}
					}
				},
				title: "traspaso",
			},
			form: {
				idTraspaso: null,
			},
			mostrarConfirmacion: false,
			registroAEliminar: null,
			registroAEditar: null,
		};
	},
	created() {},
	mounted() {
		this.cargarTraspasos();
		this.cargarHatos();
		this.cargarLotes();
	},
	computed: {},
	watch: {},
	methods: {
		buscar() {
			this.cargarTraspasos();
		},
		async cargarTraspasos() {
			this.opcionesDeTabla.loading = true;
			try {
				this.opcionesDeTabla.items = await traspasosAPI.get(
					this.form.idTraspaso
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
				let { mensaje } = await hatosAPI.delete(
					this.registroAEliminar.idTraspaso,
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
				let { mensaje } = await hatosAPI.reactivate(item.idTraspaso);
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
		cargarLoteOriginal(numArete) {
			let campoAux = this.opcionesFormulario.camposAgregar.find(
				(campo) => campo.nombre === "numArete"
			);

			let hato = campoAux.atributos.items.find(
				(item) => item.numArete === numArete
			);
			return hato ? hato.idLote : undefined;
		},
		async cargarHatos() {
			try {
				let items = await hatosAPI.get();
				let campo = this.opcionesFormulario.camposAgregar.find(
					(campo) => campo.nombre === "numArete"
				);
				let campoEditar = this.opcionesFormulario.camposEditar.find(
					(campo) => campo.nombre === "numArete"
				);

				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		async cargarLotes() {
			try {
				let items = await lotesAPI.get();
				let campo = this.opcionesFormulario.camposAgregar.find(
					(campo) => campo.nombre === "loteDestino"
				);
				let campoEditar = this.opcionesFormulario.camposEditar.find(
					(campo) => campo.nombre === "loteDestino"
				);

				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		formatDate,
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
