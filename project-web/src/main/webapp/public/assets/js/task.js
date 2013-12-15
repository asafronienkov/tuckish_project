
var selectedTask = {};

clearSelTask = function() {
	selectedTask = {};
};

saveTask = function() {
	$.ajax({
		type: "POST",
		url: "app/tasks/save",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: $("#taskId").val(),
			name: $("#taskName").val(),
			description: $("#taskDesc").val(),
			startDate: $("#taskStartDate").datepicker("getDate"),
			endDate: $("#taskEndDate").datepicker("getDate")
		}),
		success: function(msg) {
			$("#projectDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes saved!</div></p>');
			$("#projectsGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#projectDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes failed!</div></p>');
		}
	});
};

$(document).ready(function() {
	$("#projectSel").select2({
		placeholder: "Select a project...",
		allowClear: true,
		ajax: {
			url: 'app/projects/all',
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
		}
	});
	
	$("#taskStartDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#taskEndDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	$("#tasksGrid").jqGrid({
		url:'app/tasks/all',
		datatype: "json",
		colNames:['ID', 'NAME', 'DESCRIPTION', 'START', 'END', 'LOE'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'name', index: 'name'},
			{name: 'description', index: 'description'},
			{name: 'startDate', index: 'startDate'},
			{name: 'endDate', index: 'endDate'},
			{name: 'levelOfEffort', index: 'levelOfEffort'}
        ],
        rowNum: 10,
        rowList: [10,20,30],
        pager: '#tasksPager',
        height: 200,
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Tasks",
        ondblClickRow: function(rowid, rowIdx, colIdx, event) {
        	$.ajax({
        		type: "GET",
        		url: "app/tasks/find",
        		data: {id: rowid},
        		dataType: "json"
        	}).done(function(data) {
        		selectedTask = data;
        		$("#taskDetails").modal("show");
        	});
        }
	});
	$("#tasksGrid").jqGrid('navGrid', '#tasksPager', { edit:false, add:false, del:false });
	
	$("#taskDetails").on("show.bs.modal", function(e) {
		if (!$.isEmptyObject(selectedTask)) {
			$("#taskModalLabel").html("Edit Task");
			
			$("#taskId").val(selectedProject.id);
			$("#taskId").val(selectedProject.id);
			$("#taskName").val(selectedProject.name);
			$("#taskDesc").val(selectedProject.description);
			$("#startDate").datepicker("setDate", selectedProject.startDate);
			$("#endDate").datepicker("setDate", selectedProject.endDate);
		} else {
			$("#taskModalLabel").html("New Task");
			
			$("#taskId").val("");
			$("#projectId").val("");
			$("#taskName").val("");
			$("#taskDesc").val("");
			$("#startDate").datepicker("setDate", "");
			$("#endDate").datepicker("setDate", "");
		}
	});
});