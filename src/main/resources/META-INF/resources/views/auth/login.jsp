<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!-- bootstrap -->
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<!-- lien bootstrap js local -->
<script rel="stylesheet" src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Connection</h1>

		<div>
			<c:if test="${param.error!=null}">
				<div class="alert alert-danger">Erreur d'authentification</div>
			</c:if>
			<!-- pour formulaire login : toujours method post et action "" -->
			<form method="post" action="">
				<!-- Pour éviter certaines attaques -->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div>
					<!-- input : doit s'appeler username (pour spring) -->
					<label for="username">login:</label> <input id="username"
						name="username" class="form-control">

				</div>
				<div>
					<!-- input : doit s'appeler password (pour spring) -->
					<label for="password">password:</label><input type="password"
						id="password" name="password" class="form-control">
				</div>
				<div>
					<button type="submit" class="btn btn-info">envoyer</button>
					<a href="${ctx}" class="btn btn-link">Retour accueil</a>
				</div>
				<div>
				<a href="${ctx}/inscription" class="btn btn-link">Inscription</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>