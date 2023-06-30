export const sleep = (ms) => {
	return new Promise((res) => setTimeout(res, ms));
};

export const formatDate = (dateStr) => {
	let date = new Date(dateStr);
	let options = {
		year: "numeric",
		month: "2-digit",
		day: "2-digit",
	};

	return date.toLocaleDateString("es-MX", options);
};
