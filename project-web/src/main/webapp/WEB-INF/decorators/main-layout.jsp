<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Project<sitemesh:write property='title'/></title>

<link type="text/css" href="<c:url value="/public/assets/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/public/assets/select2/select2.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/public/assets/css/tuckish.css" />" rel="stylesheet" />

<script type="text/javascript" src="<c:url value="/public/assets/jquery/jquery-2.0.3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/bootstrap/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/select2/select2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/js/tuckish.js" />"></script>

<sitemesh:write property='head'/>
</head>
<body>
	<c:import url="/WEB-INF/include/navbar.jsp" />

	<img src="public/assets/images/ajax-loader.gif" id="loading-indicator" style="display:none" />

	<div class="modal fade" id="generalError">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Error</h4>
				</div>
				<div class="modal-body">
					<p>
						<div id="genErrBody"></div>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 	the admin modal for types -->
	<div class="modal fade" id="manageTypes" role="dialog" aria-labelledby="typeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="typeModalLabel">Manage Types</h4>
				</div>
				<div class="modal-body">
					<table id="typesGrid"></table>
					<form role="form">
						<div class="form-group">
							<label for="newTypeName">Create a type</label>
							<div class="input-group">
								<input type="text" id="newTypeName" class="form-control" placeholder="Enter a name...">
								<span class="input-group-btn">
									<button type="button" class="btn btn-primary" onclick="saveType();">Save</button>
								</span>							
							</div>
						</div>
						
						<div id="newTypeAlert"></div>
						
						<div class="form-group">
							<label>Modify a type</label><br />
							<p><input type="hidden" id="modifyTypeSel" class="populate placeholder" style="width:300px"></p>
							<div class="input-group">
								<input type="text" id="updateTypeName" class="form-control" placeholder="New name...">
								<span class="input-group-btn">
									<button type="button" class="btn btn-warning" onclick="updateType();">Modify</button>
								</span>							
							</div>
						</div>
						
						<div id="updateAlert"></div>
						
						<div class="form-group">
							<label for="deleteSpan">Delete a type</label><br />
							<span id="deleteSpan">
								<input type="hidden" id="deleteTypeSel" class="populate placeholder" style="width:300px">
								<button type="button" class="btn btn-danger" onclick="deleteType();">Delete</button>
							</span>
						</div>
						
						<div id="deleteAlert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<sitemesh:write property='body'/>

<!-- 	<div class="navbar navbar-fixed-bottom"> -->
<%-- 		<c:import url="/WEB-INF/include/footer.jsp" /> --%>
<!-- 	</div> -->
</body>
</html>