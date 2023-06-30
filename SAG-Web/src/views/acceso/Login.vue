<template>
	<v-container>
		<v-form ref="formulario" v-model="formularioValido" onsubmit="acceder">
			<v-row class="align-center justify-center">
				<v-col cols="12" sm="8" md="8">
					<v-card class="elevation-12">
						<v-row>
							<v-col cols="12" md="8">
								<v-card-text class="mt-12">
									<h1 class="text-center display-2 deep-orange--text">Iniciar sesión</h1>
										<v-text-field v-model="formulario.usuario" label="Usuario" :rules="[reglas.required]" @keyup.enter="acceder" prepend-icon="mdi mdi-account" >
										</v-text-field>
										<v-text-field v-model="formulario.passwrod" type="password" label="Contraseña" :rules="[reglas.required]" @keyup.enter="acceder" prepend-icon="mdi mdi-lock" >
										</v-text-field>
										<div class="text-center mt-3">
											<v-btn rounded color="#FF9C3E" @click="acceder" :disabled="loading">Iniciar sesión</v-btn>
										</div>
								</v-card-text>
							</v-col>
							<v-col cols="12" md="4" class="white accent-3">
								<v-card-text class="mt-12">
									<img src="@/assets/logo.png" width="200"/>
								</v-card-text>
							</v-col>
						</v-row>
					</v-card>
				</v-col>
			</v-row>
		</v-form>
	</v-container>
</template>
<script>
import { login } from "@/authentication/access";
import { mostrarAlerta } from "@/utils/alerta";
import { formRules } from "@/utils/rules.js";
import Vue from "vue";

const vm = new Vue();

export default {
	name: "Login",
	data() {
		return {
			formulario: {
				usuario: null,
				passwrod: null,
			},
			formularioValido: false,
			reglas: formRules,
			loading: false,
		};
	},
	methods: {
		async acceder() {
			this.$refs.formulario.validate();
			if (!this.formularioValido) return;

			let successful = await login(
				this.formulario.usuario,
				this.formulario.passwrod
			);

			if (successful === false) {
				return;
			}
			this.$router.push({
				name: "Movimientos",
			});
		},
		registrarse() {
			this.$router.push({ path: "/registro" });
		},
	},
};
</script>
