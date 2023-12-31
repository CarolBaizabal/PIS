import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "@/plugins/vuetify";
import "@mdi/font/css/materialdesignicons.css";
import VueSession from "vue-session";

Vue.config.productionTip = false;
Vue.use(VueSession);

new Vue({
	router,
	vuetify,
	render: function (h) {
		return h(App);
	},
}).$mount("#app");
