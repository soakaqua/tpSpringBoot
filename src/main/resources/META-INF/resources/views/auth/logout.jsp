<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- bootstrap -->
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
<!-- lien bootstrap js local -->
<script rel="stylesheet" src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<!-- on test si la propri�t� du login existe ; si oui : propose d�connexion -->
<c:if test="${pageContext.request.userPrincipal.name != null}">

	<form method="post" action="${ctx}/logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn btn-link">d�connexion</button>
	</form>
</c:if>
