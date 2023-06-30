<template>
	<div>
		<v-app-bar app color="#FFCCBC">
			<v-app-bar-nav-icon @click="drawer = true" color="deep-orange"> </v-app-bar-nav-icon>
			<v-toolbar-title @click="redirect($event, '/')" style="cursor: pointer" >Prenda facil</v-toolbar-title>
				<v-list-item-icon>
					<v-img src="@/assets/logo.png"></v-img>
				</v-list-item-icon>
			<v-row class="justify-end align-center">
				<p class="mb-0 mr-4">{{ $session.get("usuario").nombre }}</p>
				<div class="my-2">
					<v-btn color="#D32F2F" fab small depressed @click="cerrarSesion">
						<v-icon>mdi mdi-login</v-icon>
					</v-btn>
				</div>
			</v-row>
		</v-app-bar>
		<v-navigation-drawer v-model="drawer" absolute temporary>
			<v-list class="pa-0">
				<v-list-litle avatar>
					<v-list-litle-avatar>
						<v-icon > mdi mdi-cow </v-icon>
					</v-list-litle-avatar>
				</v-list-litle>

				<v-list-item-group v-model="group" active-class="text--accent-4" color="#FFCCBC">
					<template v-for="opcion in menu">
						<v-list-item @click="redirect($event, opcion.path)">
							<v-list-item-title>
								{{ opcion.name }}
							</v-list-item-title>
						</v-list-item>
					</template>
				</v-list-item-group>
			</v-list>
		</v-navigation-drawer>
	</div>
</template>
<script>
import { cerrarSesion } from "@/authentication/access";

const opcionesMenu = [
	{
		path: "/VentaRemates",
		name: "Ventas-Remates",
	},
	{
		path: "/Comercializacion",
		name: "Comercializacion",
	},
	{
		path: "/EgresosIngresos",
		name: "Egresos Ingresos",
	},
	{
		path: "/EmpContratos",
		name: "Empeños Contratos",
	},
];

export default {
	name: "Navbar",
	data() {
		return {
			drawer: false,
			menu: opcionesMenu,
			group: opcionesMenu.findIndex(
				(opcion) => opcion.path === this.$route.path
			),
		};
	},
	methods: {
		cerrarSesion,
		redirect(event, to) {
			if (this.$route.path === to) {
				event.stopPropagation();
				this.drawer = false;
				return;
			}
			this.$router.push({
				path: to,
			});
		},
	},
};
</script>
<style>
nav {
	padding: 30px;
}

a {
	text-decoration: none;
}

nav a {
	font-weight: bold;
	color: #2c3e50;
}

v-list-item__title router-link-active {
	color: #42b983 !important;
}
</style>
