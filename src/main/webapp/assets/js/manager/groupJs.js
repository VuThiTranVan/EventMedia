// init
var frmName, frmDescription, frmDateStart, frmDateEnd, frmNote, frmStatus, frmType;

// Go to top page
function goTopPage() {
	$('html,body').animate({
		scrollTop : 0
	}, 300);
}

function getGroup() {
	$.ajax({
	    url : "/EventMedia/manager/init",
	    type : "GET",
	    data : false,
	    dataType : 'json',
	    async : true,
	    success : function(data, textStatus, jqXHR) {
	    	if (jqXHR.status == 404) {
		    	$("#messageContainer").removeClass('hidden_elem');
		    }
	    	if (jqXHR.status == 200) {

	    		$("#divBtnEdit").removeClass('hidden_elem');
	    		
	    		// display section group info
	    		$(".infoGroup").show();

	    		// hiden button edit when status of group is inactive
	    		if (data.status == 0) {

	    			$("#btnEdit").show();
	    			$("#btnCancel").hide();
	    			$("#btnSave").hide();
	    			$("#btnDelete").attr("href", "/EventMedia/manager/"+data.id);

	    			frmName = data.name;
	    			frmDescription = data.description;
	    			frmDateStart = data.dateStart;
	    			frmDateEnd = data.dateEnd;
	    			frmNote = data.note;
	    			frmStatus = data.status;
	    			frmType = data.type;
	    		} else {
	    			$("#btnDelete").attr("href", "/EventMedia/manager/"+data.id);
	    			$("#btnStatistical").attr("href", "/EventMedia/manager/"+data.id+"/chart");
	    			$("#btnEdit").hide();
	    			$("#btnCancel").hide();
	    			$("#btnSave").hide();

	    		}

		    	// group info
	    		$("#id").val(data.id);
	    		$("#name").val(data.name);
	    		$("#description").val(data.description);
	    		$("#note").val(data.note);
	    		$("#dateStart").val(data.dateStart);
	    		$("#dateEnd").val(data.dateEnd);
	    		
	    		$('#inactive').prop("checked", true);
	    		if (data.status == 0) {
	    			$('#active').prop("checked", true);
	    			$(".listMember").hide();
	    		}

	    		$('#public').prop("checked", true);
	    		if (data.type == 0) {
	    			$('#private').prop("checked", true);
	    		}

	    		// List member
	    		if (data.user.length > 0) {
		    		if ($.fn.DataTable.isDataTable('#dataTables-result')) {
		                $('#dataTables-result').DataTable().destroy();
		            }
	
	                $('#dataTables-result').DataTable({
	                    "bProcessing" : true,
	                    "aaData" : data.user,
	                    "createdRow" : function(row, data, dataIndex) {
	                        $(row).addClass('gradeX');
	                    },
	                    "aoColumns" : [
	                    	{
	                            "mDataProp" : "username",
	                            "mRender" : function(data, type, row) {
	                                return "<a target='_blank' href='/EventMedia/manager/user/" + row.id + "'>"
	                                        + data+"</a>";
	                            },
	                        }, { "mDataProp" : "name"
	                        }, { "mDataProp" : "email"
	                        }, { "mDataProp" : "phone"
	                        }, { "mDataProp" : "statusJoin"
	                        }, { "mDataProp" : "id",
	                        	"mRender": function(data, type, row) {
	                        		if (row.statusJoin == "Request") {
	                        		 return "<button onclick='clickRejectUser("+data+","+$("#id").val()+",this)'>" +
	                        		 		"<img src='./assets/imgs/reject.jpg' alt='Reject user' style='height: 20px;'/>" +
	                        		 		"</button>" +
	                        		 		"  <button onclick='clickAcceptUser("+data+","+$("#id").val()+",this)'>" +
	                        		 		"<img src='./assets/imgs/accept.jpg' alt='Accept user' style='height: 20px;'/></button>";
	                        		} else {
	                        			 return "<button onclick='clickRemoveUser("+data+","+$("#id").val()+",this)'>" +
	                        		 		"<img src='./assets/imgs/delete-record.png' alt='Remove user' style='height: 20px;'/>" +
	                        		 		"</button>";
	                        		}

	                        	}
	                        }],
	                    responsive : true
	                });
	                $(".listMember").show();
				} else {
					 $(".listMember").hide();
					if (data.status == 0) {
						$(".listMember").show();
		    			$(".listMemberBody").html("<b>No member!</b>");
	    			}
	    		}

	    		// list image
	    		if (data.image.length > 0) {
	    			$(".listImage").show();
	    		} else {
	    			$(".listImageBody").html("<b>No image!</b>");
	    			$(".listImage").show();
	    		}
	    		
	    		if ($.fn.DataTable
	                    .isDataTable('#dataTables-image')) {
	                $('#dataTables-image').DataTable()
	                        .destroy();
	            }

                $('#dataTables-image').DataTable({
                    "bProcessing" : true,
                    "aaData" : data.image,
                    "createdRow" : function(row, data, dataIndex) {
                        $(row).addClass('gradeX');
                    },
                    "aoColumns" : [
                    	{
                            "mDataProp" : "title"
                        }, { "mDataProp" : "userCreate"
                        }, { "mDataProp" : "votes.length"
                        }, { "mDataProp" : "id",
                        	"mRender": function(data, type, row) {
                        		 return "<button onclick='clickRemoveImage("+data+",this)'>" +
                        		 		"<img src='./assets/imgs/delete-record.png' style='height: 20px;'/>" +
                        		 		"</button>";
                            }
                        }, 
                        { "mDataProp" : "url",
                        	"mRender": function(data, type, row) {
	                       	return "<img src='"+data+"' style='height: 150px;'"
	                            +" data-to='"+data+"'"
	                             + " data-caption='Like: <b>"+row.votes.length +"</b><br>" +
	                             		"<b>Title</b>: "+row.title +
	                             "</b></u><br>Description: "+row.description+"' class='modallery'>";
                        	}
                        }],
                    responsive : true
                });
            	$(this).modallery({
            	    title: "<b>"+data.name +"</b>",
            	    navigate: true,
            	    arrows: true,
            	    keypress: true
            	  });
		    }
	    	
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	$("#messageContainer").removeClass('hidden_elem');
	    	$(".manageUser").hide();
	    }
	});
}

// edit
function clickBtnEdit() {
	isEditGroupForm(false);
}

function clickBtnCancel() {
	$("#name").val(frmName);
	$("#description").val(frmDescription);
	$("#note").val(frmNote);
	$("#dateStart").val(frmDateStart);
	$("#dateEnd").val(frmDateEnd);
	$('#active').prop("checked", true);
	$('#public').prop("checked", true);
	if (frmType == 0) {
		$('#private').prop("checked", true);
	}
	isEditGroupForm(true);
}

function clickBtnSave() {
	if ($("#EditGroupForm").valid()) {
		var postData = $("#EditGroupForm").serializeArray();
		var formURL = $("#EditGroupForm").attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			dataType : 'json',
			async: true,
			success : function(data, textStatus, jqXHR) {
				if (jqXHR.status == 200) {
					isEditGroupForm(true);
					
					$('#message').html($("#mgsUpdateGroupSuccess").text());
					
					if ($('input[type=radio][name=status]:checked').val() == 1) {
						getGroup();
						$("#btnEdit").hide();


					} else {
						frmName = $("#name").val();
		    			frmDescription = $("#description").val();
		    			frmNote = $("#note").val();
		    			frmDateEnd = $("#dateEnd").val();
		    			frmDateStart = $("#dateStart").val();
		    			frmType =  $('input[type=radio][name=type]:checked').val();
	    			}
				}
				else {
					$('#message').html($("#mgsUpdateGroupError").text());
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 200) {
					isEditGroupForm(true);
					$('#message').html($("#mgsUpdateGroupSuccess").text());
					if ($('input[type=radio][name=status]:checked').val() == 1) {
						getGroup();
						$("#btnEdit").hide();

					} else {
						frmName = $("#name").val();
		    			frmDescription = $("#description").val();
		    			frmNote = $("#note").val();
		    			frmDateEnd = $("#dateEnd").val();
		    			frmDateStart = $("#dateStart").val();
		    			frmType =  $('input[type=radio][name=type]:checked').val();
	    			}
				}
				else {
					$('#message').html($("#mgsUpdateGroupError").text());
				}
			}
		});
	
	}
}

// when Status change = inactive then show wanning
$('input[type=radio][name=status]').change(function () {
	if ($('input[type=radio][name=status]:checked').val() == 1) {
		$('<div></div>').appendTo('body')
		.html('<div><h6>You have chosen to close this group.<br>Are you sure?</h6></div>')
		.dialog({
		modal: true,
		title: 'Confirm!!!',
		zIndex: 10000,
		autoOpen: true,
		width: 'auto',
		resizable: false,
		buttons: {
		    Yes: function () {
		        $(this).dialog("close");
		    },
		    No: function () {

		        // Change status is  0
		    	$('#active').prop("checked", true);
		        $(this).dialog("close");
		    }
		},
		close: function (event, ui) {
		    $(this).remove();
		}
		});
		
	}
});

// datepicker
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

// validate
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
}, '{0} is invalid format date.');

//validate register form
jQuery.extend(jQuery.validator.messages, {
	required : "This is item required"
});

//Setup form validation on the #update-form element
var validator = $("#EditGroupForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		name :  "required",
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

function isEditGroupForm(flgUpdate) {
	// item input
	$("#name").prop('disabled', flgUpdate);
	$("#description").prop('disabled', flgUpdate);
	$("#note").prop('disabled', flgUpdate);
	$("#dateStart").prop('disabled', flgUpdate);
	$("#dateEnd").prop('disabled', flgUpdate);
	$("input[type=radio][name=status]").prop("disabled",flgUpdate);
	$("input[type=radio][name=type]").prop("disabled",flgUpdate);

	if (flgUpdate) {

		// item button
		$("#btnEdit").show();
		$("#btnSave").hide();
		$("#btnCancel").hide();
		$("#name").removeClass('css-required');
		$("#description").removeClass('css-required');
		$("#dateStart").removeClass('css-required');
		$("#dateEnd").removeClass('css-required');
		$("#name-error").hide();
		$("#description-error").hide();
		$("#note-error").hide();
		$("#dateStart-error").hide();
		$("#dateEnd-error").hide();
	} else {
		$("#btnEdit").hide();
		$("#btnSave").show();
		$("#btnCancel").show();

		$("#name").addClass('css-required');
		$("#description").addClass('css-required');
		$("#dateStart").addClass('css-required');
		$("#dateEnd").addClass('css-required');
	}
}

// REMOVE IMAGE
function clickRemoveImage(id, el) {
	if (confirm("Are you sure delete record?") == true) {
		$('#messageContainer').html('');
		
		var formURL = "/EventMedia/manager/image/" + id;
		$.ajax({
			url : formURL,
			type : "GET",
			data : false,
			dataType : 'json',
			success : function(data) {
				goTopPage();
				if (data) {
					// remove datatable
					$('#dataTables-image').DataTable().row($(el).parents('tr')).remove().draw();

					// Message
					$('#message').html($("#mgsRemoveImageSuccess").text());
				}
				else {
					$('#message').html($("#mgsRemoveImageError").text());
				}
			},
			error : function(error) {
				goTopPage();
				$('#message').html($("#mgsRemoveImageError").text());
			}
		});
	}
}

// REMOVE USER
function clickRemoveUser(id, idGroup, el) {
	if (confirm("Are you sure remove user throw out this group?") == true) {
		$('#messageContainer').html('');
		var formURL = "/EventMedia/manager/user/remove/"+idGroup+"/" + id;
		$.ajax({
			url : formURL,
			type : "GET",
			data : false,
			dataType : 'json',
			success : function(data) {
				goTopPage();
				if (data) {
					// remove datatable
					$('#dataTables-result').DataTable().row($(el).parents('tr')).remove().draw();
	
					// Message
					$('#message').html($("#mgsRemoveUserSuccess").text());
				}
				else {
					$('#message').html($("#mgsRemoveUserError").text());
				}
			},
			error : function(error) {
				goTopPage();
				$('#message').html($("#mgsRemoveUserError").text());
			}
		});
	}

}
//REJECT USER JOIN GROUP
function clickRejectUser(id, idGroup, el) {
	if (confirm("Are you sure reject user join your group?") == true) {
		$('#messageContainer').html('');
		var formURL = "/EventMedia/manager/user/reject/"+idGroup+"/" + id;
		$.ajax({
			url : formURL,
			type : "GET",
			data : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					
					// Confirm to send mail: yes/no
					if (confirm("You want to send a letter to notify users. Yes or No?") == true) {
						window.location.href="mailto:"+$(el).parents('tr').find("td:eq(2)").text()+
						 "?subject=Notification: Your request join group have REJECT&body=Dear "+$(el).parents('tr').find("td:eq(1)").text()+",%0D%0A";
					}

					// remove datatable
					$('#dataTables-result').DataTable().row($(el).parents('tr')).remove().draw();
				}
				else {
					goTopPage();
					$('#message').html($("#mgsRejectUserError").text());
				}
			},
			error : function(error) {
				goTopPage();
				$('#message').html($("#mgsRejectUserError").text());
			}
		});
	}
}


//ACCEPT USER JOIN GROUP
function clickAcceptUser(id, idGroup, el) {
	$('#messageContainer').html('');
	var formURL = "/EventMedia/manager/user/accept/"+idGroup+"/" + id;
	$.ajax({
		url : formURL,
		type : "GET",
		data : false,
		dataType : 'json',
		success : function(data) {
			if (data) {
				// Confirm to send mail: yes/no
				if (confirm("You want to send a letter to notify users. Yes or No?") == true) {
					window.location.href="mailto:"+$(el).parents('tr').find("td:eq(2)").text()+
					 "?subject=Notification: Your request join group have accept&body=Dear "+$(el).parents('tr').find("td:eq(1)").text()+",%0D%0A";
				}

				// change value
				$(el).parents('tr').find("td:eq(4)").text("Approve");
				$(el).parents('tr').find("td:eq(5)").html("<button onclick='clickRemoveUser("+id+","+idGroup+",this)'>" +
        		 		"<img src='./assets/imgs/delete-record.png' alt='Remove user' style='height: 20px;'/></button>");
			}
			else {
				goTopPage();s
				$('#message').html($("#mgsAcceptUserError").text());
			}
		},
		error : function(error) {
			goTopPage();
			$('#message').html($("#mgsAcceptUserError").text());
		}
	});

}