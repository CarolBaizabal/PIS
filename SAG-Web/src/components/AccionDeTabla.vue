<template>
	<v-tooltip top>
		<template #activator="{ on, attrs }">
			<v-btn depressed fab x-small :color="color" v-on="on" v-bind="attrs" @click="executeAction" >
				<v-icon color="white">mdi-{{ icon }}</v-icon>
			</v-btn>
		</template>
		<span>{{ tooltip }}</span>
	</v-tooltip>
</template>
<script>
export default {
	name: "AccionDeTabla",
	props: {
		icon: String,
		color: String,
		tooltip: String,
		action: Function,
	},
	methods: {
		executeAction() {
			if (this.action !== undefined) {
				this.action();
			}
		},
	},
};
</script>
