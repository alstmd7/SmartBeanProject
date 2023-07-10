$('#email').on('change', e => {
	const email = $('#email').val();
	
	if(email !== "") {
		$('#error-email').hide();
	}
});

$('#password').on('change', e => {
	const password = $('#password').val();
	
	if(password !== "") {
		$('#error-password').hide();
	}
});

function checkValue(htmlForm) {
	const email = htmlForm.email.value;
	const password = htmlForm.password.value;
	
	let check = true;
	
	if(email === "") {
		$('#error-email').show();
		check = false;
	} else if(password === "") {
		$('#error-password').show();
		check = false;
	}
	
	if(check === true) {
		htmlForm.submit();
	}
}