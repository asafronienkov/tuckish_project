
$(document).ready(function() {
	$("#typeSel").select2({
		placeholder: "Select a type...",
		allowClear: true,
		data:[
			{id: 0, text: 'Automotive'},
			{id: 1, text: 'Computer'},
			{id: 2, text: 'Network'},
			{id: 3, text: 'Software'},
			{id: 4, text: 'Construction'}
		]
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
			{name: 'type', index: 'type'},
			{name: 'start', index: 'start'},
			{name: 'end', index: 'end'}
        ],
        rowNum: 10,
        rowList: [10,20,30],
        pager: '#projectsPager',
        autowidth: true,
        sortname: 'id',
        viewrecords: true,
        sortorder: "desc",
        caption: "Projects"
	});
	$("#projectsGrid").jqGrid('navGrid', '#projectsPager', {edit:false, add:false, del:false});
});