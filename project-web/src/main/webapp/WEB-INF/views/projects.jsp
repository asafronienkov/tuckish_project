<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/ui-darkness/jquery-ui.min.css"> -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css">
<link type="text/css" href="public/assets/jqgrid/css/ui.jqgrid.css" rel="stylesheet" />
<link type="text/css" href="public/assets/datepicker/css/datepicker.css" rel="stylesheet" />
<link type="text/css" href="public/assets/select2/select2.css" rel="stylesheet" />

<!-- <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="public/assets/datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="public/assets/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="public/assets/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="public/assets/select2/select2.min.js"></script>
<script type="text/javascript" src="public/assets/js/project.js"></script>

<title>Management</title>
</head>
<body>
	<p>
		<button type="button" class="btn btn-default"
			data-toggle="modal" data-target="#projectDetails">Create
			Project</button>
	</p>
	<!-- Project Details Modal -->
	<!-- Removed tabindex="-1" due to bug with select2 inside a bootstrap3 modal -->
	<div class="modal fade" id="projectDetails" role="dialog" aria-labelledby="projectModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="projectModalLabel">Project Details</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="projectName">Name</label>
							<input type="text" id="projectName" class="form-control" placeholder="Project Name...">
						</div>
						<div class="form-group">
							<label for="projectDesc">Description</label>
							<textarea id="projectDesc" class="form-control" rows="3" placeholder="Project Description..."></textarea>
						</div>
						<div class="form-group">
							<label for="typeSel">Type</label><br />
							<input type="hidden" id="typeSel" class="populate placeholder" style="width:300px">
						</div>
						<div class="form-group">
							<label for="startDate">Start Date</label><br />
							<input type="text" id="startDate" class="datepicker" value="" >
						</div>
						<div class="form-group">
							<label for="endDate">End Date</label><br />
							<input type="text" id="endDate" class="datepicker" value="" >
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- Projects Grid -->
	<p>
		<table id="projectsGrid"></table>
		<div id="projectsPager"></div>
	</p>
</body>
</html>