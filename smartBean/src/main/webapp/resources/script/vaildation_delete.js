$("#deleteAgree").change(function() {

	if ($("#deleteAgree").is(":checked") && $('#password').val() !== "") {
		$('#delete-btn').attr('disabled', false);
		$('#delete-btn').attr('style', 'background-color: #d73434; cursor: pointer');
	} else {
		$('#delete-btn').attr('disabled', true);
		$('#delete-btn').attr('style', 'background-color: lightgray; cursor: default');
	}

});

$('#password').on('change', e => {
	const password = $('#password').val();
	
	if($("#deleteAgree").is(":checked") && password !== ""){
		$('#delete-btn').attr('disabled', false);
		$('#delete-btn').attr('style', 'background-color: #d73434; cursor: pointer');
	}
	
	if(password === "") {
		$('#delete-btn').attr('disabled', true);
		$('#delete-btn').attr('style', 'background-color: lightgray; cursor: default');
	}

});
