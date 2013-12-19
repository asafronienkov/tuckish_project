<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/ui-darkness/jquery-ui.min.css"> -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css">
<link type="text/css" href="public/assets/jqgrid/css/ui.jqgrid.css" rel="stylesheet" />
<link type="text/css" href="public/assets/datepicker/css/datepicker.css" rel="stylesheet" />

<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="public/assets/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="public/assets/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="public/assets/js/project.js"></script>
<script type="text/javascript" src="public/assets/js/task.js"></script>

<title> Details</title>
</head>
<body>
	<div id="projectAlert"></div>
	<div class="panel panel-primary">
		<div class="panel-heading">${project.name} Details</div>
		<div class="panel panel-body">
			<form role="form">
				<input type="hidden" id="projectId" value="${project.id}">
				<div class="form-group">
					<label for="projectName">Name</label>
					<input type="text" id="projectName" class="form-control" placeholder="Project Name..." value="${project.name}">
				</div>
				<div class="form-group">
					<label for="projectDesc">Description</label>
					<textarea id="projectDesc" class="form-control" rows="3" placeholder="Project Description...">${project.description}</textarea>
				</div>
				<div class="form-group">
					<label for="projectTypeSel">Type</label><br />
					<input type="hidden" id="projectTypeSel" class="populate placeholder" style="width:300px" value='{"id": ${project.type.id}, "text": "${project.type.name}"}'>
				</div>
				<div class="form-group">
					<label for="projectStartDate">Start Date</label><br />
					<input type="text" id="projectStartDate" class="datepicker" value="${project.startDate}">
				</div>
				<div class="form-group">
					<label for="projectEndDate">End Date</label><br />
					<input type="text" id="projectEndDate" class="datepicker" value="${project.endDate}">
				</div>
			</form>
			<p>
				<button type="button" class="btn btn-info" data-toggle="modal" data-target="#taskDetails" onclick="clearSelTask();">Create Task</button>
			</p>
			<!-- 	Tasks Grid -->
			<table id="tasksGrid"></table>
			<div id="tasksPager"></div>
		</div>
		<div class="panel-footer">
			<button type="button" class="btn btn-info" onclick="saveProject();">Save Changes</button>
		</div>
	</div>
	
	<!-- Task Details Modal -->
	<!-- Removed tabindex="-1" due to bug with select2 inside a bootstrap3 modal -->
	<div class="modal fade" id="taskDetails" role="dialog" aria-labelledby="taskModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="taskModalLabel"></h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<input type="hidden" id="taskId">
						<input type="hidden" id="projectId" value="${project.id}">
						<div class="form-group">
							<label for="projectName">Name</label>
							<input type="text" id="taskName" class="form-control" placeholder="Task Name...">
						</div>
						<div class="form-group">
							<label for="taskDesc">Description</label>
							<textarea id="taskDesc" class="form-control" rows="3" placeholder="Task Description..."></textarea>
						</div>
						<div class="form-group">
							<label for="taskStartDate">Start Date</label><br />
							<input type="text" id="taskStartDate" class="datepicker" value="">
						</div>
						<div class="form-group">
							<label for="taskEndDate">End Date</label><br />
							<input type="text" id="taskEndDate" class="datepicker" value="">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="saveTask();">Save changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>