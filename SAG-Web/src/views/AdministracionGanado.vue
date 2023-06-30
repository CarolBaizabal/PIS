<template>
	<div>
		<MainLayout v-model="form" :campos="campos" :title="titulo" @buscar="buscar" @nuevo-registro=" mostrarFormulario = true; opcionesFormulario.esEdicion = false;">
			<template #main>
				<v-data-table v-model="selected" v-bind="opcionesDeTabla" class="elevation-1" loading-text="Cargando" no-data-text="No hay información" :selectable="true" item-key="numArete" >
					<template #item.acciones="{ item }">
						<div class="d-flex justify-space-around" style="gap: 10px">
							<AccionDeTabla icon="mdi mdi-pencil-box" color="blue" tooltip="Editar"
								:action=" () => { editarRegistro(item); } " />
							<AccionDeTabla v-if="item.estatus === 'Activo'" icon="trash-can" color="red" tooltip="Borrar" :action="() => mostrarDialogoEliminar(item)" />
							<AccionDeTabla v-else icon="delete-restore" color="green" tooltip="Reactivar" :action="() => reactivarItem(item)" />
						</div>
					</template>
					<template #item.tipoGanado="{ value }">
						<span>
							{{ value === "tipo1" ? "Externo" : "Interno" }}
						</span>
					</template>
					<template #item.diaDeAlta="{ value }">
						<span>
							{{ formatDate(value) }}
						</span>
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
		<v-container v-if="selected.length > 0">
			<v-tabs v-model="tabSelected">
				<v-tab v-for="item in tabs" :key="item">
					{{ item }}
				</v-tab>
			</v-tabs>

			<v-row class="align-center justify-end my-2">
				<v-container class="d-flex align-center justify-end">
					<v-btn
						rounded
						color="primary"
						class="mx-4"
						@click="nuevoRegistroDetalle"
					>
						<v-icon>mdi-plus</v-icon>
						Nuevo registro
					</v-btn>
				</v-container>
			</v-row>
			<v-data-table
				v-bind="opcionesDeTablaDetalle"
				class="elevation-1"
				loading-text="Cargando..."
				no-data-text="No hay información"
			>
				<template #item.acciones="{ item }">
					<div class="d-flex justify-center" style="gap: 3rem">
						<AccionDeTabla
							icon="pencil"
							color="blue"
							tooltip="Editar"
							:action="
								() => {
									editarRegistroDetalle(item);
								}
							"
						/>
						<AccionDeTabla
							v-if="item.estatus === 'Activo'"
							icon="trash-can"
							color="red"
							tooltip="Borrar"
							:action="() => mostrarDialogoEliminarCria(item)"
						/>
						<AccionDeTabla
							v-else
							icon="delete-restore"
							color="green"
							tooltip="Reactivar"
							:action="
								() => {
									if (tabSelected === 0) {
										reactivarCria(item);
									} else {
										reactivarVisita(item);
									}
								}
							"
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
				<template #item.fechaNac="{ value }">
					<span>{{ formatDate(value) }}</span>
				</template>
				<template #item.fechaVisita="{ value }">
					<span>{{ formatDate(value) }}</span>
				</template>
			</v-data-table>
		</v-container>
		<!-- Formulario hato -->
		<Formulario
			v-model="mostrarFormulario"
			v-bind="opcionesFormulario"
			:item="registroAEditar"
		></Formulario>
		<Formulario
			v-if="tabSelected === 0 && selected.length > 0"
			v-model="mostrarFormularioDetalle"
			v-bind="opcionesFormularioCrias"
			:item="
				registroAEditar
					? registroAEditar
					: { numArete: selected[0].numArete }
			"
		></Formulario>
		<Formulario
			v-if="tabSelected === 1 && selected.length > 0"
			v-model="mostrarFormularioDetalle"
			v-bind="opcionesFormularioVisita"
			:item="
				registroAEditar
					? registroAEditar
					: { hato: selected[0].numArete }
			"
		></Formulario>
		<!-- Confirmar borrar hato -->
		<DialogoConfirmar
			v-model="mostrarConfirmacion"
			:mensaje="mensajeConfirmacion"
			texto-aceptar="Eliminar"
			colorAceptar="error"
			:pedir-motivo="true"
			@aceptar="eliminarRegistro"
		></DialogoConfirmar>
		<!-- Confirmar borrar cría -->
		<DialogoConfirmar
			v-model="mostrarConfirmacionCria"
			:mensaje="
				tabSelected === 0
					? '¿Seguro que desea borrar a la cría?'
					: '¿Seguro que desea borrar la visita?'
			"
			texto-aceptar="Eliminar"
			colorAceptar="error"
			:pedir-motivo="true"
			@aceptar="
				(motivo) => {
					if (tabSelected === 0) {
						eliminarCria(motivo);
					} else {
						eliminarVisita(motivo);
					}
				}
			"
		></DialogoConfirmar>
	</div>
</template>
<script>
import { criasAPI } from "@/api/criaAPI";
import { hatosAPI } from "@/api/hatoAPI";
import { lotesAPI } from "@/api/loteAPI";
import { razaAPI } from "@/api/razaAPI";
import { ranchosAPI } from "@/api/ranchoAPI";
import { visitasAPI } from "@/api/visitasAPI";
import AccionDeTabla from "@/components/AccionDeTabla.vue";
import Busqueda from "@/components/Busqueda.vue";
import DialogoConfirmar from "@/components/DialogoConfirmar.vue";
import Formulario from "@/components/Formulario.vue";
import MainConDetalle from "@/layouts/MainConDetalle.vue";
import MainLayout from "@/layouts/MainLayout.vue";
import { mostrarAlerta } from "@/utils/alerta";
import { formatDate, sleep } from "@/utils/general";
import { formRules } from "@/utils/rules";

export default {
	name: "AdministracionGanado",
	props: {},
	data() {
		return {
			titulo: "Administración de ganado",
			opcionesDeTabla: {
				headers: [
					{ text: "Acciones", value: "acciones", sortable: false },
					{ text: "Arete", value: "numArete" },
					{ text: "Sexo", value: "sexo" },
					{ text: "Descripción", value: "descripcion" },
					{ text: "Tipo de ganado", value: "tipoGanado" },
					{ text: "Estatus", value: "estatus" },
					{ text: "Fecha alta", value: "diaDeAlta" },
					// { text: "Fecha baja", value: "diaDeBaja" },
					// { text: "Motivo de baja", value: "motivoDeBaja" },
				],
				items: [],
				selectable: true,
				showSelect: true,
				singleSelect: true,
				loading: false,
			},
			campos: [
				{
					tipo: "texto",
					nombre: "numArete",
					atributos: {
						label: "Número de arete",
						type: "text",
					},
					cols: 12,
					sm: 12,
					md: 12,
				},
			],
			mostrarFormulario: false,
			mostrarFormularioDetalle: false,
			opcionesFormulario: {
				esEdicion: false,
				camposAgregar: [
					{
						tipo: "texto",
						nombre: "numArete",
						atributos: {
							label: "Número de arete",
							type: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "sexo",
						atributos: {
							label: "Sexo",
							items: [
								{
									value: "macho",
									text: "Macho",
								},
								{ value: "hembra", text: "Hembra" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "tipoGanado",
						atributos: {
							label: "Tipo de ganado",
							items: [
								{ value: "tipo1", text: "Externo" },
								{ value: "tipo2", text: "Interno" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idRaza",
						atributos: {
							label: "Raza",
							items: [],
							itemValue: "idRaza",
							itemText: "nombre",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idRancho",
						atributos: {
							label: "Rancho",
							items: [],
							itemValue: "idRancho",
							itemText: "nombre",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idLote",
						atributos: {
							label: "Lote",
							items: [],
							itemValue: "idLote",
							itemText: "nombreLote",
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
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				camposEditar: [
					{
						tipo: "texto",
						nombre: "numArete",
						atributos: {
							label: "Número de arete",
							disabled: true,
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "sexo",
						atributos: {
							label: "Sexo",
							items: [
								{
									value: "macho",
									text: "Macho",
								},
								{ value: "hembra", text: "Hembra" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "tipoGanado",
						atributos: {
							label: "Tipo de ganado",
							items: [
								{ value: "tipo1", text: "Externo" },
								{ value: "tipo2", text: "Interno" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idRaza",
						atributos: {
							label: "Raza",
							items: [],
							itemValue: "idRaza",
							itemText: "nombre",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idLote",
						atributos: {
							disabled: true,
							label: "Lote",
							items: [],
							itemValue: "idLote",
							itemText: "nombreLote",
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
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				funcionAgregar: async (datos) => {
					try {
						let respuesta = await hatosAPI.create(datos);
						mostrarAlerta(respuesta.mensaje, "success");
						this.buscar();
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				funcionEditar: async (datos) => {
					try {
						let { mensaje } = await hatosAPI.update(
							this.registroAEditar.numArete,
							datos
						);
						mostrarAlerta(mensaje, "success");
						this.buscar();
						this.registroAEditar = null;
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				title: "hato",
			},
			mostrarConfirmacion: false,
			mostrarConfirmacionCria: false,
			mensajeConfirmacion: "¿Seguro que desea eliminar el registro?",
			form: {
				numArete: null,
			},
			tabs: ["Crías", "Control médico"],
			tabSelected: 0,
			selected: [],
			opcionesDeTablaCrias: {
				headers: [
					{ text: "Acciones", value: "acciones" },
					{ text: "Sexo", value: "sexo" },
					{
						text: "Nacimiento",
						value: "fechaNac",
						sortable: false,
					},
					{ text: "Estatus", value: "estatus" },
					{
						text: "Observaciones",
						value: "observaciones",
					},
				],
				items: [],
				selectable: true,
				itemKey: "idCria",
				loading: false,
			},
			opcionesDeTablaControl: {
				headers: [
					{ text: "Acciones", value: "acciones" },
					{ text: "#", value: "idVisita" },
					{ text: "Visita", value: "nombreVisita" },
					{ text: "Fecha", value: "fechaVisita" },
					{ text: "Motivo", value: "motivo" },
					{ text: "Estatus", value: "estatus" },
					{ text: "Hato", value: "hato" },
					{
						text: "Observaciones",
						value: "observaciones",
					},
				],
				items: [],
				selectable: true,
				itemKey: "idCria",
				loading: false,
			},
			registroAEliminar: null,
			registroAEditar: null,
			opcionesFormularioCrias: {
				esEdicion: false,
				camposAgregar: [
					{
						tipo: "texto",
						nombre: "numArete",
						atributos: {
							label: "Número de arete de la madre",
							type: "text",
							rules: [formRules.required],
							disabled: true,
							value: null,
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "sexo",
						atributos: {
							label: "Sexo",
							items: [
								{
									value: "macho",
									text: "Macho",
								},
								{ value: "hembra", text: "Hembra" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "fechaNac",
						atributos: {
							label: "Fecha de nacimiento",
							rules: [formRules.required],
							type: "date",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idRaza",
						atributos: {
							label: "Raza",
							items: [],
							itemValue: "idRaza",
							itemText: "nombre",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "observaciones",
						atributos: {
							label: "Observaciones",
							rows: 1,
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				camposEditar: [
					{
						tipo: "texto",
						nombre: "numArete",
						atributos: {
							label: "Número de arete de la madre",
							type: "text",
							rules: [formRules.required],
							disabled: true,
							value: null,
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "sexo",
						atributos: {
							label: "Sexo",
							items: [
								{
									value: "macho",
									text: "Macho",
								},
								{ value: "hembra", text: "Hembra" },
							],
							itemValue: "value",
							itemText: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "fechaNac",
						atributos: {
							label: "Fecha de nacimiento",
							rules: [formRules.required],
							type: "date",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "idRaza",
						atributos: {
							label: "Raza",
							items: [],
							itemValue: "idRaza",
							itemText: "nombre",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "observaciones",
						atributos: {
							label: "Observaciones",
							rows: 1,
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				funcionAgregar: async (datos) => {
					try {
						let respuesta = await criasAPI.create(datos);
						mostrarAlerta(respuesta.mensaje, "success");
						this.cargarCrias(this.selected[0].numArete);
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				funcionEditar: async (datos) => {
					try {
						let respuesta = await criasAPI.update(
							this.registroAEditar.idCria,
							datos
						);
						mostrarAlerta(respuesta.mensaje, "success");
						this.registroAEditar = null;
						this.cargarCrias(this.selected[0].numArete);
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				title: "cría",
			},
			opcionesFormularioVisita: {
				esEdicion: false,
				camposAgregar: [
					{
						tipo: "texto",
						nombre: "nombreVisita",
						atributos: {
							label: "Nombre de la visita",
							type: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "fechaVisita",
						atributos: {
							label: "Fecha de visita",
							rules: [formRules.required],
							type: "date",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "hato",
						atributos: {
							label: "Hato",
							items: [],
							itemValue: "numArete",
							itemText: "numArete",
							disabled: true,
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "motivo",
						atributos: {
							label: "Motivo",
							type: "text",
							rules: [],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "observaciones",
						atributos: {
							label: "Observaciones",
							rows: 1,
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				camposEditar: [
					{
						tipo: "texto",
						nombre: "idVisita",
						atributos: {
							label: "Número de visita",
							type: "text",
							rules: [formRules.required],
							disabled: true,
							value: null,
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "nombreVisita",
						atributos: {
							label: "Nombre de la visita",
							type: "text",
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "fechaVisita",
						atributos: {
							label: "Fecha de visita",
							rules: [formRules.required],
							type: "date",
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "seleccion",
						nombre: "hato",
						atributos: {
							label: "Hato",
							items: [],
							itemValue: "numArete",
							itemText: "numArete",
							disabled: true,
							rules: [formRules.required],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "texto",
						nombre: "motivo",
						atributos: {
							label: "Motivo",
							type: "text",
							rules: [],
						},
						cols: 12,
						md: 6,
					},
					{
						tipo: "parrafo",
						nombre: "observaciones",
						atributos: {
							label: "Observaciones",
							rows: 1,
							rules: [],
						},
						cols: 12,
						sm: 12,
						md: 12,
					},
				],
				funcionAgregar: async (datos) => {
					try {
						let respuesta = await visitasAPI.create(datos);
						mostrarAlerta(respuesta.mensaje, "success");
						this.cargarVisitas(this.selected[0].numArete);
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				funcionEditar: async (datos) => {
					try {
						let respuesta = await visitasAPI.update(
							this.registroAEditar.idVisita,
							datos
						);
						mostrarAlerta(respuesta.mensaje, "success");
						this.registroAEditar = null;
						this.cargarVisitas(this.selected[0].numArete);
						return true;
					} catch ({ data }) {
						if (data) {
							return data.mensaje;
						}
						return false;
					}
				},
				title: "visita a veterinario",
			},
		};
	},
	created() {},
	mounted() {
		this.cargarHatos();
		this.cargarRazas();
		this.cargarLotes();
		this.cargarRanchos();
		this.cargarAllHatos();
	},
	computed: {
		opcionesDeTablaDetalle() {
			if (this.tabSelected === 0) {
				return this.opcionesDeTablaCrias;
			}
			return this.opcionesDeTablaControl;
		},
	},
	watch: {
		async selected(value) {
			if (value.length === 0) {
				return;
			}
			let numArete = value[0].numArete;
			if (this.tabSelected === 0) {
				this.cargarCrias(numArete);
			} else {
				this.cargarVisitas(numArete);
			}
			let campo = this.opcionesFormularioCrias.camposAgregar.find(
				(campo) => campo.nombre === "numArete"
			);
			if (campo !== undefined) {
				campo.atributos.value = numArete;
			}
		},
		tabSelected(value) {
			if (this.selected.length === 0) {
				return;
			}

			let numArete = this.selected[0].numArete;
			if (value === 0) {
				this.cargarCrias(numArete);
			} else {
				this.cargarVisitas(numArete);
			}
		},
		mostrarFormulario(value) {
			if (value === false) {
				this.registroAEditar = null;
			}
		},
	},
	methods: {
		formatDate,
		buscar() {
			this.cargarHatos();
		},
		async cargarHatos() {
			this.opcionesDeTabla.loading = true;
			try {
				this.opcionesDeTabla.items = await hatosAPI.get(
					this.form.numArete
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
			this.opcionesDeTabla.loading = false;
		},
		async cargarAllHatos() {
			try {
				let items = await hatosAPI.get();
				let campo = this.opcionesFormularioVisita.camposAgregar.find(
					(campo) => campo.nombre === "hato"
				);
				let campoEditar =
					this.opcionesFormularioVisita.camposEditar.find(
						(campo) => campo.nombre === "hato"
					);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		async cargarCrias(numArete) {
			this.opcionesDeTablaCrias.loading = true;

			try {
				this.opcionesDeTablaCrias.items = await criasAPI.get(numArete);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			} finally {
				this.opcionesDeTablaCrias.loading = false;
			}
		},
		async cargarVisitas(numArete) {
			this.opcionesDeTablaControl.loading = true;

			try {
				this.opcionesDeTablaControl.items = await visitasAPI.get(
					numArete
				);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			} finally {
				this.opcionesDeTablaControl.loading = false;
			}
		},
		async cargarRazas() {
			//
			let items = [];
			try {
				items = await razaAPI.get();
				let campo = this.opcionesFormulario.camposAgregar.find(
					(campo) => campo.nombre === "idRaza"
				);
				let campoEditar = this.opcionesFormulario.camposEditar.find(
					(campo) => campo.nombre === "idRaza"
				);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;

				campo = this.opcionesFormularioCrias.camposAgregar.find(
					(campo) => campo.nombre === "idRaza"
				);
				campoEditar = this.opcionesFormularioCrias.camposEditar.find(
					(campo) => campo.nombre === "idRaza"
				);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;

				campo = this.opcionesFormularioVisita.camposAgregar.find(
					(campo) => campo.nombre === "idRaza"
				);
				campoEditar = this.opcionesFormularioVisita.camposEditar.find(
					(campo) => campo.nombre === "idRaza"
				);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
			return items;
		},
		async cargarLotes() {
			//
			let items = [];
			try {
				items = await lotesAPI.get();
				let campo = this.opcionesFormulario.camposAgregar.find(
					(campo) => campo.nombre === "idLote"
				);
				let campoEditar = this.opcionesFormulario.camposEditar.find(
					(campo) => campo.nombre === "idLote"
				);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
			return items;
		},
		async cargarRanchos() {
			//
			let items = [];
			try {
				items = await ranchosAPI.get();
				let campo = this.opcionesFormulario.camposAgregar.find(
					(campo) => campo.nombre === "idRancho"
				);
				let campoEditar = this.opcionesFormulario.camposEditar.find(
					(campo) => campo.nombre === "idRancho"
				);
				campo.atributos.items = items;
				campoEditar.atributos.items = items;
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
			return items;
		},
		nuevoRegistroDetalle() {
			this.mostrarFormularioDetalle = true;
			this.opcionesFormularioCrias.esEdicion = false;
			this.opcionesFormularioVisita.esEdicion = false;
		},
		async eliminarRegistro(motivo) {
			try {
				let respuesta = await hatosAPI.delete(
					this.registroAEliminar.numArete,
					motivo
				);
				mostrarAlerta(respuesta.mensaje, "success");
				this.mostrarConfirmacion = false;
				this.buscar();
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
		},
		async eliminarCria(motivo) {
			try {
				let respuesta = await criasAPI.delete(
					this.registroAEliminar.idCria,
					motivo
				);
				mostrarAlerta(respuesta.mensaje, "success");
				this.mostrarConfirmacionCria = false;
				this.cargarCrias(this.selected[0].numArete);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
		},
		async eliminarVisita(motivo) {
			try {
				let respuesta = await visitasAPI.delete(
					this.registroAEliminar.idVisita,
					motivo
				);
				mostrarAlerta(respuesta.mensaje, "success");
				this.mostrarConfirmacionCria = false;
				this.cargarVisitas(this.selected[0].numArete);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data, "error");
				}
			}
		},
		mostrarDialogoEliminar(item) {
			this.registroAEliminar = item;
			this.mostrarConfirmacion = true;
		},
		mostrarDialogoEliminarCria(item) {
			this.registroAEliminar = item;
			this.mostrarConfirmacionCria = true;
		},
		editarRegistro(item) {
			this.opcionesFormulario.esEdicion = true;
			this.mostrarFormulario = true;
			this.registroAEditar = item;
		},
		editarRegistroDetalle(item) {
			this.opcionesFormularioCrias.esEdicion = true;

			this.opcionesFormularioVisita.esEdicion = true;

			this.mostrarFormularioDetalle = true;
			this.registroAEditar = item;
		},
		async reactivarItem(item) {
			try {
				let { mensaje } = await hatosAPI.reactivate(item.numArete);
				mostrarAlerta(mensaje, "success");
				this.buscar();
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		async reactivarCria(item) {
			try {
				let { mensaje } = await criasAPI.reactivate(item.idCria);
				mostrarAlerta(mensaje, "success");
				this.cargarCrias(this.selected[0].numArete);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
		async reactivarVisita(item) {
			try {
				let { mensaje } = await visitasAPI.reactivate(item.idVisita);
				mostrarAlerta(mensaje, "success");
				this.cargarVisitas(this.selected[0].numArete);
			} catch ({ data }) {
				if (data) {
					mostrarAlerta(data.mensaje, "error");
				}
			}
		},
	},
	components: {
		MainLayout,
		Busqueda,
		MainConDetalle,
		Formulario,
		AccionDeTabla,
		DialogoConfirmar,
	},
};
</script>

<style>
.error {
	background-color: red;
}
</style>
