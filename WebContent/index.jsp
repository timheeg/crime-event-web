<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
<meta charset="ISO-8859-1">
<title>Crime Event Test</title>
</head>
<body>
	<div>This is a test.</div>
	<div>
		<a href="view">View Knowledge Base</a>
	</div>
	<div>
		<a href="load">Load Knowledge Base</a>
	</div>
	<div><a href="ontology/crime-event-ontology.owl">Crime Event Ontology</a></div>
</body>
</html>