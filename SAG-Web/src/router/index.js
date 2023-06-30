import Vue from "vue";
import VueRouter from "vue-router";
import VentaRemates from "../views/VentasRemates.vue";
import Comercializacion from "../views/Comercializacion.vue";
import EgresosIngresos from "../views/IngresosEgresos.vue";
import EmpeñosContratos from "../views/EmpeñosContratos.vue";
import Login from "@/views/acceso/Login.vue";
import NotFound from "@/views/errors/NotFound.vue";
import { validateSession } from "@/authentication/access";

Vue.use(VueRouter);

const routes = [
	{
		path: "/login",
		name: "login",
		component: Login,
	},
	{
		path: "/VentaRemates",
		name: "VentaRemates",
		component: VentaRemates,
	},
	{
		path: "/Comercializacion",
		name: "Comercializacion",
		component: Comercializacion,
	},
	{
		path: "/EgresosIngresos",
		name: "EgresosIngresos",
		component: EgresosIngresos,
	},
	{
		path: "/EmpContratos",
		name: "EmpContratos",
		component: EmpeñosContratos,
	},
	{
		path: "*",
		name: "*",
		component: NotFound,
	},
];

const router = new VueRouter({
	routes,
});

router.beforeEach((to, from, next) => {
	if (
		!validateSession() &&
		(to.name === "login" ||
			to.name === "registro" ||
			to.path === "/registro")
	) {
		next();
		return;
	}

	if (
		validateSession() &&
		(to.name === "login" ||
			to.name === "registro" ||
			to.path === "/registro")
	) {
		next("/");
		return;
	}

	if (!validateSession()) {
		next("/login");
		return;
	}

	next();
});

export default router;
