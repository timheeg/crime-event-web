package cime.event.query;

import crime.event.vocabulary.CRIME_EVENT;

public class SparqlQuery {

	public static final String SelectAll = "SELECT ?s WHERE { ?s ?p ?o . } ";

	public static final String SelectThefts = "PREFIX " + CRIME_EVENT.NS_NAME
			+ ": <" + CRIME_EVENT.NAMESPACE
			+ "> SELECT ?s WHERE { ?s ?p crime:Theft . } ";
}
