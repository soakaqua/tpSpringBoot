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
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h1>Edition personne</h1>

		<form:form action="${ctx}/saveLogin" methode="post"
			modelAttribute="login">

 
			<%-- envoyer la version pour éviter de modifier quelque chose avec la mauvaise version --%>
			<div class="form-group">
				<form:label path="login">identifiant</form:label>
				<form:input path="login" cssClass="form-control" readonly="false"></form:input>
			</div>
			<div class="form-group">
				<form:label path="password">mot de passe</form:label>
				<form:input path="password" cssClass="form-control" readonly="false"></form:input>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">envoyer</button>
				<a href="${ctx}/auth/login" class="btn btn-warning">annuler</a>
			</div>
		</form:form>

	</div>
</body>
</html>