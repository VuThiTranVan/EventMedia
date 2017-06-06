window.onload = function() {
	//$(".alert").hide();
	$("#formAlert").hide();
	$("#formAlertImage").hide();
	$("#formAlertError").hide();
	$("#formAlertErrorDes").hide();
	$("#formAlertEmpty").hide();
}
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

function goBack() {
	window.history.back();
}

function checkEmpty(str) {
	if (str == "") {
		return true;
	}
	return false;
}
$('#btnImport').on("click", function(e) {
	e.preventDefault();
	var fileImport = $("#fileImport").val();
	var fileName = $("#fileName").val();
	
	var title = $("#title").val();
	var description = $("#description").val();
	
	var check = true;
	$("#err_file").text("");
	$("#err_formatFile").text("");
	$("#err_data").text("");
	
	if (checkEmpty(fileImport)) {
		check = false;
		$("#formAlert").slideDown(400);
	} else {
		var suffixesFile = fileName.substring(fileName.lastIndexOf(".") + 1);
		$("#formAlert").slideUp(400);
		if (("png" != suffixesFile)&&("jpg" != suffixesFile)&&("jpeg" != suffixesFile)) {
			check = false;
			$("#formAlertImage").slideDown(400);
		}else{
			$("#formAlertImage").slideUp(400);
		}
	}
	if(title == "" || description == ""){
		check = false;
		$("#formAlertEmpty").slideDown(400);
	}else{
		$("#formAlertEmpty").slideUp(400);
	}
	if (title.length > 50) {
		check = false;
		$("#formAlertError").slideDown(400);
	} else {
		$("#formAlertError").slideUp(400);
	}
	
	if (description.length > 500) {
		check = false;
		$("#formAlertErrorDes").slideDown(400);
	} else {
		$("#formAlertErrorDes").slideUp(400);
	}
	
	if (check) {
		$("#formImport").submit();
	}
});
