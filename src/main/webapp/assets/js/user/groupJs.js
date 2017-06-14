var dateFormat = 'yy-mm-dd';
$("#dateStart").datepicker({
	dateFormat : dateFormat,
	minDate : '0d',
	maxDate : '+7d',
	onSelect : function() {
		if ($(this).valid()) {
			$("#dateEnd-error").hide();
		};
	}
});
$("#dateEnd").datepicker({
	dateFormat : dateFormat,
	minDate : new Date(),
	maxDate : '+30d',
	onSelect : function() {
		if ($(this).valid()) {
			$("#dateStart-error").hide();
		};
	}
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
jQuery.validator.addMethod("greaterThan", 
		function(value, element, params) {
		    if (!/Invalid|NaN/.test(new Date(value))) {
		        return new Date(value) >= new Date($(params[0]).val());
		    }
		    return isNaN(value) && isNaN($(params[0]).val()) 
		        || (Number(value) >= Number($(params[0]).val())); 
}, '{1} not greater than {2}.');

jQuery.validator.addMethod("lesserThan", 
		function(value, element, params) {
		    if (!/Invalid|NaN/.test(new Date(value))) {
		        return new Date(value) <= new Date($(params[0]).val());
		    }
		    return isNaN(value) && isNaN($(params[0]).val()) 
		        || (Number(value) <= Number($(params[0]).val())); 
}, '{1} not lesser than {2}.');

// check format date
jQuery.validator.addMethod("isDate", 
	function isDate(value, element) {
		var currVal = value;
	    if(currVal == '')
	        return false;
	    
	    var rxDatePattern = /^(\d{4})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/; //Declare Regex
	    var dtArray = currVal.match(rxDatePattern); // is format OK?
	    
	    if (dtArray == null) 
	        return false;

	    //Checks for mm/dd/yyyy format.
	    dtYear = dtArray[1];
	    dtMonth= dtArray[3];
	    dtDay = dtArray[5];

	    if (dtMonth < 1 || dtMonth > 12) 
	        return false;
	    else if (dtDay < 1 || dtDay> 31) 
	        return false;
	    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
	        return false;
	    else if (dtMonth == 2) 
	    {
	        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
	                return false;
	    }
	    return true;
}, '{0} ' + $("#invalidDate").text());

jQuery.extend(jQuery.validator.messages, {
	required : $("#messageRequired").text()
});

//Setup form validation on the #update-form element
var validator = $("#registerForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		name : "required",
		description : "required",
		dateEnd : {
			required: true,
			isDate: ["Date end"],
			greaterThan: ["#dateStart", "Date start", "Date end"]
		},
		dateStart: {
			required: true,
			isDate: ["Date start"],
			lesserThan: ["#dateEnd", "Date end", "Date start"]
		},
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});