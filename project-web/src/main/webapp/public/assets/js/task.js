
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
		},
		error: function(msg) {
			$("#genErrBody").html("Failed to load Task details, contact Support");
			$("#generalError").modal("show");
		}
	});
	
	$("#partsGrid").jqGrid({
		url:'app/parts/task?id=' + taskId,
		datatype: "json",
		colNames:['ID', 'ACTION', 'NAME', 'MANUFACTURER', 'NUMBER', 'COST', 'WEIGHT'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 110},
			{name: 'name', index: 'name'},
			{name: 'manufacturer', index: 'manufacturer'},
			{name: 'number', index: 'number'},
			{name: 'cost', index: 'cost'},
			{name: 'weight', index: 'weight'}
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#partsPager',
        autoheight: true,
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Parts",
        gridComplete: function() {
        	var ids = jQuery("#partsGrid").jqGrid('getDataIDs');
        	for (var i = 0; i < ids.length; i++) {
        		var cl = ids[i];
        		edit = '<button type="button" class="btn btn-info btn-sm" onclick="loadPart(' + cl + ');">Edit</button>';
        		del = '<button type="button" class="btn btn-danger btn-sm" onclick="removePart(' + cl + ');">Delete</button>';
        		jQuery("#partsGrid").jqGrid('setRowData', ids[i], {act: " " + edit + " " + del + " "});
        	}
        }
	});
	$("#partsGrid").jqGrid('navGrid', '#partsPager', { edit:false, add:false, del:false });
});