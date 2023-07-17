$('#email').on('change', e => {
	if($('#email').val() !== "") {
		$('#error-email').hide();
	}
});

$('#name').on('change', e => {
	if($('#name').val() !== "") {
		$('#error-name').hide();
	}
});

$('#password').on('change', e => {
	if($('#password').val() !== "") {
		$('#error-password').hide();
	}
});

$('#check_password').on('change', e => {
	if($('#check_password').val() === $('#password').val()) {
		$('#error-check_password').hide();
	}
});

function checkValue(htmlForm) {
	const email = htmlForm.email.value;
	const name = htmlForm.name.value;
	const password = htmlForm.password.value;
	const check_password = htmlForm.check_password.value;
	
	let check = true;
	
	if(email === "") {
		$('#error-email').show();
		check = false;		
	} 
	else if(name === "") {
		$('#error-name').show();
		check = false;
	}
	else if(password === "") {
		$('#error-password').show();
		check = false;
	}
	else if(password !== check_password) {
		$('#error-check_password').show();
		check = false;
	}
	
	if(check === true) {
		htmlForm.submit();
	}	
	
}