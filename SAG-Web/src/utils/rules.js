const required = (value) => {
	if (value === null || value === undefined) return "Campo requerido";
	return true;
};
const email = (value) => {
	if (value === null || value === undefined) return true;
	let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (!regex.test(value)) return "Correo no válido";
	return true;
};
const number = (value) => {
	if (value === null || value === undefined) return true;
	if (isNaN(value)) return "Debe ser un número";

	return true;
};

export const formRules = {
	required,
	email,
	number,
};
