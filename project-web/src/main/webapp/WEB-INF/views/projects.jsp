<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<c:url value="/public/assets/js/projects.js" />"></script>

<title>s</title>
</head>
<body>
	<p>
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#projectDetails">Create Project</button>
	</p>
	<p>
		<div id="projectAlert"></div>
	</p>
	<!-- Project Details Modal -->
	<!-- Removed tabindex="-1" due to bug with select2 inside a bootstrap3 modal -->
	<div class="modal fade" id="projectDetails" role="dialog" aria-labelledby="projectModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="projectModalLabel">New Project</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<input type="hidden" id="projectId">
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
							<label for="projectStartDate">Start Date</label><br />
							<input type="text" id="projectStartDate" class="datepicker" value="">
						</div>
						<div class="form-group">
							<label for="projectEndDate">End Date</label><br />
							<input type="text" id="projectEndDate" class="datepicker" value="">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="saveNewProject();">Save Changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- Projects Grid -->
	<p>
		<table id="projectsGrid"></table>
		<div id="projectsPager"></div>
	</p>
	<!-- Tasks Grid -->
	<p>
		<table id="tasksGrid"></table>
		<div id="tasksPager"></div>
	</p>
	<!-- Parts Grid -->
	<p>
		<table id="partsGrid"></table>
		<div id="partsPager"></div>
	</p>
</body>
</html>