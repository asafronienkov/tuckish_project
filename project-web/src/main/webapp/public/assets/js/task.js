
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
			projectId: $("#projectId").val(),
			name: $("#taskName").val(),
			description: $("#taskDesc").val(),
			startDate: $("#taskStartDate").datepicker("getDate"),
			endDate: $("#taskEndDate").datepicker("getDate")
		}),
		success: function(msg) {
			$("#taskDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes saved!</div></p>');
			$("#tasksGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#taskDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes failed!</div></p>');
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
	
	$("#tasksGrid").jqGrid({
		url:'app/tasks/all',
		datatype: "json",
		colNames:['ID', 'ACTION', 'NAME', 'DESCRIPTION', 'START', 'END', 'LOE'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 75},
			{name: 'name', index: 'name'},
			{name: 'description', index: 'description'},
			{name: 'startDate', index: 'startDate'},
			{name: 'endDate', index: 'endDate'},
			{name: 'levelOfEffort', index: 'levelOfEffort'}
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#tasksPager',
        autoheight: true,
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Tasks",
        gridComplete: function() {
        	var ids = jQuery("#tasksGrid").jqGrid('getDataIDs');
        	for (var i = 0; i < ids.length; i++) {
        		var cl = ids[i];
        		edit = '<button type="button" class="btn btn-info btn-sm" onclick="loadTask(' + cl + ');">Edit</button>';
        		del = '<button type="button" class="btn btn-danger btn-sm" onclick="deleteTask(' + cl + ');">Delete</button>';
        		jQuery("#tasksGrid").jqGrid('setRowData', ids[i], {act: edit + del});
        	}
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