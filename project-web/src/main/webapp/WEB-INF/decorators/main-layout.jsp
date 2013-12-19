<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Project<sitemesh:write property='title'/></title>

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link type="text/css" href="public/assets/select2/select2.css" rel="stylesheet" />
<link type="text/css" href="public/assets/css/tuckish.css" rel="stylesheet" />

<script src="//code.jquery.com/jquery-2.0.3.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="public/assets/select2/select2.min.js"></script>
<script type="text/javascript" src="public/assets/js/tuckish.js"></script>

<sitemesh:write property='head'/>
</head>
<body>
	<c:import url="/WEB-INF/include/navbar.jsp" />

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