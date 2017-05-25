$("#birthday").datepicker({
	dateFormat : 'yy-mm-dd',
	minDate : new Date('1960-01-01'),
	maxDate : '-1d'
});

$("#btnClear").click(function() {
	$("#registerForm")[0].reset();
	$("#username-error").hide();
	$("#password-error").hide();
	$("#name-error").hide();
	$("#phone-error").hide();
	$("#email-error").hide();
	$("#birthday-error").hide();
})

$("#btnRegiser").click(function() {
	if ($("#registerForm").valid()) {
		$("#registerForm").submit();
	}
})

// Check isExit of username
$("#username").blur(function(e) {
	var formURL = "/EventMedia/register/isExitUsername";
	$.ajax({
		url : formURL,
		type : "GET",
		data : {
			username: $("#username").val()
		},
		dataType : 'json',
		async: true,
		success : function(data) {
			alert(1 + data.message);
			$("#btnRegiser").prop('disabled', false);
			$('#messageContainer').addClass('hidden_elem');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 200) {
				$("#btnRegiser").prop('disabled', false);
				$('#messageContainer').addClass('hidden_elem');
			} else {
				$("#btnRegiser").prop('disabled', true);
				$("#messageContainer").add
				$('#messageContainer').removeClass('hidden_elem');
			}
		}
	});
})

// validate register form


jQuery.extend(jQuery.validator.messages, {
	required : "This is item required.",
	email: "This is invalid email.",
	number: "This is invalid number"
});

//Setup form validation on the #update-form element
var validator = $("#registerForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		username : "required",
		password : "required",
		name : "required",
		birthday: "required",
		phone: 
		{  
			required: true,
			number: true
		},
		email : {  
			required: true,
			email: true
		}
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});