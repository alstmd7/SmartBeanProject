$('#email').on('change', e => {
	if($('#email').val() !== "") {
		$('#error-email').hide();
		$('#email').parent().css('border-color', 'lightgrey');
	}
});

$('#password').on('change', e => {
	if($('#password').val() !== "") {
		$('#error-password').hide();
		$('#password').parent().css('border-color', 'lightgrey');
		$('#password').parent().css('border-top', 'none');
	}
});

$('#name').on('change', e => {
	if($('#name').val() !== "") {
		$('#error-name').hide();
		$('#name').parent().css('border-color', 'lightgrey');
	}
});

function checkValue(htmlForm) {
	const email = htmlForm.email.value;
	const password = htmlForm.password.value;
	const name = htmlForm.name.value;
	
	let check = true;
	
	if(email === "") {
		$('#error-email').show();
		$('#email').parent().css('border-color', 'red');
		check = false;		
	} 
	else if(password === "") {
		$('#error-password').show();
		$('#password').parent().css('border-color', 'red');
		$('#password').parent().css('border-top', 'solid 1px red');
		check = false;
	}
	else if(name === "") {
		$('#error-name').show();
		$('#name').parent().css('border-color', 'red');
		check = false;
	}
	
	if(check === true) {
		htmlForm.submit();
	}	
	
}