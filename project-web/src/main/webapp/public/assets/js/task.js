
saveTask = function() {
	$.ajax({
		type: "POST",
		url: "app/tasks/save?projectId=" + projectId,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: taskId,
			name: $("#taskName").val(),
			description: $("#taskDesc").val(),
			startDate: $("#taskStartDate").datepicker("getDate"),
			endDate: $("#taskEndDate").datepicker("getDate"),
			levelOfEffort: $("#taskLOE").val()
		}),
		success: function(data) {
			$("#taskAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Save completed!</div></p>');
		},
		error: function(msg) {
			$("#taskAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Save failed!</div></p>');
		}
	});
};

$(document).ready(function() {
	$("#taskStartDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#taskEndDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	$.ajax({
		type: "GET",
		url: "app/tasks/find?id=" + taskId, // taskId is created by the task JSP file
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			$("#taskId").val(data.id);
			$("#taskName").val(data.name);
			$("#taskDesc").val(data.description);
			$("#taskStartDate").datepicker("setDate", data.startDate);
			$("#taskEndDate").datepicker("setDate", data.endDate);
			$("#taskLOE").val(data.levelOfEffort);
		},
		error: function(msg) {
			$("#genErrBody").html("Failed to load Task details, contact Support");
			$("#generalError").modal("show");
		}
	});
});