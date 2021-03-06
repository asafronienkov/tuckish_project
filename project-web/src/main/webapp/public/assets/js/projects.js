
saveNewProject = function() {
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
				id: $("#typeSel").select2("val")
			},
			startDate: $("#projectStartDate").datepicker("getDate"),
			endDate: $("#projectEndDate").datepicker("getDate")
		}),
		success: function(data) {
			$("#projectDetails").modal("hide");
			loadProject(data.id);
		},
		error: function(msg) {
			$("#projectDetails").modal("hide");
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Failed to create new project: ' + msg + '</div></p>');
		}
	});
};

deleteProject = function(projectId) {
	$.ajax({
		type: "DELETE",
		url: "app/projects/delete?id=" + projectId,
		success: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Project deleted!</div></p>');
			$("#projectsGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#projectAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Delete failed!</div></p>');
		}
	});
};

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
		}
	});
	$("#projectStartDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("#projectEndDate").datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	$("#projectsGrid").jqGrid({
		url:'app/projects/all',
		datatype: "json",
		colNames:['ID', 'ACTION', 'NAME', 'DESCRIPTION', 'TYPE', 'START', 'END'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 120, fixed: true, align: 'center'},
			{name: 'name', index: 'name', width: 125, fixed: true, align: 'center'},
			{name: 'description', index: 'description'},
			{name: 'type', index: 'type', width: 125, fixed: true, align: 'center', formatter: function(cellvalue, opts, rowObj) { return cellvalue.name; }},
			{name: 'startDate', index: 'startDate', width: 125, fixed: true, align: 'center'},
			{name: 'endDate', index: 'endDate', width: 125, fixed: true, align: 'center'}
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#projectsPager',
        height: 150,
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        shrinkToFit: true,
        sortorder: "desc",
        caption: "Projects",
        gridComplete: function() {
        	var ids = jQuery("#projectsGrid").jqGrid('getDataIDs');
        	for (var i = 0; i < ids.length; i++) {
        		var cl = ids[i];
        		edit = '<button type="button" class="btn btn-info btn-sm" onclick="loadProject(' + cl + ');">Edit</button>';
        		del = '<button type="button" class="btn btn-danger btn-sm" onclick="deleteProject(' + cl + ');">Delete</button>';
        		jQuery("#projectsGrid").jqGrid('setRowData', ids[i], {act: " " + edit + " " + del + " "});
        	}
        }
	});
	$("#projectsGrid").jqGrid('navGrid', '#projectsPager', { edit:false, add:false, del:false });
	
	$("#tasksGrid").jqGrid({
		url:'app/tasks/all',
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
        height: 150,
        autowidth: true,
        shrinkToFit: true,
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
	
	$("#partsGrid").jqGrid({
		url:'app/parts/all',
		datatype: "json",
		colNames:['ID', 'ACTION', 'MANUFACTURER', 'NAME', 'DESCRIPTION', 'TYPE'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 120, fixed: true, align: 'center'},
			{name: 'manufacturer', index: 'manufacturer', width: 175, fixed: true, align: 'center'},
			{name: 'name', index: 'name', width: 125, fixed: true, align: 'center'},
			{name: 'description', index: 'description'},
			{name: 'type', index: 'type', width: 125, fixed: true, align: 'center', formatter: function(cellvalue, opts, rowObj) { return cellvalue.name; }}
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#partsPager',
        height: 150,
        autowidth: true,
        shrinkToFit: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Parts",
        gridComplete: function() {
        	var ids = jQuery("#partsGrid").jqGrid('getDataIDs');
        	for (var i = 0; i < ids.length; i++) {
        		var cl = ids[i];
        		edit = '<button type="button" class="btn btn-info btn-sm" onclick="loadPart(' + cl + ');">Edit</button>';
        		del = '<button type="button" class="btn btn-danger btn-sm" onclick="deletePart(' + cl + ');">Delete</button>';
        		jQuery("#partsGrid").jqGrid('setRowData', ids[i], {act: " " + edit + " " + del + " "});
        	}
        }
	});
	$("#partsGrid").jqGrid('navGrid', '#partsPager', { edit:false, add:false, del:false });
});