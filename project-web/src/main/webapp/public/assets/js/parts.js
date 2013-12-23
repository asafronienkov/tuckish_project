
var selectedPart = {};
newPart = function() {
	selectedPart = {};
	
	$("#partDetails").modal("show");
};

loadPart = function(partId) {
	$.ajax({
		type: "GET",
		url: "app/parts/find?partId=" + partId,
		contentType: "application/json",
		success: function(data) {
			selectedPart = data;
			$("#partDetails").modal("show");
		},
		error: function(msg) {
			$("#taskAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Error loading part!</div></p>');
		}
	});
};

addPart = function() {
	$.ajax({
		type: "POST",
		url: "app/tasks/addPart?taskId=" + taskId + "&partId=" + $("#partSel").select2("val"),
		contentType: "application/json",
		success: function(data) {
			$("#partDetails").modal("hide");
			$("#taskAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Part added!</div></p>');
			$("#partsGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#partDetails").modal("hide");
			$("#taskAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Part add failed!</div></p>');
		}
	});
};

savePart = function() {
	$.ajax({
		type: "POST",
		url: "app/parts/save?taskId=" + taskId,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify({
			id: $("#partId").val(),
			name: $("#partName").val(),
			description: $("#partDesc").val(),
			manufacturer: $("#partManu").val(),
			number: $("#partNumber").val(),
			cost: $("#partCost").val(),
			weight: $("#partWeight").val(),
			type: {
				id: $("#partTypeSel").select2("val")
			}
		}),
		success: function(data) {
			$("#partDetails").modal("hide");
			$("#taskAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Part saved!</div></p>');
			$("#partsGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#partDetails").modal("hide");
			$("#taskAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Part save failed!</div></p>');
		}
	});
};

removePart = function(partId) {
	$.ajax({
		type: "DELETE",
		url: "app/parts/delete?taskId=" + taskId + "&partId=" + partId,
		contentType: "application/json",
		success: function(data) {
			$("#partsGrid").trigger("reloadGrid");
		},
		error: function(msg) {
			$("#taskAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Removal failed!</div></p>');
		}
	});
};

$(document).ready(function() {
	$("#partSel").select2({
		placeholder: "Select a part...",
		allowClear: true,
		ajax: {
			url: 'app/parts/all',
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
	
	$("#partTypeSel").select2({
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
	
	$("#partsGrid").jqGrid({
		url:'app/parts/task?id=' + taskId,
		datatype: "json",
		colNames:['ID', 'ACTION', 'MANUFACTURER', 'NAME', 'NUMBER', 'COST', 'WEIGHT', 'TYPE'],
		colModel:[
			{name: 'id', index: 'id', hidden: true},
			{name: 'act', index: 'act', width: 120, fixed: true, align: 'center'},
			{name: 'manufacturer', index: 'manufacturer', width: 175, fixed: true, align: 'center'},
			{name: 'name', index: 'name'},
			{name: 'number', index: 'number', width: 125, fixed: true, align: 'center'},
			{name: 'cost', index: 'cost', width: 50, fixed: true, align: 'center'},
			{name: 'weight', index: 'weight', width: 50, fixed: true, align: 'center'},
			{name: 'type', index: 'type', width: 125, fixed: true, align: 'center', formatter: function(cellvalue, opts, rowObj) { return cellvalue.name; }}
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
        		del = '<button type="button" class="btn btn-danger btn-sm" onclick="removePart(' + cl + ');">Remove</button>';
        		jQuery("#partsGrid").jqGrid('setRowData', ids[i], {act: " " + edit + " " + del + " "});
        	}
        }
	});
	$("#partsGrid").jqGrid('navGrid', '#partsPager', { edit:false, add:false, del:false });
	
	$("#partDetails").on('show.bs.modal', function (e) {
		if ($.isEmptyObject(selectedPart)) {
			$("#partHeading").html("New Part");
			
			$("#partId").val("");
			$("#partName").val("");
			$("#partManu").val("");
			$("#partNumber").val("");
			$("#partCost").val("");
			$("#partWeight").val("");
			$("#partTypeSel").select2("data", null);
		} else {
			$("#partHeading").html("Modify Part");
			
			$("#partId").val(selectedPart.id);
			$("#partName").val(selectedPart.name);
			$("#partManu").val(selectedPart.manufacturer);
			$("#partNumber").val(selectedPart.number);
			$("#partCost").val(selectedPart.cost);
			$("#partWeight").val(selectedPart.weight);
			$("#partTypeSel").select2("data", {id: selectedPart.type.id, text: selectedPart.type.name});
		}
	});
});