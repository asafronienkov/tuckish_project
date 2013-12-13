
// This is a placeholder for when a users double clicks a project
var selectedProject = {};

loadProjectDetails = function(data) {
	$("#projectDetails").on("show.bs.modal", function(e) {
		// Set modal fields based on clicked item
	});
	$("#projectDetails").modal("show");
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
		},
		formatResults: function(data) {
			var debug; 
		}
	});
	$("#startDate").datepicker({
		format: 'yy-mm-dd'
	});
	$("#endDate").datepicker({
		format: 'yy-mm-dd'
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
			{name: 'start', index: 'start', formatter: 'date'},
			{name: 'end', index: 'end', formatter: 'date'}
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
        		loadProjectDetails(data);
        	});
        }
	});
	$("#projectsGrid").jqGrid('navGrid', '#projectsPager', {edit:false, add:false, del:false});
});