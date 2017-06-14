$("#search-image").click(function() {
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
});

// paging
function nextPage(noPage) {
	$("#noPage").val(noPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

function prevPage(noPage) {
	$("#noPage").val(noPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

function goPageTotal(totalPage) {
	$("#noPage").val(totalPage);
	$("#searchForm").attr("action", "/EventMedia/searchImage");
	$("#searchForm").submit();
}

$("#noPage").blur(function() {
	if ( $("#noPage").val().match(/^\d+$/) && $("#noPage").val() != "" ) {
		$("#searchForm").attr("action", "/EventMedia/searchImage");
		$("#searchForm").submit();
	}
});

function voteImage(el, id) {
	$("#mgsVote").addClass('hidden_elem');
	$.ajax({
		url : "/EventMedia/user/vote/add/" + id,
		type : "GET",
		data : false,
		dataType : 'json',
		async: true,
		success : function(data) {
			if (data) {
				$(el).closest('.col-sm-4').find('#add').addClass('hidden_elem');
				$(el).closest('.col-sm-4').find('#remove').removeClass('hidden_elem');
				$(el).closest('.col-sm-4').find('.vote').text(parseInt($(el).closest('.col-sm-4').find('.vote').text()) + 1);
			} else {
				$("#mgsVote").removeClass('hidden_elem');
			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#mgsVote").removeClass('hidden_elem');
		}
	});
}

function removeVoteImage(el, id) {
	$("#mgsVote").addClass('hidden_elem');
	$.ajax({
		url : "/EventMedia/user/vote/remove/" + id,
		type : "GET",
		data : false,
		dataType : 'json',
		async: true,
		success : function(data) {
			if (data) {
				$(el).closest('.col-sm-4').find('#add').removeClass('hidden_elem');
				$(el).closest('.col-sm-4').find('#remove').addClass('hidden_elem');
				$(el).closest('.col-sm-4').find('.vote').text(parseInt($(el).closest('.col-sm-4').find('.vote').text()) - 1);
			} else {
				$("#mgsVote").removeClass('hidden_elem');
			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#mgsVote").removeClass('hidden_elem');
		}
	});
}
window.onload = function() {
	$(this).modallery({
		title : $("#homeTitleModallery").text(),
		navigate : true,
		arrows : true,
		keypress : true
	});
	$("#mdlyFooter").hide();
}
