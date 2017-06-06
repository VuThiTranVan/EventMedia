function clickBtnEdit() {
	$(".editForm").removeClass('hidden_elem');
	$(".lableForm").addClass('hidden_elem');
	$("#savebtn").prop('disabled', true);
	document.getElementById("url").readOnly = true; 
}

function clickBtnCancel() {
	$(".lableForm").removeClass('hidden_elem');
	$(".editForm").addClass('hidden_elem');
	$("#updateForm")[0].reset();
}

// update user
$("#savebtn").click(function() {
	if ($("#updateForm").valid()) {
		$("#updateForm").submit();
	}
});

jQuery.extend(jQuery.validator.messages, {
	required : "This is item required.",
	email: "This is invalid email.",
	number: "This is invalid number"
});

//Setup form validation on the #update-form element
var validator = $("#updateForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		title : "required",
		url : "required",
		description : "required"
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});

$("#title").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

//$("#url").blur(function() { 
$("#choose").click(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$("#description").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

function checkItemChangeValue() {
	var title = $('#title').val();
	var lbltitle = $('#lbltitle').text();
	
	var url = $('#url').val();
	var lblurl = $('#lblurl').text();
	
	var description = $('#description').val();
	var lbldescription = $('#lbldescription').text();
	
	if (title == lbltitle 
			&& url == lblurl
			&& description == lbldescription
			) {
		return false;
	}
	else{
		return true;
	}
}

$("#btn_download").click(function() {
	
	$("#btn_seach").click();
	$("#searchForm").attr("action", "/SpringSecurity/managementUsers/downloadCSV");
	$("#searchForm").submit();
	
});

$(document).on('click', '.browse', function() {
	var file = $(this).parent().parent().parent().find('.file');
	file.trigger('click');
});

$(document).on(
		'change',
		'.file',
		function() {
			$(this).parent().find('.form-control').val(
					$(this).val().replace(/C:\\fakepath\\/i, ''));
		});