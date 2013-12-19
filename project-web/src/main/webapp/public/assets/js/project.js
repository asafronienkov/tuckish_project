
saveProject = function() {
	$.ajax({
		type: "POST",
		url: "app/projects/save",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: $("#projectId").val(),
			name: $("#projectName").val(),
			description: $("#projectDesc").val(),
			type: {
				id: $("#projectTypeSel").select2("val")
			},
			startDate: $("#projectStartDate").datepicker("getDate"),
			endDate: $("#projectEndDate").datepicker("getDate")
		}),
		success: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes saved!</div></p>');
		},
		error: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes failed!</div></p>');
		}
	});
};

$(document).ready(function() {
	$("#projectTypeSel").select2({
		placeholder: "Select a type...",
		allowClear: true,
		ajax: {
			url: 'app/types/all',
			dataType: 'json',
			results: function (data) {
				var formattedData = [];
				$.each(data, function(idx, item) {
					formattedData.push({
						'id': item.id,
						'text': item.name
					});
				});
				return {results: formattedData};
			}
		},
		initSelection : function (element, callback) {
	        callback(JSON.parse(element.val()));
		}
	});
	$("#projectStartDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#projectEndDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
});