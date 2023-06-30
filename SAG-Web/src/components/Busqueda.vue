<template>
	<v-card elevation="1" class="shrink" v-show="campos.length > 0">
		<v-row class="align-center" @click="toggleMostrarCampos">
			<v-card-title class="ml-8">Búsqueda</v-card-title>
			<div class="d-flex ml-auto">
				<v-btn rounded color="#FF5252" class="ml-auto" @click.stop="buscar" >Buscar</v-btn >
				<v-btn rounded color="#FFCCBC" class="ml-2 mr-8" @click.stop="limpiarCampos" >Limpiar</v-btn >
			</div>
		</v-row>
		<v-expand-transition>
			<v-row >
				<v-col cols="12">
					<v-card-text>
						<v-form ref="formBusqueda" v-model="formValido" @submit.prevent="buscar" >
							<v-row>
								<template v-for="campo in campos">
									<v-col :cols="campo.cols ? campo.cols : '12'" :sm="campo.sm ? campo.sm : '6'" :md="campo.md ? campo.md : '4'" >
										<v-text-field
											v-if="campo.tipo === 'texto'"
											:value="value[campo.nombre]"
											:clearable="
												campo.atributos.clearable !==
												undefined
													? campo.atributos.clearable
													: true "
											v-bind="campo.atributos "
											@input=" (value) =>
													updateValue( value, campo.nombre ) " />
										<v-select
											v-else-if="
												campo.tipo === 'seleccion' "
											:value="value[campo.nombre]"
											:clearable="
												campo.atributos.clearable !== undefined ? campo.atributos.clearable : true "
											v-bind="campo.atributos"
											@input=" (value) => updateValue( value, campo.nombre ) " />
										<v-switch
											v-else-if="campo.tipo === 'switch'"
											:value="value[campo.nombre]"
											:clearable="true"
											v-bind="campo.atributos"
											@change="
												(value) =>
													updateValue(
														value,
														campo.nombre
													)
											"
										/>
										<v-radio-group
											v-else-if="campo.tipo === 'radio'"
											:value="value[campo.nombre]"
											:clearable="
												campo.atributos.clearable !==
												undefined
													? campo.atributos.clearable
													: true " v-bind="campo.atributos"
											@change=" (value) => updateValue( value, campo.nombre ) " >
											<v-row
												class="align-center justify-space-between my-2 px-3" >
												<v-radio
													v-for="opcion in campo .atributos.opciones"
													:key=" String( opcion.value).replace(' ', '_')"
													:value="opcion.value"
													:label="opcion.nombre"
												></v-radio>
											</v-row>
										</v-radio-group>
										<v-textarea
											v-else-if="campo.tipo === 'parrafo'"
											:value="value[campo.nombre]"
											:clearable="
												campo.atributos.clearable !==
												undefined
													? campo.atributos.clearable
													: true"
											v-bind="campo.atributos"
											@input="
												(value) =>
													updateValue(
														value,
														campo.nombre)"/>
										<v-menu 
										v-else-if="campo.tipo === 'fecha'">
											<template v-slot:activator="{ on }">
        										<v-text-field 
												:value="value[campo.nombre]" 
												label="Fecha"
												:clearable="
												campo.atributos.clearable !==
												undefined
													? campo.atributos.clearable
													: true "
												prepend-icon="mdi-calendar-range" 
												v-on="on"
												v-bind="campo.atributos"
												></v-text-field>
    										</template>
											<v-date-picker 
											v-bind="campo.atributos"
											v-model="value[campo.nombre]"
											:clearable="
												campo.atributos.clearable !==
												undefined
													? campo.atributos.clearable
													: true "></v-date-picker>
										</v-menu>
									</v-col>
								</template>
							</v-row>
						</v-form>
					</v-card-text>
				</v-col>
			</v-row>
		</v-expand-transition>
	</v-card>
</template>
<script>
import format from 'date-fns/format';
export default {
	name: "Busqueda",
	props: {
		campos: {
			type: Array,
			default: () => [],
			ejemplo: [
				{
					tipo: "texto",
					nombre: "numeroDePersonal",
					atributos: {
						label: "Número de Personal",
						type: "number",
					},
				},
				{
					tipo: "seleccion",
					nombre: "persona",
					atributos: {
						label: "Persona",

						items: [
							{ id: 1, nombre: "Jazmín", apellido: "Gómez" },
							{ id: 2, nombre: "Carol", apellido: "Baizabal" },
						],
						itemText: "nombre",
						itemValue: "id",
					},
				},
				{
					tipo: "switch",
					nombre: "activo",
					atributos: {
						label: "¿Está activo?",
					},
				},
				{
					tipo: "radio",
					nombre: "Radio",
					atributos: {
						label: "¿Está activo?",
						opciones: [
							{ value: 1, nombre: "Todos" },
							{ value: 2, nombre: "Activos" },
							{ value: 3, nombre: "Inactivos" },
						],
					},
					cols: 12,
				},
			],
		},
		value: {
			type: Object,
			default: () => {},
		},
	},
	data() {
		return {
			formValido: false,
			mostrarCampos: false,
		};
	},
	computed: {
		showLimpiar() {
			return this.campos.some((campo) => {
				if (this.value[campo.nombre] !== null)
					return this.value[campo.nombre] !== null;
			});
		}
	},
	methods: {
		buscar() {
			this.$refs.formBusqueda.validate();
			if (!this.formValido) return;
			this.$emit("buscar");
		},
		updateValue(val, key) {
			let aux = JSON.parse(JSON.stringify(this.value));
			aux[key] = val;
			this.$emit("input", aux);
		},
		limpiarCampos() {
			
			for (const campo of this.campos) {
				this.value[campo.nombre] = null;
			}
			this.$refs.formBusqueda.reset();
		},
		toggleMostrarCampos() {
			if (this.showLimpiar) return;
			this.mostrarCampos = !this.mostrarCampos;
		},
	},
};
</script>
