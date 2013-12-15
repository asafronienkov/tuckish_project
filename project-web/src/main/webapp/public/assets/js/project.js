
// This is a placeholder for when a users double clicks a project
var selectedProject = {};

clearSelProject = function() {
	selectedProject = {};
}

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
				id: $("#typeSel").select2("val"),
			},
			startDate: $("#startDate").datepicker("getDate"),
			endDate: $("#endDate").datepicker("getDate")
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
}

$(document).ready(function() {
	$("#typeSel").select2({
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
			if (!$.isEmptyObject(selectedProject)) {
				var data = {id: selectedProject.type.id, text: selectedProject.type.name};
		        callback(data);
			}
		}
	});
	$("#startDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#endDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	$("#projectsGrid").jqGrid({
		url:'app/projects/all',
		datatype: "json",
		colNames:['ID', 'NAME', 'DESCRIPTION', 'TYPE', 'START', 'END'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'name', index: 'name'},
			{name: 'description', index: 'description'},
			{name: 'type', index: 'type', formatter: function(cellvalue, opts, rowObj) { return cellvalue.name; }},
			{name: 'startDate', index: 'startDate'},
			{name: 'endDate', index: 'endDate'}
        ],
        rowNum: 10,
        rowList: [10,20,30],
        pager: '#projectsPager',
        height: 100,
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Projects",
        ondblClickRow: function(rowid, rowIdx, colIdx, event) {
        	$.ajax({
        		type: "GET",
        		url: "app/projects/find",
        		data: {id: rowid},
        		dataType: "json"
        	}).done(function(data) {
        		selectedProject = data;
        		$("#projectDetails").modal("show");
        	});
        }
	});
	$("#projectsGrid").jqGrid('navGrid', '#projectsPager', { edit:false, add:false, del:false });
	
	$("#projectDetails").on("show.bs.modal", function(e) {
		if (!$.isEmptyObject(selectedProject)) {
			$("#projectModalLabel").html("Edit Project");
			
			$("#projectId").val(selectedProject.id);
			$("#projectName").val(selectedProject.name);
			$("#projectDesc").val(selectedProject.description);
			$("#typeSel").select2("val", selectedProject.type);
			$("#startDate").datepicker("setDate", selectedProject.startDate);
			$("#endDate").datepicker("setDate", selectedProject.endDate);
		} else {
			$("#projectModalLabel").html("New Project");
			
			$("#projectId").val("");
			$("#projectName").val("");
			$("#projectDesc").val("");
			$("#typeSel").select2("data", null);
			$("#startDate").datepicker("setDate", "");
			$("#endDate").datepicker("setDate", "");
		}
	});
});