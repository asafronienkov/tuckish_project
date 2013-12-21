<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" href="<c:url value="/public/assets/jquery/ui/css/smoothness/jquery-ui-1.10.3.custom.min.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/public/assets/jqgrid/css/ui.jqgrid.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/public/assets/datepicker/css/datepicker.css" />" rel="stylesheet" />

<script type="text/javascript" src="<c:url value="/public/assets/jquery/ui/js/jquery-ui-1.10.3.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/jqgrid/js/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/jqgrid/js/i18n/grid.locale-en.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/js/parts.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/js/task.js" />"></script>

<title>: ${project.name}, Task: ${task.name}</title>
</head>
<body>
	<script type="text/javascript">
		var taskId = ${task.id};
		var projectId = ${project.id};
	</script>
	
	<c:url var="projectUrl" value="project" >
		<c:param name="id" value="${project.id}" />
	</c:url>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/projects" />">Projects</a></li>
		<li><a href="<c:url value="${projectUrl}" />">${project.name}</a></li>
		<li class="active">${task.name}</li>
	</ol>
	
	<div id="taskAlert"></div>
	
	<div class="panel panel-primary">
		<!-- 		<div class="panel-heading">Details</div> -->
		<div class="panel panel-body">
			<form role="form">
				<input type="hidden" id="projectId" value="${project.id}">
				<input type="hidden" id="taskId" value="${task.id}">
				<div class="form-group">
					<label for="taskName">Name</label>
					<input type="text" id="taskName" class="form-control" placeholder="Task Name...">
				</div>
				<div class="form-group">
					<label for="taskDesc">Description</label>
					<textarea id="taskDesc" class="form-control" rows="3" placeholder="Task Description..."></textarea>
				</div>
				<div class="form-group">
					<label for="taskStartDate">Start Date</label><br />
					<input type="text" id="taskStartDate" class="datepicker">
				</div>
				<div class="form-group">
					<label for="taskEndDate">End Date</label><br />
					<input type="text" id="taskEndDate" class="datepicker">
				</div>
			</form>
			<p>
				<button type="button" class="btn btn-info" data-toggle="modal" data-target="partDetails">Add Part</button>
			</p>
			<!-- 	Tasks Grid -->
			<table id="partsGrid"></table>
			<div id="partsPager"></div>
		</div>
		<div class="panel-footer">
			<button type="button" class="btn btn-info" onclick="saveTask();">Save Changes</button>
		</div>
	</div>
	
	<!-- Part Details Modal -->
	<!-- Removed tabindex="-1" due to bug with select2 inside a bootstrap3 modal -->
	<div class="modal fade" id="partDetails" role="dialog" aria-labelledby="partModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="partModalLabel"></h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<input type="hidden" id="projectId" value="${project.id}">
						<input type="hidden" id="taskId" value="${task.id}">
						<input type="hidden" id="partId">
						<div class="form-group">
							<label for="taskName">Name</label>
							<input type="text" id="taskName" class="form-control" placeholder="Task Name...">
						</div>
						<div class="form-group">
							<label for="taskDesc">Description</label>
							<textarea id="taskDesc" class="form-control" rows="3" placeholder="Task Description..."></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="savePart();">Save Changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>
</html>