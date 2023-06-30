<template>
	<v-app>
		<Navbar v-if="mostrarMenu"></Navbar>
		<v-main>
			<v-container id="app">
				<router-view />
			</v-container>
		</v-main>
		<Alerta></Alerta>
	</v-app>
</template>
<script>
import Alerta from "./components/Alerta.vue";
import Navbar from "./components/Navbar.vue";

export default {
	name: "App",
	components: { Navbar, Alerta },
	computed: {
		mostrarMenu() {
			if (this.$route.name === "login") return false;
			if (this.$route.path === "/registro") return false;
			return true;
		},
	},
};
</script>
<style>
#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
}
.v-main__wrap {
	overflow-y: auto;
	max-height: 90vh;
}
</style>
