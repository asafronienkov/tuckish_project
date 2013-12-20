
$(document).ajaxSend(function(event, request, settings) {
    $('#loading-indicator').show();
});

$(document).ajaxComplete(function(event, request, settings) {
    $('#loading-indicator').hide();
});

openManageTypes = function() {
	$("#newTypeName").val("");
	$("#updateTypeName").val("");
	$("#modifyTypeSel").select2("data", null);
	$("#deleteTypeSel").select2("data", null);
	$("#manageTypes").modal("show");
};

saveType = function() {
	var newTypeName = $("#newTypeName").val();
	if (newTypeName !== "") {
		$.ajax({
			type: "POST",
			url: "app/types/save",
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify({
				name: newTypeName
			}),
			success: function(msg) {
				$("#newTypeName").val("");
				$("#newTypeAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Save completed!</div></p>');
			},
			error: function(msg) {
				$("#newTypeAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Save failed!</div></p>');
			}
		});
	} else {
		$("#newTypeAlert").html('<p><div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Enter a valid name</div></p>');
	}
};

updateType = function() {
	var typeId = $("#modifyTypeSel").select2("val");
	var updatedName = $("#updateTypeName").val();
	if (typeId !== "" && updatedName !== "") {
		$.ajax({
			type: "PUT",
			url: "app/types/update",
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify({
				id: typeId,
				name: updatedName
			}),
			success: function(msg) {
				$("#updateTypeName").val("");
				$("#modifyTypeSel").select2("data", null);
				$("#updateAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Update completed!</div></p>');
			},
			error: function(msg) {
				$("#updateAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Update failed!</div></p>');
			}
		});
	} else {
		$("#updateAlert").html('<p><div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>A type and updated name must be provided</div></p>');
	}
};

deleteType = function() {
	var typeId = $("#deleteTypeSel").select2("val");
	if (typeId !== "") {
		$.ajax({
			type: "DELETE",
			url: "app/types/delete?id=" + typeId,
			success: function(msg) {
				$("#deleteTypeSel").select2("data", null);
				$("#deleteAlert").html('<p><div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Delete completed!</div></p>');
			},
			error: function(msg) {
				$("#deleteAlert").html('<p><div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Delete failed!</div></p>');
			}
		});
	} else {
		$("#deleteAlert").html('<p><div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Select a type to delete</div></p>');
	}
};

$(document).ready(function() {
	$("#modifyTypeSel").select2({
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
	$("#deleteTypeSel").select2({
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
});
