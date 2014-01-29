<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link type="text/css" href="<c:url value="/public/assets/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 20px;
}
</style>
<script type="text/javascript" src="<c:url value="/public/assets/jquery/jquery-2.0.3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/public/assets/bootstrap/js/bootstrap.min.js" />"></script>
</head>
<body>
	<c:if test="${not empty error}">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			Your login attempt was not successful, try again.<br /> Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please sign in</h3>
					</div>
					<div class="panel-body">
						<form name="loginForm" accept-charset="UTF-8" role="form" action="<c:url value='j_spring_security_check' />" method='POST'>
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="E-mail" id="j_username" name="j_username" type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password" id="j_password" name="j_password" type="password" value="">
								</div>
								<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
