package cime.event.query;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import crime.event.vocabulary.CRIME_EVENT;

public class QueryAllThefts {

	public static final String SelectAllThefts = "PREFIX crime: <"
			+ CRIME_EVENT.NAMESPACE + ">\n" + "SELECT ?event ?impact \n"
			+ "WHERE { \n" + "  ?event ?p crime:Theft . \n"
			+ "  OPTIONAL { ?event crime:impact ?impact } \n" + "} ";

	public static List<String> executeQuery(Repository repo)
			throws OpenRDFException {
		RepositoryConnection conn = repo.getConnection();
		List<String> results = new ArrayList<String>();
		try {
			TupleQuery tupleQuery = conn.prepareTupleQuery(
					QueryLanguage.SPARQL, SelectAllThefts);

			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();

					Value event = bindingSet.getValue("event");
					results.add(event.stringValue());

					Value impact = bindingSet.getValue("impact");
					results.add(impact.stringValue());
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
