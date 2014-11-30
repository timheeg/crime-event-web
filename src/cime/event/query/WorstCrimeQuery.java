package cime.event.query;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Value;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import crime.event.vocabulary.CRIME_EVENT;

public class WorstCrimeQuery {

	public static final String SelectWorstCrime = "PREFIX rdf: <"
			+ RDF.NAMESPACE + ">\n" + "PREFIX crime: <" + CRIME_EVENT.NAMESPACE
			+ ">\n" + "SELECT ?type  \n" + "WHERE { \n" + "  ?event ?p ?o . \n"
			+ "  ?event rdf:type ?type . \n"
			+ "  ?event crime:impact ?impact . \n" + "} \n"
			+ "ORDER BY DESC(?impact) LIMIT 1";

	public static List<String> executeQuery(Repository repo)
			throws OpenRDFException {
		RepositoryConnection conn = repo.getConnection();
		List<String> results = new ArrayList<String>();
		try {
			TupleQuery tupleQuery = conn.prepareTupleQuery(
					QueryLanguage.SPARQL, SelectWorstCrime);

			TupleQueryResult result = tupleQuery.evaluate();
			try {
				if (!result.hasNext()) {
					results.add("none");
				}
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();

					Value event = bindingSet.getValue("type");
					results.add(event.stringValue());
				}
			} finally {
				result.close();
			}
		} finally {
			conn.close();
		}
		return results;
	}

}
