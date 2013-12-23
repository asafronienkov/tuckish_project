
saveProject = function() {
	$.ajax({
		type: "POST",
		url: "app/projects/save",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: projectId,
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

saveNewTask = function() {
	$.ajax({
		type: "POST",
		url: "app/tasks/save?projectId=" + projectId,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: 0,
			name: $("#taskName").val(),
			description: $("#taskDesc").val(),
			startDate: $("#taskStartDate").datepicker("getDate"),
			endDate: $("#taskEndDate").datepicker("getDate")
		}),
		success: function(data) {
			$("#taskDetails").modal("hide");
			loadTask(data.id);
		},
		error: function(msg) {
			$("#taskDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Changes failed!</div></p>');
		}
	});
};

deleteTask = function(taskId) {
	$.ajax({
		type: "DELETE",
		url: "app/tasks/delete?id=" + taskId,
		success: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Task deleted!</div></p>');
			$("#tasksGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Task delete failed!</div></p>');
		}
	});
};

$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "app/projects/find?id=" + projectId, // projectId is created by the project JSP file
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			$("#projectId").val(data.id);
			$("#projectName").val(data.name);
			$("#projectDesc").val(data.description);
			
			var type = {};
			type.id = data.type.id;
			type.text = data.type.name;
			$("#projectTypeSel").select2("data", type);
			
			$("#projectStartDate").datepicker("setDate", data.startDate);
			$("#projectEndDate").datepicker("setDate", data.endDate);
		},
		error: function(msg) {
			$("#genErrBody").html("Failed to load project details, contact Support");
			$("#generalError").modal("show");
		}
	});
	
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
		}
	});
	$("#projectStartDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#projectEndDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	$("#tasksGrid").jqGrid({
		url:'app/tasks/project?id=' + projectId,
		datatype: "json",
		colNames:['ID', 'ACTION', 'NAME', 'DESCRIPTION', 'START', 'END', 'LOE'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 120, fixed: true, align: 'center'},
			{name: 'name', index: 'name', width: 125, fixed: true, align: 'center'},
			{name: 'description', index: 'description'},
			{name: 'startDate', index: 'startDate', width: 125, fixed: true, align: 'center'},
			{name: 'endDate', index: 'endDate', width: 125, fixed: true, align: 'center'},
			{name: 'levelOfEffort', index: 'levelOfEffort', width: 50, fixed: true, align: 'center'}
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
        		jQuery("#tasksGrid").jqGrid('setRowData', ids[i], {act: " " + edit + " " + del + " "});
        	}
        }
	});
	$("#tasksGrid").jqGrid('navGrid', '#tasksPager', { edit:false, add:false, del:false });
});