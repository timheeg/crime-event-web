<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
<meta charset="ISO-8859-1">

<style>
/*

Sticky Footer by Ryan Fait
http://ryanfait.com/

*/

* {
	margin: 0;
}

html, body {
	height: 100%;
}

body { 
	font-family: sans-serif;
}

.pad {
	padding-top: 10px;
}

.wrapper {
	min-height: 100%;
	margin: 0 auto -95px; /* the bottom margin is the negative value of the footer's height */
}

.footer, .push {
	height: 95px; /* .push must be the same height as .footer */
}

.footer {
	background-color: #E6E6E6;
	font-size: 12px;
}

.content {
	padding: 20px;
}
</style>
<title>Crime Event Test</title>
</head>
<body>
<div class="wrapper">
	<div class="content">
		<h1 style="padding-bottom:20px;">Crime Event Knowledge Base</h1>
		<div class="pad">
			<a href="view">View Knowledge Base</a>
		</div>
		<div class="pad">
			<a href="load">Load Knowledge Base</a>
		</div>
		<div class="pad">
			<a href="fake-create">Create Fake Events</a>
		</div>
		<div class="pad">
			<div>Queries:</div>
			<div class="pad">
				<span style="font-style: italic;">Find all occurrences of Theft? </span>
				<a href="query-thefts">Query All Thefts</a>
			</div>
			<div class="pad">
				<span style="font-style: italic;">What type of crime event has the most occurrences? </span>
				<a href="query-worst">Query Worst Crime</a>
			</div>
		</div>
		<h2 style="padding-top: 40px;">Crime Event Ontology</h2>
		<div class="pad">
			<span>Namespace: </span>
			<span style="font-family: monospace; color: red;">http://knoesis.wright.edu/ontologies/Crime#</span>
		</div>
		<div class="pad">
			<a href="ontology/crime-event-ontology.owl">Crime Event Ontology</a>
		</div>
	</div>
	<div class="push"></div>
</div>
<div class="footer">
	<div class="content">
		<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br /><div>Copyright &copy; 2014 Timothy Heeg, Surendra Marupudi.  This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.</div>
	</div>
</div>
</body>
</html>