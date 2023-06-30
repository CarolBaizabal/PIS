<template>
	<v-dialog :value="value" @input="updateValue" width="900">
		<v-card>
			<v-card-title>
				<h1>{{ esEdicion ? "Editar" : "Agregar" }} {{ title }}</h1>
			</v-card-title>
			<v-card-text>
				<v-form ref="formulario" v-model="formularioValido">
					<v-row v-if="esEdicion">
						<template v-for="campo in camposEditar">
							<v-col :cols="campo.cols ? campo.cols : '12'" :sm="campo.sm ? campo.sm : '6'" :md="campo.md ? campo.md : '4'" >
								<v-text-field
									v-if="campo.tipo === 'texto'" v-model="formulario[campo.nombre]" :clearable="
										campo.atributos.clearable !== undefined ? campo.atributos.clearable : true "
									v-bind="campo.atributos" @keyup.enter="enviarFormulario" @change="change(campo.nombre)" />
								<v-select
									v-else-if="campo.tipo === 'seleccion'"
									v-model="formulario[campo.nombre]"
									:clearable=" campo.atributos.clearable !== undefined
											? campo.atributos.clearable : true " v-bind="campo.atributos"
									@input=" (value) => updateValueForm(value, campo.nombre) "
									@keyup.enter="enviarFormulario"
									@change="change(campo.nombre)" />
								<v-switch v-else-if="campo.tipo === 'switch'" v-model="formulario[campo.nombre]" :clearable="true" v-bind="campo.atributos"
									@change="
										(value) => {
											updateValueForm( value, campo.nombre );
											change(campo.nombre); } "
									@keyup.enter="enviarFormulario" />
								<v-radio-group
									v-else-if="campo.tipo === 'radio'"
									v-model="formulario[campo.nombre]"
									:clearable="
										campo.atributos.clearable !== undefined ? campo.atributos.clearable : true " v-bind="campo.atributos"
									@change="
										(value) => {
											updateValueForm(
												value,
												campo.nombre
											);
											change(campo.nombre); } "
									@keyup.enter="enviarFormulario" >
									<v-row class="align-center justify-space-between my-2 px-3" >
										<v-radio v-for="opcion in campo.atributos .opciones"
											:key=" String(opcion.value).replace( ' ', '_' ) "
											:value="opcion.value"
											:label="opcion.nombre" ></v-radio>
									</v-row>
								</v-radio-group>
								<v-textarea
									v-else-if="campo.tipo === 'parrafo'"
									v-model="formulario[campo.nombre]"
									:clearable=" campo.atributos.clearable !== undefined ? campo.atributos.clearable : true " v-bind="campo.atributos"
									@input=" (value) => updateValueForm(value, campo.nombre) "
									@keyup.enter="enviarFormulario"
									@change="change(campo.nombre)" />
							</v-col>
						</template>
					</v-row>
					<v-row v-else>
						<template v-for="campo in camposAgregar">
							<v-col :cols="campo.cols ? campo.cols : '12'" :sm="campo.sm ? campo.sm : '6'" :md="campo.md ? campo.md : '4'" >
								<v-text-field v-if="campo.tipo === 'texto'"
									v-model="formulario[campo.nombre]" :clearable=" campo.atributos.clearable !== undefined
											? campo.atributos.clearable
											: true " v-bind="campo.atributos"
									@change="change(campo.nombre)" />
								<v-select v-else-if="campo.tipo === 'seleccion'" v-model="formulario[campo.nombre]" :clearable="
										campo.atributos.clearable !== undefined ? campo.atributos.clearable : true " v-bind="campo.atributos"
									@input=" (value) => updateValueForm(value, campo.nombre) "
									@change="change(campo.nombre)" />
								<v-switch v-else-if="campo.tipo === 'switch'" v-model="formulario[campo.nombre]"
									:clearable="true" v-bind="campo.atributos"
									@change=" (value) => { updateValueForm( value, campo.nombre ); change(campo.nombre); } " />
								<v-radio-group
									v-else-if="campo.tipo === 'radio'"
									v-model="formulario[campo.nombre]"
									:clearable=" campo.atributos.clearable !== undefined
											? campo.atributos.clearable : true " v-bind="campo.atributos"
									@change=" (value) => { updateValueForm( value, campo.nombre ); change(campo.nombre); } " >
									<v-row
										class="align-center justify-space-between my-2 px-3" >
										<v-radio
											v-for="opcion in campo.atributos .opciones"
											:key=" String(opcion.value).replace( ' ', '_' ) "
											:value="opcion.value"
											:label="opcion.nombre" ></v-radio>
									</v-row>
								</v-radio-group>
								<v-textarea v-else-if="campo.tipo === 'parrafo'"
									v-model="formulario[campo.nombre]" :clearable="
										campo.atributos.clearable !== undefined
											? campo.atributos.clearable
											: true " v-bind="campo.atributos"
									@input=" (value) => updateValueForm(value, campo.nombre) "
									@change="change(campo.nombre)" />
							</v-col>
						</template>
					</v-row>
				</v-form>
			</v-card-text>
			<v-card-actions class="justify-end">
				<v-btn rounded color="success" class="px-4" :disabled="enviando" @click="enviarFormulario" >
					<v-icon>mdi-check</v-icon>
					{{ esEdicion ? "Actualizar" : "Agregar" }}
				</v-btn>
				<v-btn rounded color="error" :disabled="enviando" class="px-4 justify-space-between align-center" @click="cancelar" >
					<v-icon>mdi-close</v-icon>Cancelar</v-btn >
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>
<script>
import { mostrarAlerta } from "@/utils/alerta";

export default {
	name: "Formulario",
	props: {
		esEdicion: Boolean,
		camposEditar: {
			type: Array,
			default: () => [],
		},
		camposAgregar: {
			type: Array,
			default: () => [],
		},
		title: String,
		value: Boolean,
		funcionAgregar: Function,
		funcionEditar: Function,
		item: null,
	},
	data() {
		return {
			formularioValido: false,
			formulario: {},
			enviando: false,
		};
	},
	methods: {
		async enviarFormulario() {
			this.$refs.formulario.validate();
			if (!this.formularioValido) return;
			this.enviando = true;
			let result = false;
			if (this.esEdicion) {
				result = await this.funcionEditar(this.formulario);
			} else {
				result = await this.funcionAgregar(this.formulario);
			}
			this.enviando = false;
			if (result !== true) {
				if (result === undefined) {
					mostrarAlerta();
				} else {
					mostrarAlerta(result, "error");
				}
				return;
			}

			this.$emit("input", false);
			this.enviando = false;
		},
		cancelar() {
			this.$refs.formulario.reset();
			this.$emit("input", false);
		},
		updateValue(value) {
			this.$emit("input", value);
		},
		updateValueForm(value, key) {
			this.formulario[key] = value;
			this.$forceUpdate();
		},
		change(nombre) {
			let value = this.formulario[nombre];
			// if (!value) return;

			if (this.esEdicion) {
				let dependientesEditar = this.camposEditar.filter(
					(campo) => campo.dependeDe === nombre
				);
				dependientesEditar.forEach((campo) => {
					let val = campo.update(value);
					this.updateValueForm(val, campo.nombre);
				});
			} else {
				let dependientes = this.camposAgregar.filter(
					(campo) => campo.dependeDe === nombre
				);
				dependientes.forEach((campo) => {
					let val = campo.update(value);
					this.updateValueForm(val, campo.nombre);
				});
			}
		},
	},
	watch: {
		item(value) {
			if (!value) return;
			let keys = Object.keys(value);
			keys.forEach((key) => {
				this.formulario[key] = value[key];
			});
		},
	},
	beforeMount() {
		let campos = this.esEdicion ? this.camposEditar : this.camposAgregar;
		for (let i = 0; i < campos.length; i++) {
			const campo = campos[i];
			this.formulario[campo.nombre] = null;
		}
	},
};
</script>
