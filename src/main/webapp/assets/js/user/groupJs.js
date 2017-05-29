var dateFormat = 'yy-mm-dd';
$("#dateStart").datepicker({
	dateFormat : dateFormat,
	minDate : '0d',
	maxDate : '+7d'
});
$("#dateEnd").datepicker({
	dateFormat : dateFormat,
	minDate : new Date(),
	maxDate : '+30d'
});

$("#btnClear").click(function() {
	$("#registerForm")[0].reset();
	$("#dateEnd-error").hide();
	$("#dateStart-error").hide();
	$("#note-error").hide();
	$("#description-error").hide();
	$("#name-error").hide();
	$("#btnRegiser").prop('disabled', false);
})

$("#btnRegiser").click(function() {
	if ($("#registerForm").valid()) {
		var postData = $("#registerForm").serializeArray();
		var formURL = $("#registerForm").attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : 'json',
			async: true,
			success : function(data) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 201) {
					$('#messageContainerFail').addClass('hidden_elem');
					$('#messageContainer').removeClass('hidden_elem');
					$("#btnClear").hide();
					$("#btnRegiser").hide();
				}
				if (jqXHR.status == 409){
					$('#messageContainerFail').removeClass('hidden_elem');
					$('#messageContainer').addClass('hidden_elem');
				}
			}
		});

	}
})
// validate register form
jQuery.extend(jQuery.validator.messages, {
	required : "This is item required"
});

//Setup form validation on the #update-form element
var validator = $("#registerForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		name : "required",
		description : "required",
		dateEnd : "required",
		dateStart: "required",
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});